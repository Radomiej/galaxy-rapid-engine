package galaxy.rapid.systems;

import java.util.ArrayList;
import java.util.Collection;
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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import galaxy.rapid.common.UuidHelper;
import galaxy.rapid.components.Box2dComponent;
import galaxy.rapid.components.PositionComponent;
import galaxy.rapid.components.RectangleColliderComponent;
import galaxy.rapid.event.RemoveBox2dComponentEvent;
import galaxy.rapid.event.RemoveRectangleColliderComponent;
import galaxy.rapid.eventbus.RapidBus;
import galaxy.rapid.physic.BodyCreator;
import net.mostlyoriginal.api.event.common.Subscribe;

public class PhysicSystem extends EntityProcessingSystem {

	private Collection<Box2dComponent> componentsToRemove = new ArrayList<Box2dComponent>();
	private float accumulator = 0;
	private final float TIME_STEP = 1 / 60f;
	private final int VELOCITY_ITERATIONS = 6;
	private final int POSITION_ITERATIONS = 2;

	private ComponentMapper<Box2dComponent> box2dMapper;
	private ComponentMapper<PositionComponent> positionMapper;

	private World physicWorld;
	@Wire
	private RapidBus rapidBus;

	public PhysicSystem() {
		super(Aspect.all(Box2dComponent.class, PositionComponent.class));
	}

	@Override
	protected void initialize() {
		physicWorld = new World(new Vector2(0, -10), true);
		rapidBus.register(this);
	}

	@Override
	public void inserted(Entity e) {
		Box2dComponent box2d = box2dMapper.get(e);
		PositionComponent position = positionMapper.get(e);
		Body body = BodyCreator.getBody(position.getPosition(), physicWorld);
		body.setUserData(box2d.getUserData());
		box2d.setBody(body);
	}

	@Override
	protected void begin() {
		// fixed time step
		// max frame time to avoid spiral of death (on slow devices)
		float frameTime = Math.min(world.getDelta(), 0.25f);
		accumulator += frameTime;
		while (accumulator >= TIME_STEP) {
			physicWorld.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
			accumulator -= TIME_STEP;
		}
	}

	@Override
	protected void process(Entity e) {
		Box2dComponent box2d = box2dMapper.get(e);
		PositionComponent position = positionMapper.get(e);

		for(Box2dComponent boxToRemove : componentsToRemove){
			if(box2d == boxToRemove){
				physicWorld.destroyBody(box2d.getBody());
				e.edit().remove(boxToRemove);
				return;
			}
		}
		
		position.setPosition(box2d.getBody().getPosition());
		position.setRotation(box2d.getBody().getAngle() * MathUtils.radiansToDegrees);
	}

	@Override
	public void removed(Entity e) {
		Gdx.app.log("PhysicRectangleColliderSystem", "Remove Fixture Event");
	}

	public World getPhysicWorld() {
		return physicWorld;
	}

	@Subscribe
	public void removeComponentEvent(RemoveBox2dComponentEvent removeComponent) {
		componentsToRemove.add(removeComponent.box2dComponent);
		Gdx.app.log("PhysicSystem", "Remove Body");
	}
}