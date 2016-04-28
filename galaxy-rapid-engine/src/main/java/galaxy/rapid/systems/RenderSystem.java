package galaxy.rapid.systems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import galaxy.rapid.camera.RapidCamera;
import galaxy.rapid.components.RenderComponent;
import galaxy.rapid.components.ShapeComponent;
import galaxy.rapid.components.SpineComponent;
import galaxy.rapid.components.SpriteComponent;
import galaxy.rapid.event.PhysicDebugEnterChangeEvent;
import galaxy.rapid.eventbus.RapidBus;
import galaxy.rapid.managers.RenderableManager;
import galaxy.rapid.render.EmptyRenderer;
import galaxy.rapid.render.Renderer;
import galaxy.rapid.render.ShapeRenderer;
import galaxy.rapid.render.SpineRenderer;
import galaxy.rapid.render.SpriteRenderer;
import net.mostlyoriginal.api.event.common.Subscribe;

public class RenderSystem extends BaseSystem {

	private ComponentMapper<RenderComponent> renderMapper;
	private ComponentMapper<SpriteComponent> spriteMapper;
	private ComponentMapper<SpineComponent> spineMapper;
	private ComponentMapper<ShapeComponent> shapeMapper;

	private RenderableManager renderableManager;
	@Wire
	private PolygonSpriteBatch polygonBatch;

	private PhysicSystem physicSystem;
	private Box2DDebugRenderer debugRenderer;
	private boolean debugRender = false;
	@Wire
	private RapidCamera camera;
	@Wire
	private RapidBus rapidBus;
	
	@Override
	protected void initialize() {
		debugRenderer = new Box2DDebugRenderer();
		rapidBus.register(this);
	}

	@Override
	protected void begin() {
		SpineRenderer.INSTANCE.prepareCamera(camera);
		polygonBatch.setProjectionMatrix(camera.getCombined());
		polygonBatch.begin();
	}

	@Override
	protected void processSystem() {
		for (Entity e : renderableManager.getRendererObjects()) {
			Renderer renderer = getRendererForEntity(e);
			// if(renderer == ShapeRenderer.INSTANCE) continue;
			renderer.render(e, polygonBatch);
		}
	}

	private Renderer getRendererForEntity(Entity e) {
		if (spriteMapper.has(e)) {
			return SpriteRenderer.INSTANCE;
		} else if (shapeMapper.has(e)) {
			return ShapeRenderer.INSTANCE;
		} else if (spineMapper.has(e)) {
			return SpineRenderer.INSTANCE;
		} else {
			return EmptyRenderer.INSTANCE;
		}
	}

	@Override
	protected void end() {
		polygonBatch.end();
		if (debugRender) {
			debugRenderer.render(physicSystem.getPhysicWorld(), camera.getCombined());
		}
	}

	@Subscribe
	public void physicDebugEnterChangeEvent(PhysicDebugEnterChangeEvent event){
		debugRender = event.enableDebugRender;
	}
}
