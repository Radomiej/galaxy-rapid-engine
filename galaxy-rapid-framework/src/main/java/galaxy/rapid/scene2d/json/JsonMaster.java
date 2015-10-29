package galaxy.rapid.scene2d.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class JsonMaster {
	private Map<String, JsonNode> nodes;
	private Map<String, Actor> actors;

	public JsonMaster() {
		nodes = new HashMap<String, JsonNode>();
		actors = new HashMap<String, Actor>();
	}

	public Map<String, JsonNode> getNodes() {
		return nodes;
	}

	public void setNodes(Map<String, JsonNode> nodes) {
		this.nodes = nodes;
	}

	public Map<String, Actor> getActors() {
		return actors;
	}

	public void setActors(Map<String, Actor> actors) {
		this.actors = actors;
	}

	public void put(JsonNode node, Actor actor) {
		String name = node.name;
		nodes.put(name, node);
		actors.put(name, actor);
	}

	public List<JsonNode> getChildrens(JsonNode parent) {
		List<JsonNode> childrens = new ArrayList<JsonNode>();
		for(String childName : parent.childrens){
			childrens.add(nodes.get(childName));
		}		
		return childrens;
	}
}
