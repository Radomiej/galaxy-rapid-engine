package galaxy.rapid.components;

import com.artemis.Component;

public class SpriteComponent extends Component {
	private String spriteAsset;

	public SpriteComponent() {
	}
	
	public SpriteComponent(String string) {
		spriteAsset = string;
	}

	public String getSpriteAsset() {
		return spriteAsset;
	}

	public void setSpriteAsset(String spriteAsset) {
		this.spriteAsset = spriteAsset;
	}
}
