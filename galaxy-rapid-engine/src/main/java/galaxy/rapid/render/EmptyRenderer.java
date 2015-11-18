package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import galaxy.rapid.asset.RapidAsset;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.components.PositionComponent;
import galaxy.rapid.configuration.RapidConfiguration;

public enum EmptyRenderer implements Renderer {
	INSTANCE;
	private com.badlogic.gdx.graphics.glutils.ShapeRenderer shapeRenderer;

	private EmptyRenderer() {
		shapeRenderer = new com.badlogic.gdx.graphics.glutils.ShapeRenderer();
	}

	public void render(Entity e, Batch batch) {
		ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class, e.getWorld());
		ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class, e.getWorld());
		
		BodyComponent body = bodyMapper.get(e);
		PositionComponent position = positionMapper.get(e);
		
		if(body == null || position == null) return;
		
		float sizeX = body.getSize().x;
		float sizeY = body.getSize().y ;
		sizeX *= RapidConfiguration.INSTANCE.getGameRatio();
		sizeY *= RapidConfiguration.INSTANCE.getGameRatio();
		
		float originX = body.getOrigin().x ;
		float originY = body.getOrigin().y;
		originX *= RapidConfiguration.INSTANCE.getGameRatio();
		originY *= RapidConfiguration.INSTANCE.getGameRatio();
		
		float posX = position.getPosition().x - originX;
		float posY = position.getPosition().y - originY;
		posX *= RapidConfiguration.INSTANCE.getGameRatio();
		posY *= RapidConfiguration.INSTANCE.getGameRatio();
		
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.rect(posX, posY, originX, originY, sizeX, sizeY, 1, 1, position.getRotation());
		shapeRenderer.end();
	}

}
