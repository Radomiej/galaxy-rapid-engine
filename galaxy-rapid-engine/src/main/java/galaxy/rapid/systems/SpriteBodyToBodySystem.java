package galaxy.rapid.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import galaxy.rapid.asset.RapidAsset;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.components.SpriteBodyToBodyComponent;
import galaxy.rapid.components.SpriteComponent;

public class SpriteBodyToBodySystem extends EntityProcessingSystem {

	private ComponentMapper<SpriteComponent> spriteComponentMapper;
	private ComponentMapper<BodyComponent> bodyComponentMapper;
	
	@SuppressWarnings("unchecked")
	public SpriteBodyToBodySystem() {
		super(Aspect.all(SpriteComponent.class, BodyComponent.class, SpriteBodyToBodyComponent.class));
	}
	
	@Override
	public void inserted(Entity e) {
		SpriteComponent spriteComponent = spriteComponentMapper.get(e);
		BodyComponent bodyComponent = bodyComponentMapper.get(e);
		Sprite sprite = RapidAsset.INSTANCE.getSprite(spriteComponent.getSpriteAsset());
		bodyComponent.setSize(new Vector2(sprite.getRegionWidth(), sprite.getRegionHeight()));
		bodyComponent.setOrigin(new Vector2(sprite.getOriginX(), sprite.getOriginY()));
	}

	@Override
	protected void process(Entity arg0) {
		
	}
}
