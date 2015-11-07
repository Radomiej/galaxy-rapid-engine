package galaxy.rapid.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent extends Component {
	private Sprite sprite;

	public SpriteComponent() {
	}

	public SpriteComponent(Sprite sprite) {
		setSprite(sprite);
	}

	public SpriteComponent(Texture texture) {
		setSprite(new Sprite(texture));
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void changeTexture(Texture texture) {
		this.sprite.setTexture(texture);
	}
}
