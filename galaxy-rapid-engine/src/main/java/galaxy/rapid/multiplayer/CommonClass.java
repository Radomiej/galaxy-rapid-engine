package galaxy.rapid.multiplayer;

import galaxy.rapid.common.ComponentsBag;
import galaxy.rapid.common.RenderOffset;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.components.Box2dComponent;
import galaxy.rapid.components.KeyboardComponent;
import galaxy.rapid.components.LiveComponent;
import galaxy.rapid.components.PlayerComponent;
import galaxy.rapid.components.PositionComponent;
import galaxy.rapid.components.RenderComponent;
import galaxy.rapid.components.SpriteComponent;

public enum CommonClass {
	INSTANCE;

	public Class[] getCommonsTab() {
		Class[] classes = { JsonGameComponent.class, SpriteComponent.class, RenderComponent.class, BodyComponent.class,
				KeyboardComponent.class, Box2dComponent.class, ComponentsBag.class, RenderOffset.class,
				PlayerComponent.class, PartUuid.class, ControllerObject.class, PositionComponent.class,
				LiveComponent.class};
		return classes;
	}
}
