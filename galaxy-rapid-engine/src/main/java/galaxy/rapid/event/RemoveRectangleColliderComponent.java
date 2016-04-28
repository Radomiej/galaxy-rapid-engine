package galaxy.rapid.event;

import galaxy.rapid.components.RectangleColliderComponent;
import net.mostlyoriginal.api.event.common.Event;

public class RemoveRectangleColliderComponent implements Event{
	public final RectangleColliderComponent rectangleColliderComponent;
	
	public RemoveRectangleColliderComponent(RectangleColliderComponent rectangleColliderComponent2) {
		this.rectangleColliderComponent = rectangleColliderComponent2;
	}
}
