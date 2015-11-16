package galaxy.rapid.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisWindow;

public class RapidStage {
	private Stage stage;
	private Table table;

	public RapidStage() {
		stage = new Stage(new ScreenViewport());
		InputMultiplexer multiplexer = (InputMultiplexer) Gdx.app.getInput().getInputProcessor();
		multiplexer.addProcessor((stage));
		table = new VisTable();		
		stage.addActor(table);
		table.setFillParent(true);
	}
	
	public Cell<Actor> add(Actor actor) {
		return table.add(actor);
	}

	public void removeActor(Actor actorToRemove) {
		table.removeActor(actorToRemove);
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void addActor(Actor actor) {
		table.addActor(actor);
	}

	public void act(float delta) {
		stage.act();
	}

	public void draw() {
		stage.draw();
	}
	
	public void resize(int width, int height){
		stage.getViewport().update(width, height, false);
	}

	public void dispose() {
		stage.dispose();
	}

	public void setDebug(boolean b) {
		stage.setDebugAll(b);
	}

	public void row() {
		table.row();
	}
}
