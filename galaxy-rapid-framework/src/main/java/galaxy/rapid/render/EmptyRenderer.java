package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
		
		withoutEntitySprite.setPosition(body.getPosition().x, body.getPosition().y);
		withoutEntitySprite.setSize(body.getSize().x, body.getSize().y);
		withoutEntitySprite.setOrigin(body.getOrigin().x, body.getOrigin().y);
		withoutEntitySprite.setRotation(body.getRotation());
		withoutEntitySprite.draw(batch);

	}

}
