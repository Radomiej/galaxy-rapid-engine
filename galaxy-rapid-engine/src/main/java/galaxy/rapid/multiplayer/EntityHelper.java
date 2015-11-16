package galaxy.rapid.multiplayer;

import java.util.Iterator;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.utils.Bag;

import galaxy.rapid.common.ComponentsBag;

public class EntityHelper {
	public static ComponentsBag getComponentsFromEntity(Entity e) {
		Bag<Component> bags = new Bag<Component>();
		bags = e.getComponents(bags);
		Iterator<Component> iterator = bags.iterator();

		ComponentsBag componentsBag = new ComponentsBag();
		while (iterator.hasNext()) {
			componentsBag.getComponents().add(iterator.next());
		}
		return componentsBag;
	}
}
