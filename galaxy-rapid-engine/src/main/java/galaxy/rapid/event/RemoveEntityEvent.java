package galaxy.rapid.event;

import com.artemis.Entity;

import net.mostlyoriginal.api.event.common.Event;

public class RemoveEntityEvent implements Event {
	private Entity removeEntity;

	public RemoveEntityEvent() {
	}

	public RemoveEntityEvent(Entity removeEntity) {
		this.removeEntity = removeEntity;
	}

	public Entity getRemoveEntity() {
		return removeEntity;
	}

	public void setRemoveEntity(Entity removeEntity) {
		System.out.println("Otrzymano zdazenie usuniecia entity");
		this.removeEntity = removeEntity;
	}

}
