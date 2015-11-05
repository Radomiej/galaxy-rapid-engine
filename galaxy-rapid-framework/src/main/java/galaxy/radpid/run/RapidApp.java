package galaxy.radpid.run;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Json;
import com.kotcrab.vis.ui.VisUI;

import galaxy.radpid.configuration.RapidConfig;
import galaxy.radpid.configuration.RapidConfiguration;
import galaxy.rapid.screen.ScreenNavigator;

public class RapidApp extends ApplicationAdapter {

	private ScreenNavigator screenNavigator;
	private Screen startScreen;

	public RapidApp(Screen startScreen) {		
		this.startScreen = startScreen;		
		
	}	

	@Override
	public void create() {
		RapidConfiguration.INSTANCE.load();
		RapidConfig rapidConfig = RapidConfiguration.INSTANCE.getRapidConfig();		
		if (rapidConfig.skinAsset != null){
			VisUI.load(Gdx.files.internal(rapidConfig.skinAsset));
		}
		else{
			VisUI.load();
		}
		if(rapidConfig.debugMode){
			Gdx.app.setLogLevel(Gdx.app.LOG_DEBUG);
		}
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
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
