package galaxy.rapid;

public enum RapidEngine {
	INSTANCE;
	
	public final MusicManager MUSIC = new MusicManager(); 
	public final RapidNetwork NETWORK = new RapidNetwork();
	
}
