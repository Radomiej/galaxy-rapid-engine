package galaxy.rapid.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.kotcrab.vis.ui.widget.VisTable;

public class ScrollableTable {

	private Table scrollContent;
	private ScrollPane scrollable;	
	
	public ScrollableTable() {
		scrollContent = new VisTable();
		scrollable = new ScrollPane(scrollContent);	
		scrollContent.top();
	}
	public Actor getActor() {
		return scrollable;
	}
	public void addLabel(Label label) {
		scrollContent.add(label).fillX();
		scrollContent.row();
	}

}
