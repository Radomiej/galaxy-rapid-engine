package galaxy.rapid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {
	
	Music music;
	public MusicManager() {
	}
	
	public void play(String assetName){
		String assetFullName = "music/" + assetName;
		music = Gdx.audio.newMusic(Gdx.files.internal(assetFullName));
		music.setLooping(true);
		music.play();
	}
	
	public void stop(){
		music.stop();
		music.dispose();
	}
}
