package galaxy.rapid.multiplayer.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.artemis.Component;
import com.artemis.Entity;

import galaxy.rapid.common.ComponentsBag;
import galaxy.rapid.common.UuidHelper;
import galaxy.rapid.multiplayer.EntityHelper;
import galaxy.rapid.multiplayer.JsonGameComponent;
import pl.silver.JGNL.JGNLServer;

@Deprecated
public class SimpleSynchronizedStrategy implements SynchronizedStrategy{

	private JGNLServer server;
	private Map<UUID, ComponentsBag> entitiesMemory = new HashMap<UUID, ComponentsBag>();

	public SimpleSynchronizedStrategy(JGNLServer server2) {
		this.server = server2;
	}
	
	@Override
	public void sendFullEntity(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEntity(Entity e) {
		ComponentsBag bag = EntityHelper.getComponentsFromEntity(e);
		UUID uuid = UuidHelper.getUuidFromEntity(e);
		
		JsonGameComponent sendObject = new JsonGameComponent();
		sendObject.setMostSignBit(uuid.getMostSignificantBits());
		sendObject.setLestSignBit(uuid.getLeastSignificantBits());
		
		if(!entitiesMemory.containsKey(uuid)){
			entitiesMemory.put(uuid, bag);
			
			sendObject.setComponents(bag);
			server.sendEvent(sendObject);
			return;
		}
		
		
		
		ComponentsBag oldBag = entitiesMemory.get(uuid);
		Set<Component> freshComponents = bag.getComponents();
		Set<Component> oldComponents = oldBag.getComponents();
		Set<Component> componentsToRemove = new HashSet<Component>(oldComponents);
		Set<Component> componentsToUpdate = new HashSet<Component>();
		//Usun z listy do usuniecia componenty ktore nadal sa
		//Dodaj je do listy freshComponent
		for(Component freshComponent : freshComponents){
			componentsToUpdate.add(freshComponent);
			for(Component removeComponent : oldComponents){
				if(freshComponent.getClass() != removeComponent.getClass()) {continue;}
				componentsToRemove.remove(removeComponent);
				break;
			}			
		}
		
		if(componentsToRemove.size() > 0){
			System.out.println("Ilosc usuwanych komponentow: " + componentsToRemove.size());
		}
		
		
		sendObject.setRemovedComponents(new ComponentsBag(componentsToRemove));
		sendObject.setComponents(bag);
		
		server.sendEvent(sendObject);
	}

	@Override
	public void sendRemoveEntity(Entity e) {
		ComponentsBag bag = EntityHelper.getComponentsFromEntity(e);
		UUID uuid = UuidHelper.getUuidFromEntity(e);
		System.out.println("Server deleted entity: " + uuid);
		JsonGameComponent sendObject = new JsonGameComponent();
		sendObject.setMostSignBit(uuid.getMostSignificantBits());
		sendObject.setLestSignBit(uuid.getLeastSignificantBits());
		sendObject.setDelete(true);
		
		server.sendEvent(sendObject);
	}
}
