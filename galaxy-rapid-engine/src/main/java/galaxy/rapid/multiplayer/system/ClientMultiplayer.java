package galaxy.rapid.multiplayer.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.artemis.BaseSystem;
import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.managers.UuidEntityManager;
import com.artemis.utils.EntityBuilder;
import com.badlogic.gdx.utils.Json;
import com.esotericsoftware.minlog.Log;

import galaxy.rapid.RapidEngine;
import galaxy.rapid.common.ComponentsBag;
import galaxy.rapid.components.KeyboardComponent;
import galaxy.rapid.multiplayer.CommonClass;
import galaxy.rapid.multiplayer.ControllerObject;
import galaxy.rapid.multiplayer.JsonGameComponent;
import galaxy.rapid.multiplayer.event.FullSynchronizedEvent;
import galaxy.rapid.network.server.PartUuid;
import pl.silver.JGNL.JGNLClient;
import pl.silver.JGNL.Network;
import pl.silver.JGNL.event.ServerEventReciver;
import pl.silver.JGNL.request.Future;
import pl.silver.reflection.SilverReflectionUtills;

public class ClientMultiplayer extends BaseSystem {

	private UuidEntityManager uuidManager;
	private UUID clientPlayerUuid;

	private List<JsonGameComponent> incomingEntities = Collections.synchronizedList(new ArrayList<JsonGameComponent>());

	public ClientMultiplayer(UUID playerUuid) {
		clientPlayerUuid = playerUuid;
	}

	@Override
	protected void initialize() {
		Log.set(Log.LEVEL_INFO);
		
		RapidEngine.INSTANCE.NETWORK.setReciver(new ServerEventReciver() {
			@Override
			public void reciveEvent(Object event) {
				if (event instanceof JsonGameComponent) {
					JsonGameComponent reciveObject = (JsonGameComponent) event;
					incomingEntities.add(reciveObject);
//					System.out.println("Odebrano JsonGameComponent ");
				} else {
					System.out.println("Other object: " + event.getClass().getSimpleName());
				}
			}
		});
		
		RapidEngine.INSTANCE.NETWORK.sendEvent(new FullSynchronizedEvent());
	}

	@Override
	protected void processSystem() {
		processReciveEvent();
	}

	private void processReciveEvent() {
		List<JsonGameComponent> incomingEvents = new ArrayList<JsonGameComponent>(incomingEntities);
		incomingEntities.clear();

		for (JsonGameComponent object : incomingEvents) {
			UUID uuid = new UUID(object.getMostSignBit(), object.getLestSignBit());
			Entity localEntity = uuidManager.getEntity(uuid);

			ComponentsBag bag = object.getComponents();
			if (localEntity == null) {
//				System.out.println("Create new local entity, uuid: " + uuid + " bag: " + bag.getComponents().size());
				new EntityBuilder(world).with(bag.getComponentsTab()).UUID(uuid).build();
			} else {
				if (object.isDelete()) {
//					System.err.println("Remove entity: " + uuid);
					world.deleteEntity(localEntity);
					continue;
				}
				addOrCopyComponentsData(localEntity, bag);
				removeOldComponents(localEntity, object.getRemovedComponents());

			}
		}
	}

	private void removeOldComponents(Entity localEntity, ComponentsBag removedComponents) {
		if (removedComponents == null) {
			return;
		}
		for (Component removeComponent : removedComponents.getComponents()) {
			localEntity.edit().remove(removeComponent.getClass());
		}
	}

	private void addOrCopyComponentsData(Entity localEntity, ComponentsBag bag) {
		for (Component newData : bag.getComponents()) {
			Component orginal = localEntity.getComponent(newData.getClass());
			if (orginal == null) {
				localEntity.edit().add(newData);
			} else {
				SilverReflectionUtills.copyProperties(orginal, newData);
			}

		}
	}

	public UUID getPlayerUuid() {
		return clientPlayerUuid;
	}

	public void sendControllerComponent(KeyboardComponent keyboardComponent) {
		ControllerObject controllerObject = new ControllerObject();
		controllerObject.setKeyboardComponent(keyboardComponent);
		controllerObject.setPartUuid(new PartUuid(clientPlayerUuid));
		RapidEngine.INSTANCE.NETWORK.sendEvent(controllerObject);
	}
}
