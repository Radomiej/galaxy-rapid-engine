package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import galaxy.rapid.asset.RapidAsset;
import galaxy.rapid.components.PositionComponent;
import galaxy.rapid.components.SpriteComponent;
import galaxy.rapid.configuration.RapidConfiguration;

public enum SpriteRenderer implements Renderer {
	INSTANCE;

	public void render(Entity e, Batch batch) {
		ComponentMapper<SpriteComponent> spriteMapper = ComponentMapper.getFor(SpriteComponent.class, e.getWorld());
		ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class, e.getWorld());
		
		//BodyComponent body = bodyMapper.get(e);
		PositionComponent position = positionMapper.get(e);

		if (position == null){
			Gdx.app.error("SpriteRenderer", "PositionComponent doesn`t exist!");
			return;
		}

		SpriteComponent spriteComponent = spriteMapper.get(e);
		String assetName = spriteComponent.getSpriteAsset();
		Sprite sprite = RapidAsset.INSTANCE.getSprite(assetName);

		float posX = position.getPosition().x;
		float posY = position.getPosition().y;
		
		
//		System.out.println("GR: " + RapidConfiguration.INSTANCE.getGameRatio());
//		
//		System.out.println("position: " + position);
//		System.out.println("posX: " + posX + " posY: " + posY);
//		System.out.println("sizeX: " + sizeX + " sizeY: " + sizeY);
//		System.out.println("originX: " + originX + " originX: " + originX);
		sprite.setPosition(posX, posY);
//		sprite.setSize(sizeX, sizeY);
//		sprite.setOrigin(originX, originY);
		sprite.setRotation(position.getRotation());
		sprite.draw(batch);
	}

}
