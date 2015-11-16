package galaxy.rapid.event;

import com.artemis.Entity;

import net.mostlyoriginal.api.event.common.Event;

public class UpdateEntityEvent implements Event{
	private Entity clickEntity;

	public Entity getClickEntity() {
		return clickEntity;
	}

	public void setClickEntity(Entity clickEntity) {
		this.clickEntity = clickEntity;
	}
}
