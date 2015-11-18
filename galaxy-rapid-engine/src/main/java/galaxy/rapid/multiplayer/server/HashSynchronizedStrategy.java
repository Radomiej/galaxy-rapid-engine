package galaxy.rapid.multiplayer.server;

import static pl.silver.reflection.SilverReflectionUtills.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.artemis.Component;
import com.artemis.Entity;

import galaxy.rapid.common.ComponentsBag;
import galaxy.rapid.common.UuidHelper;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.components.marker.TransientNetworkElement;
import galaxy.rapid.multiplayer.EntityHelper;
import galaxy.rapid.multiplayer.JsonGameComponent;
import pl.silver.JGNL.JGNLServer;

public class HashSynchronizedStrategy implements SynchronizedStrategy {

	private JGNLServer server;
	private Map<UUID, HashComponentsValue> entitiesMemory = new HashMap<UUID, HashComponentsValue>();

	public HashSynchronizedStrategy(JGNLServer server2) {
		this.server = server2;
		System.out.println("HashSynchronizedStrategy");
	}

	@Override
	public void sendEntity(Entity e) {
		ComponentsBag bag = EntityHelper.getComponentsFromEntity(e);
		clearBagFromTransientComponent(bag);

		UUID uuid = UuidHelper.getUuidFromEntity(e);

		JsonGameComponent sendUpdateObject = new JsonGameComponent();
		sendUpdateObject.setMostSignBit(uuid.getMostSignificantBits());
		sendUpdateObject.setLestSignBit(uuid.getLeastSignificantBits());

		if (!entitiesMemory.containsKey(uuid)) {
			System.out.println("Wysy³am nowy obiekt");
			HashComponentsValue hashComponentsBag = new HashComponentsValue();
			fillHashValueBag(hashComponentsBag, bag.getComponents());
			entitiesMemory.put(uuid, hashComponentsBag);
			sendUpdateObject.setComponents(bag);
			server.sendEvent(sendUpdateObject);
			return;
		}

		HashComponentsValue oldBagValues = entitiesMemory.get(uuid);
		Set<Component> freshComponents = bag.getComponents();

		Set<Class<? extends Component>> componentsClassToRemove = new HashSet<Class<? extends Component>>(oldBagValues.getComponentClasses());
		Set<Component> componentsToUpdate = new HashSet<Component>();
		Set<Component> componentsToAdd = new HashSet<Component>();
		// Usun z listy do usuniecia componenty ktore nadal sa
		// Dodaj je do listy freshComponent
		for (Component freshComponent : freshComponents) {
			componentsClassToRemove.remove(freshComponent.getClass());
			if(oldBagValues.containsComponent(freshComponent)){
				if(oldBagValues.isFreshComponent(freshComponent)){
					componentsToUpdate.add(freshComponent);
					oldBagValues.putComponent(freshComponent);
				}
			}
			else{
				System.out.println("Nowy komponent: " + freshComponent.getClass().getSimpleName());
				componentsToAdd.add(freshComponent);
				oldBagValues.putComponent(freshComponent);
			}
		}

		Set<Component> componentsToRemove = new HashSet<Component>();
		for (Class componentClass : componentsClassToRemove) {
			Component component = (Component) create(componentClass);
			oldBagValues.removeComponent(component);
			componentsToRemove.add(component);
		}

		JsonGameComponent sendHardObject = new JsonGameComponent();
		sendHardObject.setMostSignBit(uuid.getMostSignificantBits());
		sendHardObject.setLestSignBit(uuid.getLeastSignificantBits());
		boolean sendHard = false;
		if (componentsToAdd.size() > 0) {
			sendHard = true;
			sendHardObject.setComponents(new ComponentsBag(componentsToAdd));
		}
		if (componentsToRemove.size() > 0) {
			sendHard = true;
			sendHardObject.setRemovedComponents(new ComponentsBag(componentsToRemove));
		}
		if (sendHard) {
			server.sendEvent(sendHardObject);
		}

		bag.setComponents(componentsToUpdate);
		sendUpdateObject.setComponents(bag);

		// System.out.println("Update components: " +
		// sendUpdateObject.getComponents());
		server.sendUdpEvent(sendUpdateObject);
	}

	private void fillHashValueBag(HashComponentsValue hashComponentsBag, Set<Component> components) {
		for (Component component : components) {
			if(hashComponentsBag.containsComponent(component)){
				if(hashComponentsBag.isFreshComponent(component)){
					hashComponentsBag.putComponent(component);
				}
			}else{
				hashComponentsBag.putComponent(component);
			}
		}
	}

	private void clearBagFromTransientComponent(ComponentsBag bag) {
		HashSet<Component> components = (HashSet<Component>) bag.getComponents();
		HashSet<Component> toRemove = new HashSet<Component>();
		Iterator<Component> iterator = components.iterator();

		while (iterator.hasNext()) {
			Component component = iterator.next();
			if (component instanceof TransientNetworkElement) {
				toRemove.add(component);
			}
		}
		components.removeAll(toRemove);
	}

	@Override
	public void sendRemoveEntity(Entity e) {
		UUID uuid = UuidHelper.getUuidFromEntity(e);
		System.out.println("Server deleted entity: " + uuid);
		JsonGameComponent sendObject = new JsonGameComponent();
		sendObject.setMostSignBit(uuid.getMostSignificantBits());
		sendObject.setLestSignBit(uuid.getLeastSignificantBits());
		sendObject.setDelete(true);

		server.sendEvent(sendObject);
	}
}
