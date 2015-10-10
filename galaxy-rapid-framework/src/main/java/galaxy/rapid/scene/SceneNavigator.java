package galaxy.rapid.scene;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.badlogic.gdx.Screen;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class SceneNavigator {

	private Screen currentScene;
	private Map<Class<? extends Screen>, Screen> activeOldScenes;
	private EventBus eventBus = new EventBus("SceneNavigator");

	public SceneNavigator(Screen startScreen) {
		eventBus.register(this);
		activeOldScenes = new HashMap<Class<? extends Screen>, Screen>();
		changeScene(startScreen, false);
	}

	public void updateCurrentScene(float delta) {
		currentScene.render(delta);
	}

	private void createCurrentSceneView() {
		currentScene.show();
	}

	private void exitCurrentSceneView(boolean dispose) {
		if (currentScene != null) {
			currentScene.hide();
			if(dispose) currentScene.dispose();
			else activeOldScenes.put(currentScene.getClass(), currentScene);
		}
	}

	@Subscribe
	public void changeScene(ChangeSceneEvent changeEvent) {
		try {
			changeScene(changeEvent.getNewScene(), changeEvent.isDisposeCurrent());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void changeScene(Screen newScene, boolean disposeOld) {
		if(currentScene != null && newScene.getClass().equals(currentScene.getClass()) && !disposeOld){
			System.err.println("Change for the same Screen Class!");
			return;
		}
		
		exitCurrentSceneView(disposeOld);
		setCurrentScene(newScene);
		createCurrentSceneView();
	}

	public void dispose() {
		exitCurrentSceneView(true);
	}

	public void resize(int width, int height) {
		currentScene.resize(width, height);
	}

	public void pause() {
		currentScene.pause();
	}

	public void resume() {
		currentScene.resume();
	}

	public Screen getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(Screen newScene) {
		if(activeOldScenes.containsKey(newScene.getClass())){
			newScene = activeOldScenes.get(newScene.getClass());
			activeOldScenes.remove(newScene.getClass());
		}
		this.currentScene = newScene;
	}
}
