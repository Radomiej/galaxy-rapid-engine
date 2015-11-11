package galaxy.rapid.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import galaxy.rapid.scene2d.ScrollableTable;
import galaxy.rapid.ui.RapidStage;

public abstract class LeaderboardResultRapidScreen extends RapidScreen {

	private RapidStage stage;
	private Button buttonNext, buttonReplay;

	@Override
	protected void create() {
		stage = new RapidStage();
		stage.setDebug(true);
		buttonNext = new VisTextButton("Next");
		buttonReplay = new VisTextButton("Replay");
		buildLayout();
	}

	private void buildLayout() {
		Table upRow = new VisTable();
		stage.add(upRow).top().expand(1, 1).fill();
		stage.row();
		Label resultName = getResultTitle();
		upRow.add(resultName).center();
		
		Table downRow = new VisTable();
		stage.add(downRow).bottom().expand(1, 8).fill();
		
		//Leaderboard
		ScrollableTable scrollableTable = new ScrollableTable();
		downRow.add(scrollableTable.getActor()).left().expand(3, 1).fill();		
		fillLeaderboard(scrollableTable);	
		
		//Buttons		
		Table buttonsGroup = new VisTable(true);
		buttonsGroup.add(buttonReplay).bottom().fill().expand().pad(10);
		buttonsGroup.row();
		buttonsGroup.add(buttonNext).bottom().fill().expand().pad(10);
		buttonNext.pad(20);
		buttonReplay.pad(20);
		
		downRow.add(buttonsGroup).right().bottom().expand().fillX().padBottom(20).padLeft(50).padRight(50);
		
		
	}

	protected abstract void fillLeaderboard(ScrollableTable scrollableTable2);
	protected abstract Label getResultTitle();

	public void render(float delta) {
		stage.act(delta);
		stage.draw();
		
		if (buttonNext.isPressed()) {
			nextButtonPressed();
		} else if (buttonReplay.isPressed()) {
			replayButtonPressed();
		}
	}

	protected abstract void replayButtonPressed();
	protected abstract void nextButtonPressed();

	public void dispose() {

	}

}
