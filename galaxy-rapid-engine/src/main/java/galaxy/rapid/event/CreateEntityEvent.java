package galaxy.rapid.event;

import galaxy.rapid.common.ComponentsBag;
import net.mostlyoriginal.api.event.common.Event;

public class CreateEntityEvent implements Event{
	private ComponentsBag componentsBag;

	public CreateEntityEvent(ComponentsBag componentsBag) {
		this.componentsBag = componentsBag;
	}

	public ComponentsBag getComponentsBag() {
		return componentsBag;
	}

	public void setComponentsBag(ComponentsBag componentsBag) {
		this.componentsBag = componentsBag;
	}
}
