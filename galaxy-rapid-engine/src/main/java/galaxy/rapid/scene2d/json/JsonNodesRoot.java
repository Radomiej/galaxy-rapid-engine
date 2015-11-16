package galaxy.rapid.scene2d.json;

import java.util.ArrayList;
import java.util.List;

public class JsonNodesRoot {
	private List<JsonNode> nodes = new ArrayList<JsonNode>();
	private String parentTable;

	public void addNode(JsonNode node) {
		nodes.add(node);
	}

	public List<JsonNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<JsonNode> nodes) {
		this.nodes = nodes;
	}

	public String getParentTable() {
		return parentTable;
	}

	public void setParentTable(String parentTable) {
		this.parentTable = parentTable;
	}
}
