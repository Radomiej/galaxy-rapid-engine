package galaxy.rapid.screen;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class RapidArtemisScreen extends RapidScreen{

	protected World world;
	private InputMultiplexer inputMultiplexer;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	
	public void render(float delta) {		
		world.process();
	}

	public void dispose() {
		world.dispose();
	}

	@Override
	protected void create() {		
		WorldConfiguration worldConfiguration = new WorldConfiguration();
		processWorldConfiguration(worldConfiguration);		
		
		camera = new OrthographicCamera();
		inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
		spriteBatch = new SpriteBatch();
		
		worldConfiguration.register(camera);
		worldConfiguration.register(inputMultiplexer);
		worldConfiguration.register(spriteBatch);
		
		world = new World(worldConfiguration);
		processWorld(world);
	}
	
	protected abstract void processWorldConfiguration(WorldConfiguration worldConfiguration);
	protected abstract void processWorld(World world);
	
}
