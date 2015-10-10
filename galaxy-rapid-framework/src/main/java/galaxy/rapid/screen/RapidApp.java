package galaxy.rapid.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class RapidApp extends ApplicationAdapter {

	private ScreenNavigator screenNavigator;
	private Screen startScreen;

	public RapidApp(Screen startScreen) {
		this.startScreen = startScreen;
	}

	@Override
	public void create() {
		screenNavigator = new ScreenNavigator(startScreen);
	}

	@Override
	public void render() {
		screenNavigator.updateCurrentScreen(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		screenNavigator.resize(width, height);
	}

	@Override
	public void dispose() {
		screenNavigator.dispose();
	}

	@Override
	public void pause() {
		screenNavigator.pause();
	}

	@Override
	public void resume() {
		screenNavigator.resume();
	}
}
