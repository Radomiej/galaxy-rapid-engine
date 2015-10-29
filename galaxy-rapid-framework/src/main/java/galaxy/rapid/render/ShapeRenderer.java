package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import galaxy.radpid.configuration.RapidConfiguration;
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
		
		float sizeX = body.getSize().x * RapidConfiguration.INSTANCE.getCurrentScale();
		float posX = body.getPosition().x * RapidConfiguration.INSTANCE.getCurrentScale();
		float sizeY = body.getSize().y * RapidConfiguration.INSTANCE.getCurrentScale();
		float posY = body.getPosition().y * RapidConfiguration.INSTANCE.getCurrentScale();
		float originX = body.getOrigin().x * RapidConfiguration.INSTANCE.getCurrentScale();
		float originY = body.getOrigin().y * RapidConfiguration.INSTANCE.getCurrentScale();
		
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.rect(posX, posX, originX, originY, sizeX, sizeY, 1, 1, body.getRotation());
		shapeRenderer.end();

	}

}
