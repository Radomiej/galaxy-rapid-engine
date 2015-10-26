package galaxy.rapid.screen;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import galaxy.radpid.configuration.RapidConfiguration;
import galaxy.rapid.systems.TickEventSystem;

public abstract class RapidArtemisScreen extends RapidScreen {

	public static final float MIN_DELTA = 1 / 15f;

	protected World world;
	protected InputMultiplexer inputMultiplexer;
	protected OrthographicCamera camera;
	protected SpriteBatch spriteBatch;

	public void render(float delta) {
		world.setDelta(MathUtils.clamp(delta, 0, MIN_DELTA));
		world.process();
	}

	public void dispose() {
		world.dispose();
	}

	@Override
	protected void create() {
		

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		float realW = 1024;		
//		camera = new OrthographicCamera(w, w * (h / w));
		
		camera = new OrthographicCamera(realW, realW * (h / w));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
		spriteBatch = new SpriteBatch();

		WorldConfiguration worldConfiguration = new WorldConfiguration();		
		worldConfiguration.setSystem(GroupManager.class);				
		worldConfiguration.register(camera);
		worldConfiguration.register(inputMultiplexer);
		worldConfiguration.register(spriteBatch);
		worldConfiguration.register(eventBus);		
		processWorldConfiguration(worldConfiguration);
		worldConfiguration.setSystem(TickEventSystem.class);
		
		world = new World(worldConfiguration);
		injectWorld(world);
	}	

	protected abstract void processWorldConfiguration(WorldConfiguration worldConfiguration);

	protected abstract void injectWorld(World world);

}
