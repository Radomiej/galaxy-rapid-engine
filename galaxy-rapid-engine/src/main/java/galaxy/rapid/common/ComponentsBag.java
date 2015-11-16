package galaxy.rapid.common;

import java.util.HashSet;
import java.util.Set;

import com.artemis.Component;

import galaxy.rapid.components.BodyComponent;

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
	
	public Component[] getComponentsTab(){
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

	public void addComponent(Component component) {
		components.add(component);
	}
}
