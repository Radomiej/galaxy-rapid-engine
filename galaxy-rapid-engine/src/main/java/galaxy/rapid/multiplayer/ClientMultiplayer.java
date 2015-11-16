package galaxy.rapid.multiplayer;

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

import galaxy.rapid.common.ComponentsBag;
import pl.silver.JGNL.JGNLClient;
import pl.silver.JGNL.Network;
import pl.silver.JGNL.event.ServerEventReciver;
import pl.silver.reflection.SilverReflectionUtills;

public class ClientMultiplayer extends BaseSystem {

	UuidEntityManager uuidManager;
	Json json;

	List<JsonGameComponent> incomingEntities = Collections.synchronizedList(new ArrayList<JsonGameComponent>());

	@Override
	protected void initialize() {
		json = new Json();
		JGNLClient client = new JGNLClient();
		try {
			client.connect("ra-studio.ddns.net", Network.portTCP, Network.portUDP);
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
					System.out.println("Inny obiekt: " + event.getClass().getSimpleName());
				}
			}
		});

	}

	@Override
	protected void processSystem() {
		List<JsonGameComponent> incomingEvents = new ArrayList<JsonGameComponent>(incomingEntities);
		incomingEntities.clear();
		
		for (JsonGameComponent object : incomingEvents) {
			UUID uuid = new UUID(object.getMostSignBit(), object.getLestSignBit());
			Entity e = uuidManager.getEntity(uuid);

			ComponentsBag bag = object.getComponents();
			if (e == null) {
				new EntityBuilder(world).with(bag.getComponentsTab()).UUID(uuid).build();
			} else {

				for (Component newData : bag.getComponents()) {
					Component orginal = e.getComponent(newData.getClass());
					if (orginal == null) {
						e.edit().add(newData);
					} else {
						SilverReflectionUtills.copyProperties(orginal, newData);
					}

				}
			}
		}
	}

}
