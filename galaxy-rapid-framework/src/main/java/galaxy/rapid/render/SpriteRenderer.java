package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import galaxy.radpid.configuration.RapidConfiguration;
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
		
		float sizeX = body.getSize().x * RapidConfiguration.INSTANCE.getDefaultScale();
		float posX = body.getPosition().x * RapidConfiguration.INSTANCE.getDefaultScale();
		float sizeY = body.getSize().y * RapidConfiguration.INSTANCE.getDefaultScale();
		float posY = body.getPosition().y * RapidConfiguration.INSTANCE.getDefaultScale();
		float originX = body.getOrigin().x * RapidConfiguration.INSTANCE.getDefaultScale();
		float originY = body.getOrigin().y * RapidConfiguration.INSTANCE.getDefaultScale();
		
		sprite.setPosition(posX, posY);
		sprite.setSize(sizeX, sizeY);
		sprite.setOrigin(originX, originY);
		sprite.setRotation(body.getRotation());
		sprite.draw(batch);		
	}

}
