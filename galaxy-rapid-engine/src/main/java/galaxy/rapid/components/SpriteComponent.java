package galaxy.rapid.components;

import com.artemis.Component;

import galaxy.rapid.asset.RapidAsset;

public class SpriteComponent extends Component {
	private String spriteAsset;
	private boolean atlas;
	private boolean memory;
	
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

	
	public boolean isAtlas() {
		return atlas;
	}

	public void setAtlas(boolean isAtlas) {
		this.atlas = isAtlas;
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

	public boolean isMemory() {
		return memory;
	}

	public void setMemory(boolean memory) {
		this.memory = memory;
	}
}
