package galaxy.rapid.event;

import net.mostlyoriginal.api.event.common.Event;

public class PhysicDebugEnterChangeEvent implements Event {
	public final boolean enableDebugRender;

	public PhysicDebugEnterChangeEvent(boolean debugRender) {
		this.enableDebugRender = debugRender;
	}
}
