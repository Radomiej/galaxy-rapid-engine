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

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((spriteAsset == null) ? 0 : spriteAsset.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpriteComponent other = (SpriteComponent) obj;
		if (spriteAsset == null) {
			if (other.spriteAsset != null)
				return false;
		} else if (!spriteAsset.equals(other.spriteAsset))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpriteComponent [spriteAsset=" + spriteAsset + "]";
	}
}
