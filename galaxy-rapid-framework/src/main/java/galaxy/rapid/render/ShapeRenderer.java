package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import galaxy.rapid.asset.RapidAsset;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.configuration.RapidConfiguration;

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
		
//		float sizeX = body.getSize().x * RapidConfiguration.INSTANCE.getGameRatio();
//		float posX = body.getPosition().x * RapidConfiguration.INSTANCE.getGameRatio();
//		float sizeY = body.getSize().y * RapidConfiguration.INSTANCE.getGameRatio();
//		float posY = body.getPosition().y * RapidConfiguration.INSTANCE.getGameRatio();
//		float originX = body.getOrigin().x * RapidConfiguration.INSTANCE.getGameRatio();
//		float originY = body.getOrigin().y * RapidConfiguration.INSTANCE.getGameRatio();
		float sizeX = body.getSize().x;
		float posX = body.getPosition().x;
		float sizeY = body.getSize().y ;
		float posY = body.getPosition().y ;
		float originX = body.getOrigin().x ;
		float originY = body.getOrigin().y;
		
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.rect(posX, posY, originX, originY, sizeX, sizeY, 1, 1, body.getRotation());
		shapeRenderer.end();

	}

}
