package galaxy.rapid.scene2d.json;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import galaxy.rapid.ui.RapidStage;

public enum ComponentType {
	TABLE {
		@Override
		public Actor createActor(JsonNode node) {
			return new VisTable(true);
		}

		@Override
		public void addActor(Actor actor, Actor children, JsonNode childrenNode) {
			System.out.println("Dodaje aktora do tabeli");
			Table table = (Table) actor;
			if(children == null){
				table.row();
				return;
			}			
			table.add(children);			
		}
	}, LABEL {
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
	public abstract void addActor(Actor parent, Actor children, JsonNode childrenNode);
}
