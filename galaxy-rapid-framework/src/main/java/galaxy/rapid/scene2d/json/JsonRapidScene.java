package galaxy.rapid.scene2d.json;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class JsonRapidScene {

	private JsonMaster master;
	private JsonNodesRoot root;
	private Stage stage;

	public JsonRapidScene(JsonNodesRoot root) {
		stage = new Stage();
		master = new JsonMaster();
		this.root = root;
		createActors();
		buildStageActors();
		linkWithStage();
	}

	private void linkWithStage() {
		String parentTableName = root.getParentTable();
		Actor parentTable = master.getActors().get(parentTableName);
		Table table = (Table) parentTable;
		table.setFillParent(true);
		stage.addActor(table);
	}

	private void buildStageActors() {
		for(JsonNode parent : root.getNodes()){
			Actor parenActor = master.getActors().get(parent.name);	
			ComponentType componentType = parent.type;
			List<JsonNode> childrens = master.getChildrens(parent);
			for(JsonNode child : childrens){
				Actor actor = master.getActors().get(child.name);
				componentType.addActor(parenActor, actor, child);				
			}
		}
	}

	private void createActors() {
		for (JsonNode node : root.getNodes()) {
			ComponentType componentType = node.type;
			Actor actor = componentType.createActor(node);
			master.put(node, actor);
		}
	}

	public Stage getStage() {
		return stage;
	}

}
