package galaxy.radpid.input;

import com.badlogic.gdx.Gdx;

import galaxy.radpid.configuration.RapidConfiguration;

public class RapidInput {
	
	static public int getX(){
		int realX = Gdx.input.getX();
		return realX;
	}
	
	static public int getY(){
		int realY = Gdx.graphics.getHeight() - Gdx.input.getY();
		return realY;
	}
}
