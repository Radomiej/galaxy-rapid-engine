package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.Color;
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
		float opacity = spriteComponent.getOpacity();
		
		if(opacity <= 0.0f)
			return;
		
		String assetName = spriteComponent.getSpriteAsset();
		Sprite sprite = RapidAsset.INSTANCE.getSprite(assetName);

		float sizeX = body.getSize().x;
		float sizeY = body.getSize().y;
		float originX = body.getOrigin().x;
		float originY = body.getOrigin().y;
		float posX = position.getPosition().x - originX;
		float posY = position.getPosition().y - originY;
		
		sizeX *= RapidConfiguration.INSTANCE.getGameRatio();
		sizeY *= RapidConfiguration.INSTANCE.getGameRatio();
		
		originX *= RapidConfiguration.INSTANCE.getGameRatio();
		originY *= RapidConfiguration.INSTANCE.getGameRatio();
		
		posX *= RapidConfiguration.INSTANCE.getGameRatio();
		posY *= RapidConfiguration.INSTANCE.getGameRatio();

		
//		System.out.println("GR: " + RapidConfiguration.INSTANCE.getGameRatio());
//		
//		System.out.println("position: " + position);
//		System.out.println("posX: " + posX + " posY: " + posY);
//		System.out.println("sizeX: " + sizeX + " sizeY: " + sizeY);
//		System.out.println("originX: " + originX + " originX: " + originX);
		sprite.setPosition(posX, posY);
		sprite.setSize(sizeX, sizeY);
		sprite.setOrigin(originX, originY);
		sprite.setRotation(position.getRotation());
		
		Color sourceColor = sprite.getColor();
		sprite.setColor(sourceColor.r, sourceColor.g, sourceColor.b, opacity);
		sprite.draw(batch);
	}
}
