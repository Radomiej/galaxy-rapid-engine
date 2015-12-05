package galaxy.rapid.render;

import java.util.ArrayList;
import java.util.List;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import galaxy.rapid.asset.RapidAsset;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.components.PositionComponent;
import galaxy.rapid.components.ShapeComponent;
import galaxy.rapid.configuration.RapidConfiguration;

public enum ShapeRenderer implements Renderer {
	INSTANCE;
	private com.badlogic.gdx.graphics.glutils.ShapeRenderer shapeRenderer;

	private ShapeRenderer() {
		shapeRenderer = new com.badlogic.gdx.graphics.glutils.ShapeRenderer();
	}

	public void render(Entity e, Batch batch) {
		ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class, e.getWorld());
		ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class, e.getWorld());
		ComponentMapper<ShapeComponent> shapeMapper = ComponentMapper.getFor(ShapeComponent.class, e.getWorld());
		
		BodyComponent body = bodyMapper.get(e);
		PositionComponent position = positionMapper.get(e);
		ShapeComponent shape = shapeMapper.get(e);
		
		if(body == null || position == null) return;
	
		batch.end();
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
		shapeRenderer.updateMatrices();
		shapeRenderer.begin(ShapeType.Line);
		List<Vector2> positions = shape.getPolygonPoints();
		List<Vector2> positionsReal = new ArrayList<Vector2>();
		for(Vector2 pos : positions){
			Vector2 realPos = pos.cpy();
			realPos.scl(RapidConfiguration.INSTANCE.getGameRatio());
			positionsReal.add(realPos);
		}
		
		for(int x = 1; x < positionsReal.size(); x++){
			shapeRenderer.line(positionsReal.get(x - 1), positionsReal.get(x));
		}
//		for(Vector2 vec : shape.getPolygonPoints()){
//			shapeRenderer.point(vec.x * RapidConfiguration.INSTANCE.getGameRatio(), vec.y * RapidConfiguration.INSTANCE.getGameRatio(), 0);
//		}
		shapeRenderer.end();
		batch.begin();
	}

}
