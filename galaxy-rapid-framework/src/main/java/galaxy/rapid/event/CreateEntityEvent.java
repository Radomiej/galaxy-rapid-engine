package galaxy.rapid.event;

import galaxy.rapid.common.ComponentsBag;

public class CreateEntityEvent {
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
