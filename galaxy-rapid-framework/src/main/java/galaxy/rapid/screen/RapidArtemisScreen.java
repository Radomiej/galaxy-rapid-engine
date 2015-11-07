package galaxy.rapid.screen;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import galaxy.radpid.configuration.RapidConfiguration;
import galaxy.rapid.managers.RenderableManager;
import galaxy.rapid.systems.RenderSystem;
import galaxy.rapid.systems.TickEventSystem;

public abstract class RapidArtemisScreen extends RapidScreen {

	public static final float MIN_DELTA = 1 / 15f;

	protected World world;
	protected InputMultiplexer inputMultiplexer;
	protected OrthographicCamera camera;
	protected PolygonSpriteBatch spriteBatch;

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
		spriteBatch = new PolygonSpriteBatch();

		WorldConfiguration worldConfiguration = new WorldConfiguration();	
		processBeforeWorldConfiguration(worldConfiguration);		
		worldConfiguration.setSystem(GroupManager.class);	
		worldConfiguration.setSystem(RenderableManager.class);	
		worldConfiguration.setSystem(TickEventSystem.class);
		worldConfiguration.setSystem(new RenderSystem());
		worldConfiguration.register(camera);
		worldConfiguration.register(inputMultiplexer);
		worldConfiguration.register(spriteBatch);
		worldConfiguration.register(eventBus);		
		processWorldConfiguration(worldConfiguration);
		
		
		world = new World(worldConfiguration);
		injectWorld(world);
	}	

	protected void processBeforeWorldConfiguration(WorldConfiguration worldConfiguration) {
	}

	protected abstract void processWorldConfiguration(WorldConfiguration worldConfiguration);

	protected abstract void injectWorld(World world);

}
