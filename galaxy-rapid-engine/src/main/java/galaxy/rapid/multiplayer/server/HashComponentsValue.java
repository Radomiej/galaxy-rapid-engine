package galaxy.rapid.multiplayer.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.artemis.Component;

public class HashComponentsValue {
	private Map<Class<? extends Component>, Integer> hashMap;
	private Set<Class<? extends Component>> classArchive;

	public HashComponentsValue() {
		hashMap = new HashMap<Class<? extends Component>, Integer>();
		classArchive = new HashSet<Class<? extends Component>>();
	}
	
	public void putComponent(Component component){
		hashMap.put(component.getClass(), component.hashCode());
	}
	
	public void removeComponent(Component component){
		if(hashMap.remove(component) == null){
			System.err.println("You try remove no exist component class");
		}
	}
	public boolean containsComponent(Component component){
		return hashMap.containsKey(component.getClass());
	}
	
	public boolean isFreshComponent(Component freshComponent){
		int oldComponentHash = hashMap.get(freshComponent.getClass());
		return oldComponentHash != freshComponent.hashCode();
	}

	public Collection<? extends Class<? extends Component>> getComponentClasses() {
		return hashMap.keySet();
	}
}
