package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.components.SpriteComponent;

public enum SpriteRenderer implements Renderer {
	INSTANCE;
	
	public void render(Entity e, Batch batch) {
		ComponentMapper<SpriteComponent> spriteMapper = ComponentMapper.getFor(SpriteComponent.class, e.getWorld());
		ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class, e.getWorld());
				
		BodyComponent body = bodyMapper.get(e);
		SpriteComponent spriteComponent = spriteMapper.get(e);
		Sprite sprite = spriteComponent.getSprite();
		sprite.setPosition(body.getPosition().x, body.getPosition().y);
		sprite.setSize(body.getSize().x, body.getSize().y);
		sprite.setOrigin(body.getOrigin().x, body.getOrigin().y);
		sprite.setRotation(body.getRotation());
		sprite.draw(batch);		
	}

}
