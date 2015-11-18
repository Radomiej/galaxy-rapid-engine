package galaxy.rapid.multiplayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.artemis.Entity;

import galaxy.rapid.multiplayer.server.HashSynchronizedStrategy;
import pl.silver.JGNL.JGNLServer;

@RunWith(MockitoJUnitRunner.class)
public class HashSynchronizedStrategyTest {

	HashSynchronizedStrategy testStrategy;
	
	@Before
	public void setUp() throws Exception {
		JGNLServer server = Mockito.mock(JGNLServer.class);
		testStrategy = new HashSynchronizedStrategy(server);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Entity entity0 = Mockito.mock(Entity.class);
		
		
//		testStrategy.sendEntity(e);
	}

}
