package galaxy.rapid.screen;

import com.google.common.eventbus.EventBus;

public interface EventBusInjector {
	public void injectEventBus(EventBus globalEventBus);
}
