package galaxy.rapid.common;

import java.util.HashSet;
import java.util.Set;

import com.artemis.Component;

public class ComponentsBag {
	private HashSet<Component> components;
	
	public ComponentsBag() {
		components = new HashSet<Component>();
	}
	
	public ComponentsBag(Component... componentsTab) {
		components = new HashSet<Component>();		
		for(Component component : componentsTab){
			components.add(component);
		}
	}
	
	public Component[] getComponentsLikeTab(){
		Component[] componentsTab = new Component[components.size()];
		componentsTab = components.toArray(componentsTab);
		return componentsTab;
	}

	public Set<Component> getComponents() {
		return components;
	}

	public void setComponents(HashSet<Component> components) {
		this.components = components;
	}
}
