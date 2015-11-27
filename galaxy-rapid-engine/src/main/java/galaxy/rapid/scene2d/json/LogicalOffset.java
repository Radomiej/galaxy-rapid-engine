package galaxy.rapid.scene2d.json;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public enum LogicalOffset {
	BOTTOM{

		@Override
		public void procces(Actor actor) {
			if(!(actor instanceof Table)) return;
			Table table = (Table) actor;
			table.bottom();
		}

		@Override
		public void procces(Cell<Actor> cell) {
			cell.bottom();			
		}
		
	}, TOP{

		@Override
		public void procces(Actor actor) {
			if(!(actor instanceof Table)) return;
			Table table = (Table) actor;
			table.top();			
		}

		@Override
		public void procces(Cell<Actor> cell) {
			cell.top();			
		}
		
	}, LEFT{

		@Override
		public void procces(Actor actor) {
			if(!(actor instanceof Table)) return;
			Table table = (Table) actor;
			table.left();			
		}

		@Override
		public void procces(Cell<Actor> cell) {
			cell.left();
		}
		
	}, RIGHT{

		@Override
		public void procces(Actor actor) {
			if(!(actor instanceof Table)) return;
			Table table = (Table) actor;
			table.right();			
		}

		@Override
		public void procces(Cell<Actor> cell) {
			cell.right();
		}
		
	};
	
	public abstract void procces(Actor actor);
	public abstract void procces(Cell<Actor> cell);
}
