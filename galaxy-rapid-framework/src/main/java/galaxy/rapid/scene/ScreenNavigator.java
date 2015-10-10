package galaxy.rapid.scene;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.badlogic.gdx.Screen;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class ScreenNavigator {

	private Screen currentScreen;
	private Map<Class<? extends Screen>, Screen> activeOldScreens;
	private EventBus eventBus = new EventBus("GlobalAppNavigator");

	public ScreenNavigator(Screen startScreen) {
		eventBus.register(this);
		activeOldScreens = new HashMap<Class<? extends Screen>, Screen>();
		changeScreen(startScreen, false);
	}

	public void updateCurrentScreen(float delta) {
		currentScreen.render(delta);
	}

	private void createCurrentScreenView() {
		currentScreen.show();
	}

	private void exitCurrentScreenView(boolean dispose) {
		if (currentScreen != null) {
			currentScreen.hide();
			if (dispose)
				currentScreen.dispose();
			else
				activeOldScreens.put(currentScreen.getClass(), currentScreen);
		}
	}

	@Subscribe
	public void changeScreen(ChangeSceneEvent changeEvent) {
		try {
			changeScreen(changeEvent.getNewScene(), changeEvent.isDisposeCurrent());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void changeScreen(Screen newScene, boolean disposeOld) {
		if (currentScreen != null && newScene.getClass().equals(currentScreen.getClass()) && !disposeOld) {
			System.err.println("Change for the same Screen Class!");
			return;
		}

		exitCurrentScreenView(disposeOld);
		setCurrentScreen(newScene);
		createCurrentScreenView();
	}

	public void dispose() {
		exitCurrentScreenView(true);
	}

	public void resize(int width, int height) {
		currentScreen.resize(width, height);
	}

	public void pause() {
		currentScreen.pause();
	}

	public void resume() {
		currentScreen.resume();
	}

	public Screen getCurrentScreen() {
		return currentScreen;
	}

	public void setCurrentScreen(Screen newScene) {
		if (activeOldScreens.containsKey(newScene.getClass())) {
			newScene = activeOldScreens.get(newScene.getClass());
			activeOldScreens.remove(newScene.getClass());
		}
		this.currentScreen = newScene;
	}
}