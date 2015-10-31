package galaxy.rapid.scene2d.json;

import java.util.ArrayList;
import java.util.List;

public class JsonNode {

	public ComponentType type;
	public String name;
	public List<String> childrens = new ArrayList<String>();
	public String text;
	public boolean vertical;
	public float step = 1;
	public float max = 100;
	public float min = 0;
	public boolean checked;
	protected float value;
	
}
