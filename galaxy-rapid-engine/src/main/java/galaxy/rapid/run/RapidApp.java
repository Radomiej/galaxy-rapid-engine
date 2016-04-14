package galaxy.rapid.run;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import galaxy.rapid.configuration.RapidConfig;
import galaxy.rapid.configuration.RapidConfiguration;
import galaxy.rapid.screen.ScreenNavigator;

public class RapidApp extends ApplicationAdapter {
	private ScreenNavigator screenNavigator;
	private Screen startScreen;

	public RapidApp(Screen startScreen) {		
		this.startScreen = startScreen;		
		
	}	

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		Gdx.app.debug(RapidApp.class.getSimpleName(), "Loading configuration...");
		RapidConfiguration.INSTANCE.load();
		RapidConfig rapidConfig = RapidConfiguration.INSTANCE.getRapidConfig();
		
		if(rapidConfig.debugMode){
			Gdx.app.setLogLevel(Gdx.app.LOG_DEBUG);
		}
		
		Gdx.app.debug(RapidApp.class.getSimpleName(), "Setting multiplexer...");
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		Gdx.app.debug(RapidApp.class.getSimpleName(), "Run first scene...");
		screenNavigator = new ScreenNavigator(startScreen);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
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
