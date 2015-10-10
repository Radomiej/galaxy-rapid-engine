package galaxy.rapid.systems;

import java.util.Set;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.google.common.eventbus.EventBus;

import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.event.SelectEntityEvent;
import galaxy.rapid.event.UpdateEntityEvent;
import galaxy.rapid.managers.BodyManager;

public class EditorMovableAllEntitySystem extends BaseSystem {

	private ComponentMapper<BodyComponent> bodyMapper;
	private BodyManager bodyManager;

	@Wire
	private SpriteBatch spriteBatch;
	@Wire
	private EventBus eventBus;

	private int draggedEntityId = -1;

	public EditorMovableAllEntitySystem() {
	}

	@Override
	protected void processSystem() {

		Set<Entity> entities = bodyManager.getBuffs();
		if (Gdx.input.justTouched()) {
			getCurrentClickEntity(entities);
		} else if (draggedEntityId >= 0) {
			moveEntity();
		}

		if (!Gdx.input.isTouched()) {
			draggedEntityId = -1;
		}

	}

	private void moveEntity() {
		updateBodyPosition();
		sendUpdateEvent();

	}

	private void sendUpdateEvent() {
		UpdateEntityEvent entityEvent = new UpdateEntityEvent();
		entityEvent.setClickEntity(world.getEntity(draggedEntityId));
		eventBus.post(entityEvent);
	}

	private void updateBodyPosition() {
		BodyComponent body = bodyMapper.get(draggedEntityId);
		body.getPosition().x += Gdx.input.getDeltaX();
		body.getPosition().y -= Gdx.input.getDeltaY();
	}

	private void getCurrentClickEntity(Set<Entity> entities) {
		Rectangle clickRectangle = new Rectangle(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 1, 1);

		System.out.println("Click: " + clickRectangle);
		
		for (Entity entity : entities) {
			if (checkCollisionWithEntity(clickRectangle, entity)) {
				return;
			}
		}

	}

	private boolean checkCollisionWithEntity(Rectangle clickRectangle, Entity entity) {
		BodyComponent body = bodyMapper.get(entity);
		Rectangle bodyRect = new Rectangle(body.getPosition().x, body.getPosition().y, body.getSize().x,
				body.getSize().y);

		Rectangle intersector = new Rectangle();
		if (Intersector.intersectRectangles(clickRectangle, bodyRect, intersector)) {
			setSelectedEntity(entity);
			sendEvent(entity);
			return true;
		}
		return false;
	}

	private void setSelectedEntity(Entity entity) {
		draggedEntityId = entity.getId();
	}

	private void sendEvent(Entity entity) {
		SelectEntityEvent selectEvent = new SelectEntityEvent();
		selectEvent.setClickEntity(entity);
		try {
			eventBus.post(selectEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
