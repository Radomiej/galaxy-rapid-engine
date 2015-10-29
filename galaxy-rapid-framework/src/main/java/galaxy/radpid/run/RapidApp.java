package galaxy.radpid.run;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.kotcrab.vis.ui.VisUI;

import galaxy.radpid.configuration.RapidConfig;
import galaxy.radpid.configuration.RapidConfiguration;
import galaxy.rapid.screen.ScreenNavigator;

public class RapidApp extends ApplicationAdapter {

	private ScreenNavigator screenNavigator;
	private Screen startScreen;

	private String skinAssetName;
	private RapidConfig rapidConfig;

	public RapidApp(Screen startScreen) {
		this(startScreen, new RapidConfig());
	}

	@Deprecated
	public RapidApp(Screen startScreen, String skinName) {
		this.startScreen = startScreen;
		RapidConfig rapidConfig = new RapidConfig();
		rapidConfig.skinAsset = skinName;
		this.rapidConfig = rapidConfig;
	}
	
	public RapidApp(Screen startScreen, RapidConfig config) {
		this.startScreen = startScreen;
		this.rapidConfig = config;
	}

	@Override
	public void create() {
		
		if(RapidConfiguration.INSTANCE.load(rapidConfig)){
			Gdx.app.log("Configuration", "Apply configuration object");
		}
		
		if (skinAssetName != null){
			VisUI.load(Gdx.files.internal(skinAssetName));
		}
		else{
			VisUI.load();
		}
		if(rapidConfig.debugMode){
			Gdx.app.setLogLevel(Gdx.app.LOG_DEBUG);
		}
		
		System.out.println("after vis");
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		System.out.println("before ScreenNav");
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
