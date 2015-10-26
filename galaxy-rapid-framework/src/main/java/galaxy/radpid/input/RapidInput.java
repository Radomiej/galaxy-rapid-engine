package galaxy.radpid.input;

import com.badlogic.gdx.Gdx;

import galaxy.radpid.configuration.RapidConfiguration;

public class RapidInput {
	
	static public int getX(){
		int realX = Gdx.input.getX();
		
		float pixelInGame = RapidConfiguration.INSTANCE.getCurrentScale() * RapidConfiguration.DEFAULT_WIDTH;
		float scale = pixelInGame / Gdx.graphics.getWidth();
		
		return (int) (realX * scale);		
	}
	
	static public int getY(){
		int realY = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		float pixelInGame = RapidConfiguration.INSTANCE.getCurrentScale() * RapidConfiguration.DEFAULT_WIDTH;
		float scale = pixelInGame / Gdx.graphics.getWidth();
		
		return (int) (realY * scale);		
	}
}
