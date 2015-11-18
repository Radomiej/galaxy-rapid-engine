package galaxy.rapid.multiplayer.system;

import java.util.UUID;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.managers.UuidEntityManager;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.badlogic.gdx.utils.Json;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;

import galaxy.rapid.common.ComponentsBag;
import galaxy.rapid.common.EntityEngine;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.event.RemoveEntityEvent;
import galaxy.rapid.eventbus.RapidBus;
import galaxy.rapid.multiplayer.ArtemisServerRequestResponser;
import galaxy.rapid.multiplayer.EntityHelper;
import galaxy.rapid.multiplayer.JsonGameComponent;
import galaxy.rapid.multiplayer.PartUuid;
import galaxy.rapid.multiplayer.server.HashSynchronizedStrategy;
import galaxy.rapid.multiplayer.server.SimpleSynchronizedStrategy;
import galaxy.rapid.multiplayer.server.SynchronizedStrategy;
import net.mostlyoriginal.api.event.common.Subscribe;
import pl.silver.JGNL.JGNLServer;
import pl.silver.JGNL.Network;
import pl.silver.JGNL.request.RequestReciver;

public class ServerMultiplayer extends IntervalEntityProcessingSystem{
	
	@Wire
	private RapidBus eventSystem;
	private UuidEntityManager uuidManager;
	
	private JGNLServer server;
	private SynchronizedStrategy synchronizedStrategy;
	private RequestReciver requestReciver;
	
	
	private ServerMultiplayer() {
		super(Aspect.all(BodyComponent.class), 1000 / 60f);
		server = new JGNLServer(); 
		synchronizedStrategy = new HashSynchronizedStrategy(server);
	}
	
	public ServerMultiplayer(final ArtemisServerRequestResponser createPlayerReciver) {
		this();
		requestReciver = new RequestReciver() {
			@Override
			public Object recivedRequest(Connection connection, Object object) {
				 UUID uuid = createPlayerReciver.proccesNewPlayerJoin((EntityEngine) world);
				 System.out.println("Send uuid: " + uuid);
				 return new PartUuid(uuid);
			}
		};
	}

	
	
	@Override
	protected void initialize() {
		Log.set(Log.LEVEL_ERROR);
		new Json();
		server.setRequestReciver(requestReciver);
		server.start(Network.portTCP, Network.portUDP);
		eventSystem.register(this);
	}
	
	@Override
	protected void process(Entity e) {
		synchronizedStrategy.sendEntity(e);
	}

	@Subscribe
	public void removed(RemoveEntityEvent event) {
		System.out.println("Multiplayer Otrzymano zdazenie usuniecia entity");
		synchronizedStrategy.sendRemoveEntity(event.getRemoveEntity());
	}

	public JGNLServer getServer() {
		return server;
	}
}
