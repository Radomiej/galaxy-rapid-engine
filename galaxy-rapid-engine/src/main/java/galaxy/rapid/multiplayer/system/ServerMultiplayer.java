package galaxy.rapid.multiplayer.system;

import java.util.Timer;
import java.util.TimerTask;
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
import galaxy.rapid.common.UuidHelper;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.event.RemoveEntityEvent;
import galaxy.rapid.eventbus.RapidBus;
import galaxy.rapid.multiplayer.ArtemisServerRequestResponser;
import galaxy.rapid.multiplayer.CommonClass;
import galaxy.rapid.multiplayer.EntityHelper;
import galaxy.rapid.multiplayer.JsonGameComponent;
import galaxy.rapid.multiplayer.server.HashSynchronizedStrategy;
import galaxy.rapid.multiplayer.server.SynchronizedStrategy;
import galaxy.rapid.network.server.PartUuid;
import galaxy.rapid.network.service.NetworkGroup;
import galaxy.rapid.network.service.ServerClient;
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

	// TODO dodac odbieranie info o synchronizacji
	private NetworkGroup group = new NetworkGroup();

	private ServerMultiplayer() {
		super(Aspect.all(BodyComponent.class), 1000 / 30f);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				sendFullEntity.set(true);
			}
		}, 10, 1000);
	}

	public ServerMultiplayer(final ArtemisServerRequestResponser createPlayerReciver) {
		this();
		this.createPlayerListener = createPlayerReciver;
		synchronizedStrategy = new HashSynchronizedStrategy(group);
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
//		System.out.println("Synchronized: " + UuidHelper.getUuidFromEntity(e));
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

	public PartUuid addClient(ServerClient client, Object clientCreateData) {
		UUID uuid = createPlayerListener.proccesNewPlayerJoin((EntityEngine) world, clientCreateData);
		System.out.println("Send uuid: " + uuid);
		sendFullEntity.set(true);
		group.addClient(client);
		return new PartUuid(uuid);
	}
}
