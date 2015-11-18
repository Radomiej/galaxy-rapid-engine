package galaxy.rapid.multiplayer.server;

import com.artemis.Entity;

public interface SynchronizedStrategy {
	public void sendEntity(Entity e);
	public void sendRemoveEntity(Entity e);
}
