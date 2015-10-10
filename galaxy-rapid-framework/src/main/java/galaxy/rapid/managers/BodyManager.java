package galaxy.rapid.managers;

import java.util.Set;

import com.artemis.Aspect;
import com.artemis.AspectSubscriptionManager;
import com.artemis.Entity;
import com.artemis.EntitySubscription;
import com.artemis.Manager;

import galaxy.rapid.common.Mapper;
import galaxy.rapid.components.BodyComponent;

public class BodyManager extends Manager{
	private AspectSubscriptionManager asm;
	private EntitySubscription bodySubscription;
	private Set<Entity> entities;
	
	@Override
	protected void initialize() {
		super.initialize();
		bodySubscription = asm.get(Aspect.all(BodyComponent.class));		
	}
	
	public void updateList(){
		entities = Mapper.getEntitiesFromBag(bodySubscription.getEntities(), world);
	}

	public Set<Entity> getBuffs(){
		updateList();
		return entities;
	}
}
