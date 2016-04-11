package galaxy.rapid.multiplayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import galaxy.rapid.RapidEngine;
import galaxy.rapid.network.server.ServerSide;
import galaxy.rapid.network.service.ServerApi;
import pl.silver.JGNL.Network;

public class ServerApiTest {
	static ServerSide server;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Network.registerClasses(ServerApi.class);
		server = new ServerSide(null);
		server.startServer();
		Thread.sleep(200);
		System.out.println("after");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws InterruptedException {
		RapidEngine.INSTANCE.NETWORK.connect("localhost", Network.portTCP, Network.portUDP);
		Thread.sleep(200);
		ServerApi api = RapidEngine.INSTANCE.NETWORK.getServerApi();
		api.login("demo", "demo");
	}

}
