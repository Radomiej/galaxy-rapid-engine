package galaxy.rapid.screen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import galaxy.rapid.ui.RapidStage;

public abstract class SelectableRapidScreen extends RapidScreen {

	private RapidStage stage;
	private Button buttonBack, buttonPlay;

	private HorizontalGroup selectRow;
	private Set<Button> selectableItems;
	private Set<Button> selectedItems;
	private Map<Button, Stack> itemContainerMap;

	@Override
	protected void create() {
		selectableItems = new HashSet<Button>();
		selectedItems = new HashSet<Button>();
		itemContainerMap = new HashMap<Button, Stack>();
		build();
		setup();
	}

	private void setup() {
		addSelectableItems(selectableItems);
		for (final Button b : selectableItems) {
			addActorRepresentation(b);
			
			if (b instanceof VisCheckBox) {
				checkBoxSelectStrategy((VisCheckBox) b);
			} else {
				defaultSelectStrategy(b);
			}

		}
	}

	private void checkBoxSelectStrategy(final VisCheckBox b) {
		if(b.isChecked()){
			selectedItems.add(b);
		}
		
		b.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Kliknito checkBoxa");
				if(b.isChecked()){
					selectedItems.add(b);
				}
				else{
					selectedItems.remove(b);
				}
			}
		});
	}

	private void defaultSelectStrategy(final Button b) {
		
		b.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (selectedItems.contains(b)) {
					selectableItems.remove(b);
				} else {
					selectableItems.add(b);
				}
			}
		});
	}

	private void addActorRepresentation(final Button b) {
		Stack stack = new Stack();
		stack.add(b);
		itemContainerMap.put(b, stack);
		selectRow.addActor(stack);
	}

	private void build() {
		stage = new RapidStage();

		selectRow = new HorizontalGroup();
		ScrollPane scrollPane = new ScrollPane(selectRow);
		Table buttonRow = new VisTable(true);

		stage.setDebug(true);
		stage.add(scrollPane).expand(1, 8);
		stage.getTable().row();
		stage.add(buttonRow).expand(1, 1).fill();

		buttonBack = new VisTextButton("Back");
		buttonPlay = new VisTextButton("Play");
		buttonRow.add(buttonBack).expand().fill();
		buttonRow.add(buttonPlay).expand().fill();
		buttonRow.row();
		buttonBack.left();
		buttonPlay.right();
	}

	protected abstract void addSelectableItems(Set<Button> selectableItems2);

	public void render(float delta) {
		stage.draw();
		stage.act(delta);

		if (buttonPlay.isPressed()) {
			nextButtonPressed(selectedItems);
		} else if (buttonBack.isPressed()) {
			backButtonPressed();
		}
	}

	protected abstract void backButtonPressed();

	protected abstract void nextButtonPressed(Set<Button> selectedItems);

	public void dispose() {
		stage.dispose();
	}

}
