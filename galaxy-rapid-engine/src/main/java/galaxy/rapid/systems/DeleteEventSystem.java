package galaxy.rapid.systems;

import com.artemis.annotations.Wire;

import galaxy.rapid.event.RemoveEntityEvent;
import galaxy.rapid.eventbus.RapidBus;
import net.mostlyoriginal.api.event.common.EventSystem;
import net.mostlyoriginal.api.event.common.Subscribe;

public class DeleteEventSystem extends PassiveSystem {

	@Wire
	private RapidBus eventSystem;
	
	@Override
	protected void initialize() {
		eventSystem.register(this);
	}
	
	@Subscribe(priority=-1000)
	public void removeEventListener(RemoveEntityEvent removeEntity){
		world.deleteEntity(removeEntity.getRemoveEntity());
	}
	
}
