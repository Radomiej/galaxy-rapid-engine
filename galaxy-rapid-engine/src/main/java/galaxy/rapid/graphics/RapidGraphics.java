package galaxy.rapid.graphics;

import galaxy.rapid.configuration.RapidConfiguration;

public class RapidGraphics {

	public static float getWidth() {
		return RapidConfiguration.INSTANCE.getGameWidth();
	}

	public static float getHeight() {
		return RapidConfiguration.INSTANCE.getGameHeight();
	}

}
