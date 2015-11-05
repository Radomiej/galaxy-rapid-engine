package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import galaxy.radpid.configuration.RapidConfiguration;
import galaxy.rapid.asset.RapidAsset;
import galaxy.rapid.components.BodyComponent;

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
		
		float sizeX = body.getSize().x * RapidConfiguration.INSTANCE.getDefaultScale();
		float posX = body.getPosition().x * RapidConfiguration.INSTANCE.getDefaultScale();
		float sizeY = body.getSize().y * RapidConfiguration.INSTANCE.getDefaultScale();
		float posY = body.getPosition().y * RapidConfiguration.INSTANCE.getDefaultScale();
		float originX = body.getOrigin().x * RapidConfiguration.INSTANCE.getDefaultScale();
		float originY = body.getOrigin().y * RapidConfiguration.INSTANCE.getDefaultScale();
		
		withoutEntitySprite.setPosition(posX, posY);
		withoutEntitySprite.setSize(sizeX, sizeY);
		withoutEntitySprite.setOrigin(originX, originY);
		withoutEntitySprite.setRotation(body.getRotation());
		withoutEntitySprite.draw(batch);

	}

}
