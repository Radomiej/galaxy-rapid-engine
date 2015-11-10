package galaxy.rapid.systems;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.artemis.utils.EntityBuilder;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import galaxy.rapid.event.CreateEntityEvent;

public class CreateEntitySystem extends BaseSystem {

	@Wire
	private EventBus eventBus;
	
	@Override
	protected void initialize() {
		eventBus.register(this);
	}
	
	@Override
	protected void processSystem() {
		
	}
	
	@Override
	protected void dispose() {
		eventBus.unregister(this);
	}
	
	@Subscribe
	public void entityCreateEventListener(CreateEntityEvent createEntityEvent){		
		new EntityBuilder(world).with(createEntityEvent.getComponentsBag().getComponentsTab()).build();
	}

}
