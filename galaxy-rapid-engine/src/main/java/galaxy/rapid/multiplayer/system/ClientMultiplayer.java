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

import galaxy.rapid.common.ComponentsBag;
import galaxy.rapid.components.KeyboardComponent;
import galaxy.rapid.multiplayer.CommonClass;
import galaxy.rapid.multiplayer.ControllerObject;
import galaxy.rapid.multiplayer.JsonGameComponent;
import galaxy.rapid.multiplayer.PartUuid;
import pl.silver.JGNL.JGNLClient;
import pl.silver.JGNL.Network;
import pl.silver.JGNL.event.ServerEventReciver;
import pl.silver.JGNL.request.Future;
import pl.silver.reflection.SilverReflectionUtills;

public class ClientMultiplayer extends BaseSystem {

	
	private UuidEntityManager uuidManager;
	private Json json;
	private UUID clientPlayerUuid;

	private JGNLClient client;
	private String host;
	
	private List<JsonGameComponent> incomingEntities = Collections.synchronizedList(new ArrayList<JsonGameComponent>());

	public ClientMultiplayer(String host) {
		this.host = host;
	}

	@Override
	protected void initialize() {
		Log.set(Log.LEVEL_INFO);
		json = new Json();
		client = new JGNLClient(CommonClass.INSTANCE.getCommonsTab());
		try {
//			client.connect("ns364990.ovh.net", Network.portTCP, Network.portUDP);
//			client.connect("ra-studio.ddns.net", Network.portTCP, Network.portUDP);
			client.connect(host, Network.portTCP, Network.portUDP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		client.setReciver(new ServerEventReciver() {
			@Override
			public void reciveEvent(Object event) {
				if (event instanceof JsonGameComponent) {
					JsonGameComponent reciveObject = (JsonGameComponent) event;
					incomingEntities.add(reciveObject);
				} else {
					System.out.println("Other object: " + event.getClass().getSimpleName());
				}
			}
		});
		Future future = client.sendRequest("join");
		System.out.println("Wysy³am future");
		while(!future.isAnswer()){
//			System.out.println("Czekam na future");
		}
		System.out.println("Odebrano uuid gracza");
		PartUuid partUuid = (PartUuid) future.getAnswerMessage();
		clientPlayerUuid = new UUID(partUuid.getMostSignBit(), partUuid.getLestSignBit());
		
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
				System.out.println("Create new local entity, uuid: " + uuid);
				new EntityBuilder(world).with(bag.getComponentsTab()).UUID(uuid).build();
			} else {
				if(object.isDelete()){
					System.err.println("Remove entity: " + uuid);
					world.deleteEntity(localEntity);
					continue;
				}
				addOrCopyComponentsData(localEntity, bag);
				removeOldComponents(localEntity, object.getRemovedComponents());
				
			}
		}
	}

	private void removeOldComponents(Entity localEntity, ComponentsBag removedComponents) {
		if(removedComponents == null) {return;}
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
//		System.out.println("Sending input state");
		client.sendEvent(controllerObject);
	}
}
