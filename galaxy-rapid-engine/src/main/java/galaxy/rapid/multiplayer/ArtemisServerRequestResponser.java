package galaxy.rapid.multiplayer;

import java.util.UUID;

import galaxy.rapid.common.EntityEngine;

public interface ArtemisServerRequestResponser {
	public UUID proccesNewPlayerJoin(EntityEngine world);
}
