package galaxy.rapid.scene2d.json;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisProgressBar;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextArea;
import com.kotcrab.vis.ui.widget.VisTextButton;

import galaxy.rapid.ui.RapidStage;

public enum ComponentType {
	TABLE {
		@Override
		public Actor createActor(JsonNode node) {
			return new VisTable(true);
		}

		@Override
		public void addActor(Actor actor, Actor children, JsonNode childrenNode) {
//			System.out.println("Dodaje aktora do tabeli");
			Table table = (Table) actor;
			if (children == null) {	
				table.row();
				return;
			}
			Cell<Actor> cell = table.add(children);
			celling(cell, childrenNode);
		}
	},
	TEXT_AREA {
		@Override
		public Actor createActor(JsonNode node) {
			return new VisTextArea(node.text);
		}

		@Override
		public void addActor(Actor actor, Actor children, JsonNode childrenNode) {
			throw new NotAllowedOperation("Cannot add actor to this component: " + this.name());
		}
	},
	BUTTON {
		@Override
		public Actor createActor(JsonNode node) {
//			System.out.println("Tworze: " + this.name() + " text: " + node.text);
			return new VisTextButton(node.text);
		}

		@Override
		public void addActor(Actor actor, Actor children, JsonNode childrenNode) {
			throw new NotAllowedOperation("Cannot add actor to this component: " + this.name());
		}
	},
	PROGRESS_BAR {
		@Override
		public Actor createActor(JsonNode node) {
			VisProgressBar progressBar = new VisProgressBar(node.min, node.max, node.step, node.vertical);
			progressBar.setValue(node.value);
			return progressBar;
		}

		@Override
		public void addActor(Actor actor, Actor children, JsonNode childrenNode) {
			throw new NotAllowedOperation("Cannot add actor to this component: " + this.name());
		}
	},
	CHECKBOX {
		@Override
		public Actor createActor(JsonNode node) {
			return new VisCheckBox(node.text, node.checked);
		}

		@Override
		public void addActor(Actor actor, Actor children, JsonNode childrenNode) {
			throw new NotAllowedOperation("Cannot add actor to this component: " + this.name());
		}
	},
	LABEL {
		@Override
		public Actor createActor(JsonNode node) {
			return new VisLabel(node.text);
		}

		@Override
		public void addActor(Actor actor, Actor children, JsonNode childrenNode) {
			throw new NotAllowedOperation("Cannot add actor to this component: " + this.name());
		}
	};

	public abstract Actor createActor(JsonNode node);

	protected void celling(Cell<Actor> cell, JsonNode childrenNode) {
		if(childrenNode.colspan >= 0){
			cell.colspan(childrenNode.colspan);
		}
		if(childrenNode.expandX > 0 || childrenNode.expandY > 0){
			cell.expand(childrenNode.expandX, childrenNode.expandY);
		}
		if(childrenNode.fillX > 0 || childrenNode.fillY > 0){
			cell.fill(childrenNode.fillX, childrenNode.fillY);
		}
	}

	public abstract void addActor(Actor parent, Actor children, JsonNode childrenNode);
}
