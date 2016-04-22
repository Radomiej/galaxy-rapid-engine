package galaxy.rapid.event;

import com.artemis.Entity;

import galaxy.rapid.components.RectangleColliderComponent;
import net.mostlyoriginal.api.event.common.Event;

public class AddRectangleColliderComponent implements Event{
	public final RectangleColliderComponent rectangleColliderComponent;
	public final Entity entity;
	
	public AddRectangleColliderComponent(RectangleColliderComponent rectangleColliderComponent, Entity entity) {
		this.rectangleColliderComponent = rectangleColliderComponent;
		this.entity = entity;
	}
}
