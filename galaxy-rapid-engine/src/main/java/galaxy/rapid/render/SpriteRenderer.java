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

		if(sprite == null) {
			Gdx.app.error("SpriteRender", "Couldn`t load sprite: " + assetName);
			return;
		}
		
		sprite.setScale(position.getScale().x, position.getScale().y);
		float sizeX = sprite.getWidth();
		float sizeY = sprite.getHeight();
		
		float posX = position.getPosition().x - sizeX / 2;
		float posY = position.getPosition().y - sizeY / 2;
		
		
//		System.out.println("GR: " + RapidConfiguration.INSTANCE.getGameRatio());
//		
//		System.out.println("position: " + position);
//		System.out.println("posX: " + posX + " posY: " + posY);
//		System.out.println("sizeX: " + sizeX + " sizeY: " + sizeY);
//		System.out.println("originX: " + originX + " originX: " + originX);
		sprite.setPosition(posX, posY);
//		sprite.setSize(sizeX, sizeY);
		sprite.setOrigin(sizeX / 2, sizeY / 2);
		sprite.setRotation(position.getRotation());
		sprite.draw(batch);
	}

}
