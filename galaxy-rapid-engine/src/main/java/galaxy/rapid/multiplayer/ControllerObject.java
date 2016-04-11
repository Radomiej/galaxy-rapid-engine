package galaxy.rapid.multiplayer;

import galaxy.rapid.components.KeyboardComponent;
import galaxy.rapid.network.server.PartUuid;

public class ControllerObject {
	private KeyboardComponent keyboardComponent;
	private PartUuid partUuid;

	public KeyboardComponent getKeyboardComponent() {
		return keyboardComponent;
	}

	public void setKeyboardComponent(KeyboardComponent keyboardComponent) {
		this.keyboardComponent = keyboardComponent;
	}

	public PartUuid getPartUuid() {
		return partUuid;
	}

	public void setPartUuid(PartUuid partUuid) {
		this.partUuid = partUuid;
	}
}
