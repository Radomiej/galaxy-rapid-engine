package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import galaxy.rapid.asset.RapidAsset;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.components.PositionComponent;
import galaxy.rapid.components.SpriteComponent;
import galaxy.rapid.configuration.RapidConfiguration;

public enum SpriteRenderer implements Renderer {
	INSTANCE;

	public void render(Entity e, Batch batch) {
		ComponentMapper<SpriteComponent> spriteMapper = ComponentMapper.getFor(SpriteComponent.class, e.getWorld());
		ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class, e.getWorld());
		ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class, e.getWorld());
		
		BodyComponent body = bodyMapper.get(e);
		PositionComponent position = positionMapper.get(e);

		if (body == null || position == null)
			return;

		SpriteComponent spriteComponent = spriteMapper.get(e);
		String assetName = spriteComponent.getSpriteAsset();
		Sprite sprite = RapidAsset.INSTANCE.getSprite(assetName);

		float sizeX = body.getSize().x;
		float sizeY = body.getSize().y;
		sizeX *= RapidConfiguration.INSTANCE.getGameRatio();
		sizeY *= RapidConfiguration.INSTANCE.getGameRatio();

		float originX = body.getOrigin().x;
		float originY = body.getOrigin().y;
		originX *= RapidConfiguration.INSTANCE.getGameRatio();
		originY *= RapidConfiguration.INSTANCE.getGameRatio();

		float posX = position.getPosition().x - originX;
		float posY = position.getPosition().y - originY;
		posX *= RapidConfiguration.INSTANCE.getGameRatio();
		posY *= RapidConfiguration.INSTANCE.getGameRatio();

		sprite.setPosition(posX, posY);
		sprite.setSize(sizeX, sizeY);
		sprite.setOrigin(originX, originY);
		sprite.setRotation(position.getRotation());
		sprite.draw(batch);
	}

}
