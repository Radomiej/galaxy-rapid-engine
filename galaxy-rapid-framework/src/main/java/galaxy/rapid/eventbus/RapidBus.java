package galaxy.rapid.eventbus;

import net.mostlyoriginal.api.event.common.Event;
import net.mostlyoriginal.api.event.common.EventSystem;

public class RapidBus {
	private EventSystem eventSystem;
	private String name = "Default";

	public RapidBus() {
		eventSystem = new EventSystem();
	}

	public RapidBus(String name) {
		this();
		this.name = name;
	}

	public void post(Event event) {
		eventSystem.dispatch(event);
	}

	public void register(Object registerObject) {
		eventSystem.registerEvents(registerObject);
	}

	// TODO for feature upragdes
	public void unregister(Object createEntitySystem) {

	}

	public void process() {
		eventSystem.process();
	}
}
