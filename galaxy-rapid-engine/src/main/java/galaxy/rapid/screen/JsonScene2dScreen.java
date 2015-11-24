package galaxy.rapid.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;

import galaxy.rapid.configuration.RapidConfiguration;
import galaxy.rapid.scene2d.json.JsonNodesRoot;
import galaxy.rapid.scene2d.json.JsonRapidScene;

public abstract class JsonScene2dScreen extends RapidScreen {

	private String assetWithStage;
	private Stage stage;
	private Json json = new Json();
	private JsonRapidScene jsonScene;
	
	public JsonScene2dScreen(String assetStage) {
		assetWithStage = assetStage;
	}

	@Override
	public void render(float delta) {
			stage.act(delta);
			stage.draw();
	}

	@Override
	public void dispose() {
		InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
		inputMultiplexer.removeProcessor(inputMultiplexer);
		stage.dispose();
	}

	@Override
	protected void create() {
		FileHandle stageFile = Gdx.files.internal("scene2d/" + assetWithStage + ".scene");
		JsonNodesRoot root = new JsonNodesRoot();
		root = json.fromJson(JsonNodesRoot.class, stageFile);
		jsonScene = new JsonRapidScene(root);
		stage = jsonScene.getStage();
		stage.setDebugAll(RapidConfiguration.INSTANCE.isDebugMode());
		
		InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
		inputMultiplexer.addProcessor(stage);
		
		addUiLogic(jsonScene);
	}
	
	protected abstract void addUiLogic(JsonRapidScene jsonScene);
}
