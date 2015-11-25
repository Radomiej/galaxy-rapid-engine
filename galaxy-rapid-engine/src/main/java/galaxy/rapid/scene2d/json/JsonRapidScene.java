package galaxy.rapid.scene2d.json;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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
		for (JsonNode parent : root.getNodes()) {
			Actor parenActor = master.getActors().get(parent.name);
			ComponentType componentType = parent.type;
			List<JsonNode> childrens = master.getChildrens(parent);
			for (JsonNode child : childrens) {
				if (child != null) System.out.println("Dodaje do: " + parent.name + " dziecko: " + child.name);
				else  System.out.println("Dodaje do: " + parent.name + " dziecko: " + "ROW");
				
				Actor actor = null;
				if (child != null){
					actor = master.getActors().get(child.name);
				}
				componentType.addActor(parenActor, actor, child);
			}
		}
	}

	private void createActors() {
		for (JsonNode node : root.getNodes()) {
			node.name = node.name.trim();
			node.name = node.name.replaceAll("\t", "");
			node.name = node.name.replaceAll("\n", "");
			node.name = node.name.replaceAll(" ", "");
			System.out.println("Tworze obiekt: " + node.name);
			if(master.getNodes().containsValue(node)) {
				System.err.println("Wykryto powtorzenie sie obiektu: " + node.name);
			}
			for(String children : node.childrens){
				System.out.println("dziecko: " + children);
			}
			
			ComponentType componentType = node.type;
			Actor actor = componentType.createActor(node);			
			master.put(node, actor);
			
		}
	}

	public Stage getStage() {
		return stage;
	}

	public Actor getActor(String string) {
		return master.getActors().get(string);
	}

}
