package galaxy.rapid.input;

import com.badlogic.gdx.Gdx;

import galaxy.rapid.configuration.RapidConfiguration;

public class RapidInput {
	
	static public int getX(){
		int realX = Gdx.input.getX();
		float scale = Gdx.graphics.getWidth() / 1024;
		return (int) (realX * scale);
	}
	
	static public int getY(){
		int realY = Gdx.graphics.getHeight() - Gdx.input.getY();
		return realY;
	}
}
