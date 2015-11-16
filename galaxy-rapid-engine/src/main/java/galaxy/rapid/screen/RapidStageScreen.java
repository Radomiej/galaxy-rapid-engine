package galaxy.rapid.screen;

import galaxy.rapid.ui.RapidStage;

public abstract class RapidStageScreen extends RapidScreen {

	protected RapidStage stage;

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	protected void create() {
		stage = new RapidStage();
		createStage(stage);
	}
	
	protected abstract void createStage(RapidStage internalStage);

}
