package galaxy.rapid.systems;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;

import galaxy.rapid.components.RenderComponent;
import galaxy.rapid.components.ShapeComponent;
import galaxy.rapid.components.SpineComponent;
import galaxy.rapid.components.SpriteComponent;
import galaxy.rapid.managers.RenderableManager;
import galaxy.rapid.render.EmptyRenderer;
import galaxy.rapid.render.Renderer;
import galaxy.rapid.render.ShapeRenderer;
import galaxy.rapid.render.SpineRenderer;
import galaxy.rapid.render.SpriteRenderer;

public class RenderSystem extends BaseSystem {

	private ComponentMapper<RenderComponent> renderMapper;
	private ComponentMapper<SpriteComponent> spriteMapper;
	private ComponentMapper<SpineComponent> spineMapper;
	private ComponentMapper<ShapeComponent> shapeMapper;

	private RenderableManager renderableManager;
	@Wire
	private PolygonSpriteBatch polygonBatch;
	
	@Wire
	private OrthographicCamera camera;

	@Override
	protected void begin() {
		polygonBatch.setProjectionMatrix(camera.combined);
		polygonBatch.begin();
	}

	@Override
	protected void processSystem() {
		for (Entity e : renderableManager.getRendererObjects()) {
			System.out.println("Rysuje obiekt: " + e);
			Renderer renderer = getRendererForEntity(e);
			renderer.render(e, polygonBatch);			
		}
	}

	private Renderer getRendererForEntity(Entity e) {
		if(spriteMapper.has(e)){
			return SpriteRenderer.INSTANCE;
		} else if(shapeMapper.has(e)){
			return ShapeRenderer.INSTANCE;
		} else if(spineMapper.has(e)){
			return SpineRenderer.INSTANCE;
		}else{
			return EmptyRenderer.INSTANCE;
		}		
	}

	@Override
	protected void end() {
		polygonBatch.end();
	}

}
