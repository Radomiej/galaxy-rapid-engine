package galaxy.rapid.event;

import com.artemis.Entity;

import galaxy.rapid.components.ColliderComponent;
import net.mostlyoriginal.api.event.common.Event;

public class AddColliderComponent implements Event{
	public final ColliderComponent colliderComponent;
	public final Entity entity;
	
	public AddColliderComponent(ColliderComponent colliderComponent, Entity entity) {
		this.colliderComponent = colliderComponent;
		this.entity = entity;
	}
}
