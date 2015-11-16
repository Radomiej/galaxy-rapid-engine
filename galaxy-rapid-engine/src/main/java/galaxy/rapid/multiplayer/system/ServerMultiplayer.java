package galaxy.rapid.multiplayer.system;

import java.util.UUID;

import com.artemis.Aspect;
import com.artemis.Aspect.Builder;
import com.artemis.BaseSystem;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.UuidEntityManager;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.artemis.utils.EntityBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;

import galaxy.rapid.common.ComponentsBag;
import galaxy.rapid.common.EntityEngine;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.components.Box2dComponent;
import galaxy.rapid.multiplayer.ArtemisServerRequestResponser;
import galaxy.rapid.multiplayer.EntityHelper;
import galaxy.rapid.multiplayer.JsonGameComponent;
import galaxy.rapid.multiplayer.PartUuid;
import pl.silver.JGNL.JGNLServer;
import pl.silver.JGNL.Network;
import pl.silver.JGNL.request.RequestReciver;

public class ServerMultiplayer extends IntervalEntityProcessingSystem{
	private ComponentMapper<BodyComponent> bm;
	private UuidEntityManager uuidManager;
	
	private JGNLServer server;
	private Json json;
	
	private ArtemisServerRequestResponser artemisRequestResponse;
	private RequestReciver requestReciver;
	
	
	private ServerMultiplayer() {
		super(Aspect.all(BodyComponent.class), 1000 / 60f);
	}
	
	public ServerMultiplayer(final ArtemisServerRequestResponser createPlayerReciver) {
		this();
		this.artemisRequestResponse = createPlayerReciver;
		requestReciver = new RequestReciver() {
			@Override
			public Object recivedRequest(Connection connection, Object object) {
				 UUID uuid = createPlayerReciver.proccesNewPlayerJoin((EntityEngine) world);
				 return new PartUuid(uuid);
			}
		};
	}

	
	
	@Override
	protected void initialize() {
		Log.set(Log.LEVEL_ERROR);
		json = new Json();
		server = new JGNLServer(); 
		server.setRequestReciver(requestReciver);
		server.start(Network.portTCP, Network.portUDP);
	}
	
	@Override
	protected void process(Entity e) {
		
		//TODO system to manage create, update and remove component in remote entities
		ComponentsBag bag = EntityHelper.getComponentsFromEntity(e);
		UUID uuid = uuidManager.getUuid(e);
		JsonGameComponent sendObject = new JsonGameComponent();	
		sendObject.setMostSignBit(uuid.getMostSignificantBits());
		sendObject.setLestSignBit(uuid.getLeastSignificantBits());
		sendObject.setComponents(bag);
		server.sendEvent(sendObject);
	}
}
