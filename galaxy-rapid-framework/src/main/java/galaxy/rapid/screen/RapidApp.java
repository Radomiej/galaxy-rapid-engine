package galaxy.rapid.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.kotcrab.vis.ui.VisUI;

public class RapidApp extends ApplicationAdapter {

	private ScreenNavigator screenNavigator;
	private Screen startScreen;

	private String skinAssetName;

	public RapidApp(Screen startScreen) {
		this(startScreen, null);
	}

	public RapidApp(Screen startScreen, String skinName) {
		this.startScreen = startScreen;
		this.skinAssetName = skinName;
	}

	@Override
	public void create() {
		if (skinAssetName != null)
			VisUI.load(Gdx.files.internal(skinAssetName));
		else
			VisUI.load();

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);

		screenNavigator = new ScreenNavigator(startScreen);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screenNavigator.updateCurrentScreen(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		screenNavigator.resize(width, height);
	}

	@Override
	public void dispose() {
		screenNavigator.dispose();
		VisUI.dispose();
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
