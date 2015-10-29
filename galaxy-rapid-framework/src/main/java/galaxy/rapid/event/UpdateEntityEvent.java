package galaxy.rapid.event;

import com.artemis.Entity;

public class UpdateEntityEvent {
	private Entity clickEntity;

	public Entity getClickEntity() {
		return clickEntity;
	}

	public void setClickEntity(Entity clickEntity) {
		this.clickEntity = clickEntity;
	}
}
