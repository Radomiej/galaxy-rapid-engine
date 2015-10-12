package galaxy.rapid.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.common.eventbus.EventBus;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

public abstract class RapidLoadingScreen implements Screen, EventBusInjector {
	private boolean initialize = false;
	private Actor actor;
	private EventBus eventBus;
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
		build();
		setup(eventBus);
	}

	private void build() {
		stage = new Stage(new ScreenViewport());
		InputMultiplexer multiplexer = (InputMultiplexer) Gdx.app.getInput().getInputProcessor();
		multiplexer.addProcessor((stage));
		table = new VisTable();
	}

	private void setup(EventBus eventBus) {
		table.setPosition(stage.getViewport().getScreenWidth() / 2 - table.getWidth() / 2,
				stage.getViewport().getScreenHeight() / 2 - table.getHeight() / 2);
		stage.addActor(table);

		VisLabel label = new VisLabel("Loading...");
		table.add(label);
	}

	protected abstract void initialize();

	protected abstract Actor getProgressActor();
	
	protected abstract Screen getNestScreen();

	protected abstract boolean updateProgressInActor();

	public void render(float delta) {
		if (updateProgressInActor()) {
			changeScene();
		}

		stage.act(delta);
		stage.draw();
	}

	private void changeScene() {

		ChangeScreenEvent changeSceneEvent = new ChangeScreenEvent(nextScene);
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
	}

	public void injectEventBus(EventBus globalEventBus) {
		this.eventBus = eventBus;
	}

}
