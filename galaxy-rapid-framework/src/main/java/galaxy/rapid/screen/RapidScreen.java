package galaxy.rapid.screen;

import com.badlogic.gdx.Screen;
import com.google.common.eventbus.EventBus;

public abstract class RapidScreen implements Screen, EventBusInjector {
	private boolean initialize = false;

	protected EventBus eventBus;

	public void show() {
		if (!initialize) {
			initialize = true;
			create();
		}
	}

	protected abstract void create();

	public void hide() {
	}

	public void pause() {
	}

	public void resume() {
	}

	public void resize(int width, int height) {
	}

	public void injectEventBus(EventBus globalEventBus) {
		eventBus = globalEventBus;
	}
	
	protected void changeScreen(Screen screen){
		ChangeScreenEvent changeScreenEvent = new ChangeScreenEvent(screen);
		eventBus.post(changeScreenEvent);
	}
}
