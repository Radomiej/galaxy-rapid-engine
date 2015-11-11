package galaxy.rapid.screen;

import com.badlogic.gdx.Screen;

public final class ChangeScreenEvent {
	private Screen newScene;
	private boolean disposeCurrent;

	public ChangeScreenEvent(Screen newScene) {
		this(newScene, true);
	}
	
	public ChangeScreenEvent(Screen newScene, boolean disposeCurrent) {
		this.newScene = newScene;
		this.disposeCurrent = disposeCurrent;
	}

	public Screen getNewScene() {
		return newScene;
	}

	public void setNewScene(Screen newScene) {
		this.newScene = newScene;
	}

	public boolean isDisposeCurrent() {
		return disposeCurrent;
	}

	public void setDisposeCurrent(boolean disposeCurrent) {
		this.disposeCurrent = disposeCurrent;
	}
}
