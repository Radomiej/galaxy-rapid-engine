package galaxy.rapid.event;

import galaxy.rapid.components.ColliderComponent;
import net.mostlyoriginal.api.event.common.Event;

public class RemoveColliderComponent implements Event{
	public final ColliderComponent colliderComponent;
	
	public RemoveColliderComponent(ColliderComponent colliderComponent2) {
		this.colliderComponent = colliderComponent2;
	}
}
