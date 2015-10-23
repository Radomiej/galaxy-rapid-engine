package galaxy.rapid.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import galaxy.rapid.asset.RapidAsset;

public class DrawableHelper {

	public static Drawable getDrawableFromTexture(String asset) {
		Texture texture = RapidAsset.INSTANCE.getTexture(asset);
		Image image = new Image(texture);	
		Drawable drawable = image.getDrawable();
		return drawable;
	}

}
