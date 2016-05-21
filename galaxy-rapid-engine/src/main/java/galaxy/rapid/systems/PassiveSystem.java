package galaxy.rapid.systems;

import com.artemis.BaseSystem;

/**
 * Headless system.
 *
 * For tickless secondary systems that service primary systems.
 *
 * @author Daan van Yperen
 */
public class PassiveSystem extends BaseSystem {

    @Override
    protected void initialize() {
    	setEnabled(false);
    }
    
    @Override
    protected void processSystem() {
	    // do nothing!
    }
}