package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer;

import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.components.RenderComponent;
import galaxy.rapid.components.SpineComponent;

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
		AnimationState state = spine.getState();
		Skeleton skeleton = spine.getSkeleton();

		skeleton.setFlipX(render.isFlipX());
		skeleton.setPosition(body.getPosition().x + body.getSize().x / 2, body.getPosition().y + body.getSize().y / 2);
//		skeleton.setBonesToSetupPose();
//		skeleton.setSlotsToSetupPose();
		skeleton.getColor().a = 1;
		state.update(e.getWorld().getDelta());
		state.apply(skeleton);
		skeleton.updateWorldTransform();
		renderer.draw(batch, skeleton);
	}

}
