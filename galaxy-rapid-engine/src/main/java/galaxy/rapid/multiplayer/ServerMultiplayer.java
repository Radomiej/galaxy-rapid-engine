package galaxy.rapid.multiplayer;

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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;

import galaxy.rapid.common.ComponentsBag;
import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.components.Box2dComponent;
import pl.silver.JGNL.JGNLServer;
import pl.silver.JGNL.Network;

public class ServerMultiplayer extends IntervalEntityProcessingSystem{
	ComponentMapper<BodyComponent> bm;
	UuidEntityManager uuidManager;
	
	public ServerMultiplayer() {
		super(Aspect.all(BodyComponent.class), 1000 / 60f);
	}

	JGNLServer server;
	Json json;
	
	@Override
	protected void initialize() {
		json = new Json();
		server = new JGNLServer();       
		server.start(Network.portTCP, Network.portUDP);
	}
	
	@Override
	protected void process(Entity e) {
		
		ComponentsBag bag = EntityHelper.getComponentsFromEntity(e);
		UUID uuid = uuidManager.getUuid(e);
		JsonGameComponent sendObject = new JsonGameComponent();	
		sendObject.setMostSignBit(uuid.getMostSignificantBits());
		sendObject.setLestSignBit(uuid.getLeastSignificantBits());
		sendObject.setComponents(bag);
		server.sendEvent(sendObject);
	}

}
