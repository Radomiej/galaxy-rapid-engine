package galaxy.rapid.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import galaxy.rapid.asset.RapidAsset;

public class DrawableHelper {

	@Deprecated
	public static Drawable getDrawableFromTexture(String asset) {
		Texture texture = RapidAsset.INSTANCE.getTexture(asset);
		Image image = new Image(texture);	
		Drawable drawable = image.getDrawable();
		return drawable;
	}

	public static Drawable getDrawableFromAsset(String regionName) {
		Sprite sprite = RapidAsset.INSTANCE.getSprite(regionName);
//		Texture texture = sprite.getTexture();
//		TextureRegion region = RapidAsset.INSTANCE.getTextureRegion(regionName);
		SpriteDrawable drawable = new SpriteDrawable(sprite);
		return drawable;
	}

}
