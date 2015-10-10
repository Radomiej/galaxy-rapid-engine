package galaxy.rapid.screen;

import com.badlogic.gdx.Screen;

public final class ChangeSceneEvent {
	private Screen newScene;
	private boolean disposeCurrent;

	public ChangeSceneEvent(Screen newScene) {
		this(newScene, false);
	}
	
	public ChangeSceneEvent(Screen newScene, boolean disposeCurrent) {
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
