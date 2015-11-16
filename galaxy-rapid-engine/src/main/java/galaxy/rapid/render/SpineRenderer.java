package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer;

import galaxy.rapid.components.BodyComponent;
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
		ComponentMapper<RenderComponent> renderMapper = ComponentMapper.getFor(RenderComponent.class, e.getWorld());
		ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class, e.getWorld());
		ComponentMapper<SpineComponent> spineMapper = ComponentMapper.getFor(SpineComponent.class, e.getWorld());

		BodyComponent body = bodyMapper.get(e);
		RenderComponent render = renderMapper.get(e);
		SpineComponent spine = spineMapper.get(e);
		AnimationState state = spine.getAnimationState();
		Skeleton skeleton = spine.getSkeleton();

		skeleton.setFlipX(render.isFlipX());
		
		float halfSizeX = (body.getSize().x / 2) * RapidConfiguration.INSTANCE.getGameRatio();
		float posX = body.getPosition().x * RapidConfiguration.INSTANCE.getGameRatio();
		float halfSizeY = (body.getSize().y / 2) * RapidConfiguration.INSTANCE.getGameRatio();
		float posY = body.getPosition().y * RapidConfiguration.INSTANCE.getGameRatio();
		
		skeleton.setPosition(posX + halfSizeX, posY + halfSizeY);
//		skeleton.setBonesToSetupPose();
//		skeleton.setSlotsToSetupPose();
//		System.out.println("Rotacja spine: " + body.getRotation());
		skeleton.getRootBone().setRotation(body.getRotation());
		skeleton.getColor().a = 1;
		state.update(e.getWorld().getDelta());
		state.apply(skeleton);
		skeleton.updateWorldTransform();

		renderer.draw((PolygonSpriteBatch)batch, skeleton);
	}

}
