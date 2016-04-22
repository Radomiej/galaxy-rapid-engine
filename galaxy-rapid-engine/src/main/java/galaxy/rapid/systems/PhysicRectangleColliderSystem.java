package galaxy.rapid.systems;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import galaxy.rapid.common.UuidHelper;
import galaxy.rapid.components.Box2dComponent;
import galaxy.rapid.components.PositionComponent;
import galaxy.rapid.components.RectangleColliderComponent;
import galaxy.rapid.event.RemoveRectangleColliderComponent;
import galaxy.rapid.eventbus.RapidBus;
import galaxy.rapid.physic.BodyCreator;
import galaxy.rapid.physic.Box2dFactory;
import net.mostlyoriginal.api.event.common.Subscribe;

public class PhysicRectangleColliderSystem extends EntityProcessingSystem {

	private Map<RectangleColliderComponent, UUID> colliderToEntityMap = new HashMap<RectangleColliderComponent, UUID>();

	private ComponentMapper<Box2dComponent> box2dMapper;
	private ComponentMapper<PositionComponent> positionMapper;
	private ComponentMapper<RectangleColliderComponent> rectangleColliderMapper;

	@Wire
	private RapidBus rapidBus;
	private PhysicSystem physicSystem;

	public PhysicRectangleColliderSystem() {
		super(Aspect.all(Box2dComponent.class, PositionComponent.class, RectangleColliderComponent.class));
	}

	@Override
	protected void initialize() {
		rapidBus.register(this);
	}

	@Override
	public void inserted(Entity e) {
		Box2dComponent box2d = box2dMapper.get(e);
		PositionComponent position = positionMapper.get(e);
		RectangleColliderComponent rectangleCollider = rectangleColliderMapper.get(e);
		Body body = box2d.getBody();
		Fixture rectangleFixture = Box2dFactory.createRectangle(physicSystem.getPhysicWorld(), position,
				rectangleCollider, body);
		rectangleFixture.setUserData(box2d.getFixtureData());
		box2d.setRectangleFixture(rectangleFixture);
		colliderToEntityMap.put(rectangleCollider, UuidHelper.getUuidFromEntity(e));
		Gdx.app.log("PhysicRectangleColliderSystem", "Added Fixture");
	}

	@Override
	protected void begin() {
	}

	@Override
	protected void process(Entity e) {

	}

	@Override
	public void removed(Entity e) {
		Box2dComponent box2d = box2dMapper.get(e);
		Fixture removeFixture = box2d.getRectangleFixture();
		box2d.getBody().destroyFixture(removeFixture);
		Gdx.app.log("PhysicRectangleColliderSystem", "Remove Fixture");
	}

	

	@Subscribe
	public void removeComponentEvent(RemoveRectangleColliderComponent removeComponent) {
		UUID uuid = colliderToEntityMap.get(removeComponent.rectangleColliderComponent);
		UuidHelper.getEntityFromUuid(uuid, world).edit().remove(removeComponent.rectangleColliderComponent.getClass());
		colliderToEntityMap.remove(removeComponent.rectangleColliderComponent);
		Gdx.app.log("PhysicRectangleColliderSystem", "Remove Fixture Event");
	}
}
