package galaxy.rapid;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.KryoSerialization;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import galaxy.rapid.network.server.ServerApiImpl;
import galaxy.rapid.network.service.ServerApi;
import pl.silver.JGNL.Network;

public class RapidNetwork {

	private boolean connected;
	private Client client;

	public void connect(final String host, final int tcp, final int udp) {
		System.out.println("connect");
		final AtomicBoolean end = new AtomicBoolean(false);
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				createClient();
				try {
					startClient(host, tcp, udp);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				end.set(true);
			}
		});
		th.start();
	}

	private void startClient(String host, int tcp, int udp) throws IOException {
		client.start();
		try {
			client.connect(Network.TIMEOUT, host, tcp, udp);
		} catch (IOException ex) {
			throw ex;
		}
	}

	private void createClient() {
		Kryo kryo = new Kryo();
		kryo.setAsmEnabled(true);
		ObjectSpace.registerClasses(kryo);
		this.client = new Client(Network.WRITE_BUFFER, Network.READ_BUFFER, new KryoSerialization(kryo));
		Network.register(client);
	}

	public void disconnect() {
		client.stop();
	}

	public boolean isConnected() {
		return connected;
	}

	public ServerApi getServerApi() {
		  ServerApi someObject = ObjectSpace.getRemoteObject(client, 1, ServerApi.class);
		  return someObject;
	}

}
