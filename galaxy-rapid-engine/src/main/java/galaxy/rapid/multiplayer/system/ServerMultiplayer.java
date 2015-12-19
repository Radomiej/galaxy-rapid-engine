package galaxy.rapid.multiplayer.system;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

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
import galaxy.rapid.multiplayer.CommonClass;
import galaxy.rapid.multiplayer.EntityHelper;
import galaxy.rapid.multiplayer.JsonGameComponent;
import galaxy.rapid.multiplayer.PartUuid;
import galaxy.rapid.multiplayer.server.HashSynchronizedStrategy;
import galaxy.rapid.multiplayer.server.SimpleSynchronizedStrategy;
import galaxy.rapid.multiplayer.server.SynchronizedStrategy;
import galaxy.rapid.network.server.Room;
import galaxy.rapid.network.service.RapidClient;
import net.mostlyoriginal.api.event.common.Subscribe;
import pl.silver.JGNL.JGNLServer;
import pl.silver.JGNL.Network;
import pl.silver.JGNL.request.RequestReciver;

public class ServerMultiplayer extends IntervalEntityProcessingSystem {

	@Wire
	private RapidBus eventSystem;
	private UuidEntityManager uuidManager;

	private SynchronizedStrategy synchronizedStrategy;
	private ArtemisServerRequestResponser createPlayerListener;
	
	private AtomicBoolean sendFullEntity = new AtomicBoolean(false);
	private AtomicBoolean internalSendFullEntity = new AtomicBoolean(false);
	private Room gameRoom;
	
	
	private ServerMultiplayer() {
		super(Aspect.all(BodyComponent.class), 1000 / 30f);
	}

	
	public ServerMultiplayer(Room gameRoom, final ArtemisServerRequestResponser createPlayerReciver) {
		this();
		this.gameRoom = gameRoom;
		this.createPlayerListener = createPlayerListener;
		synchronizedStrategy = new HashSynchronizedStrategy(gameRoom);
	}

	@Override
	protected void initialize() {
		Log.set(Log.LEVEL_ERROR);
		eventSystem.register(this);
	}

	@Override
	protected void begin() {
		if (sendFullEntity.compareAndSet(true, false)) {
			internalSendFullEntity.set(true);
		}
	}
	@Override
	protected void process(Entity e) {
		if (internalSendFullEntity.get()) {
			synchronizedStrategy.sendFullEntity(e);
		} else {
			synchronizedStrategy.sendEntity(e);
		}
	}

	@Override
	protected void end() {
		internalSendFullEntity.compareAndSet(true, false);
	}
	@Subscribe
	public void removed(RemoveEntityEvent event) {
		synchronizedStrategy.sendRemoveEntity(event.getRemoveEntity());
	}

	public PartUuid addClient(RapidClient client, Object clientCreateData) {
		UUID uuid = createPlayerListener.proccesNewPlayerJoin((EntityEngine) world, clientCreateData);
		System.out.println("Send uuid: " + uuid);
		sendFullEntity.set(true);
		return new PartUuid(uuid);
	}
}
