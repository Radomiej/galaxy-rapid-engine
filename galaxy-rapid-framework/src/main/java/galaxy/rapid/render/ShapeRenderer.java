package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import galaxy.rapid.asset.RapidAsset;
import galaxy.rapid.components.BodyComponent;

public enum ShapeRenderer implements Renderer {
	INSTANCE;
	private com.badlogic.gdx.graphics.glutils.ShapeRenderer shapeRenderer;
	
	private ShapeRenderer() {
		shapeRenderer = new com.badlogic.gdx.graphics.glutils.ShapeRenderer();
		
	}

	public void render(Entity e, Batch batch) {
		ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class, e.getWorld());
		BodyComponent body = bodyMapper.get(e);
		if(body == null) return;
		
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.rect(body.getPosition().x, body.getPosition().y, body.getOrigin().x, body.getOrigin().y, body.getSize().x, body.getSize().y, 1, 1, body.getRotation());
		shapeRenderer.end();

	}

}
