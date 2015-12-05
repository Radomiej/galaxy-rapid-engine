package galaxy.rapid.components;

import java.nio.channels.NetworkChannel;
import java.util.UUID;

import com.artemis.Component;

import galaxy.rapid.components.marker.TransientNetworkElement;

public class TargetComponent extends Component implements TransientNetworkElement{
	private UUID target;

	public TargetComponent() {
	}
	public TargetComponent(UUID uuidFromEntity) {
		target = uuidFromEntity;
	}

	public UUID getTarget() {
		return target;
	}

	public void setTarget(UUID target) {
		this.target = target;
	}
}
