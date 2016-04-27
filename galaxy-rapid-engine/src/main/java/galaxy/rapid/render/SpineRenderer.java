package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer;

import galaxy.rapid.components.PositionComponent;
import galaxy.rapid.components.RenderComponent;
import galaxy.rapid.components.SpineComponent;
import galaxy.rapid.configuration.RapidConfiguration;

public enum SpineRenderer implements Renderer {
	INSTANCE;

	private SkeletonRenderer renderer;
	
	private SpineRenderer() {
		renderer = new SkeletonRenderer();
		renderer.setPremultipliedAlpha(true);
	}

	public void render(Entity e, Batch batch) {
		ComponentMapper<RenderComponent> renderMapper = (ComponentMapper<RenderComponent>) ComponentMapper.getFor(RenderComponent.class, e.getWorld());
		ComponentMapper<SpineComponent> spineMapper = (ComponentMapper<SpineComponent>) ComponentMapper.getFor(SpineComponent.class, e.getWorld());
		ComponentMapper<PositionComponent> positionMapper = (ComponentMapper<PositionComponent>) ComponentMapper.getFor(PositionComponent.class, e.getWorld());

		PositionComponent position = positionMapper.get(e);
		RenderComponent render = renderMapper.get(e);
		SpineComponent spine = spineMapper.get(e);
		AnimationState state = spine.getAnimationState();
		Skeleton skeleton = spine.getSkeleton();

		skeleton.setFlipX(render.isFlipX());
		
		float posX = position.getPosition().x;
		float posY = position.getPosition().y;
		
		skeleton.setPosition(posX, posY);
//		skeleton.setBonesToSetupPose();
//		skeleton.setSlotsToSetupPose();
//		System.out.println("Rotacja spine: " + body.getRotation());
		skeleton.getRootBone().setRotation(position.getRotation());
		skeleton.getColor().a = 1;
		state.update(e.getWorld().getDelta());
		state.apply(skeleton);
		skeleton.updateWorldTransform();

		renderer.draw((PolygonSpriteBatch)batch, skeleton);
	}

}
