package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import galaxy.rapid.asset.RapidAsset;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.configuration.RapidConfiguration;

public enum EmptyRenderer implements Renderer {
	INSTANCE;
	private Sprite withoutEntitySprite;

	private EmptyRenderer() {
		Texture textureWithoutEntity = RapidAsset.INSTANCE.getTexture("asset/emptyEntity.png");
		withoutEntitySprite = new Sprite(textureWithoutEntity);
	}

	public void render(Entity e, Batch batch) {
		ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class, e.getWorld());
		BodyComponent body = bodyMapper.get(e);
		if(body == null) return;
		
		float sizeX = body.getSize().x * RapidConfiguration.INSTANCE.getGameRatio();
		float posX = body.getPosition().x * RapidConfiguration.INSTANCE.getGameRatio();
		float sizeY = body.getSize().y * RapidConfiguration.INSTANCE.getGameRatio();
		float posY = body.getPosition().y * RapidConfiguration.INSTANCE.getGameRatio();
		float originX = body.getOrigin().x * RapidConfiguration.INSTANCE.getGameRatio();
		float originY = body.getOrigin().y * RapidConfiguration.INSTANCE.getGameRatio();
		
		withoutEntitySprite.setPosition(posX, posY);
		withoutEntitySprite.setSize(sizeX, sizeY);
		withoutEntitySprite.setOrigin(originX, originY);
		withoutEntitySprite.setRotation(body.getRotation());
		withoutEntitySprite.draw(batch);

	}

}
