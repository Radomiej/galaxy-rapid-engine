package galaxy.rapid.screen;

import com.badlogic.gdx.Screen;

public abstract class RapidScreen implements Screen {
	private boolean initialize = false;

	public void show() {
		if (!initialize) {
			initialize = true;
			initialize();
		}
	}

	protected abstract void initialize();
	
}
