package galaxy.rapid.scene2d.json;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Value;

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
	public float value;
	
	//Pozycjonowanie
	public JsonAlign align = JsonAlign.CENTER;
	public int colspan = -1;
	public int expandX = 0;
	public int expandY = 0;
	public float fillX = -1;
	public float fillY = -1;
	
	public float padTop = -1;
	public float padLeft = -1;
	public float padBottom = -1;
	public float padRight = -1;
	public float spaceTop = -1;
	public float spaceLeft = -1;
	public float spaceBottom = -1;
	public float spaceRight = -1;
	
}
