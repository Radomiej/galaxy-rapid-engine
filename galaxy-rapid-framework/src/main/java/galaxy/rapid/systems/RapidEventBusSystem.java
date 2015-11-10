package galaxy.rapid.systems;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;

import galaxy.rapid.eventbus.RapidBus;

public class RapidEventBusSystem extends BaseSystem {

	@Wire(failOnNull=false)
	private RapidBus eventBus;
	
	@Override
	protected void processSystem() {
		if(eventBus != null){
			eventBus.process();
		}
	}

}
