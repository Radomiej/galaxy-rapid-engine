package galaxy.rapid.asset;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;

public class SpineAssetModel
{
	private Skeleton skeleton;
	private AnimationStateData animationStateData;
	private TextureAtlas textureAtlas;

	public Skeleton getSkeleton() {
		return skeleton;
	}

	public void setSkeleton(Skeleton skeleton) {
		this.skeleton = skeleton;
	}

	public AnimationStateData getAnimationStateData() {
		return animationStateData;
	}

	public void setAnimationStateData(AnimationStateData animationStateData) {
		this.animationStateData = animationStateData;
	}	

	TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}

	void setTextureAtlas(TextureAtlas textureAtlas) {
		this.textureAtlas = textureAtlas;
	}

}
