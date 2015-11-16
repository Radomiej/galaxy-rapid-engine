package galaxy.rapid.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import galaxy.rapid.eventbus.RapidBus;

public abstract class RapidLoadingScreen implements Screen, EventBusInjector {
	private boolean initialize = false;
	private Actor actor;
	private RapidBus eventBus;
	private Stage stage;
	private Table table;
	private Screen nextScene;

	public void show() {
		if (!initialize) {
			initialize = true;
			privateInitialize();
			initialize();
		}
	}

	private void privateInitialize() {
		actor = getProgressActor();
		nextScene = getNextScreen();
		build();
		setup(eventBus);
	}

	private void build() {
		stage = new Stage(new ScreenViewport());
		InputMultiplexer multiplexer = (InputMultiplexer) Gdx.app.getInput().getInputProcessor();
		multiplexer.addProcessor((stage));
		table = new VisTable();
	}

	private void setup(RapidBus eventBus) {
		table.setPosition(stage.getViewport().getScreenWidth() / 2 - table.getWidth() / 2,
				stage.getViewport().getScreenHeight() / 2 - table.getHeight() / 2);
		stage.addActor(table);
		table.add(actor);
	}

	protected abstract void initialize();

	protected abstract Actor getProgressActor();
	
	protected abstract Screen getNextScreen();

	protected abstract boolean updateProgressInActor();

	public void render(float delta) {
		if (updateProgressInActor()) {
			changeScene();
		}

		stage.act(delta);
		stage.draw();
	}

	private void changeScene() {

		ChangeScreenEvent changeSceneEvent = new ChangeScreenEvent(nextScene, true);
		eventBus.post(changeSceneEvent);

	}

	public void resize(int width, int height) {
		stage.getViewport().setScreenSize(width, height);
	}

	public void pause() {
	}

	public void resume() {
	}

	public void hide() {
	}

	public void dispose() {
		stage.dispose();
	}

	public void injectEventBus(RapidBus globalEventBus) {
		this.eventBus = globalEventBus;
	}

}
