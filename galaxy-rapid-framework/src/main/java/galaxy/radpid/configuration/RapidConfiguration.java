package galaxy.radpid.configuration;

import java.net.URL;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public enum RapidConfiguration {
	INSTANCE;

	private final int default_width = 1024;	
	private float heightRatio = 1;
	
	private RapidConfig rapidConfig;
		
	/**
	 * Load application configuration with device memory
	 */
	public void load() {
		loadRapidConfiguration();		
		setHeightRatio();		
	}

	private void setHeightRatio() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		heightRatio = h / w;
	}

	private void loadRapidConfiguration() {
		Json json = new Json();
		FileHandle jsonFile = Gdx.files.internal("static/configuration/rapid.properties");
		rapidConfig = json.fromJson(RapidConfig.class, jsonFile);
	}	
	
	public int getDefaultAssetScale() {
		return rapidConfig.defaultAssetScale;
	}		

	public int getAppVersion() {
		return rapidConfig.appVersion;
	}
	
	public String getAppName() {
		return rapidConfig.appName;
	}
	
	public int getDefaultScale() {
		return rapidConfig.defaultAssetScale;
	}
	
	public float getHeightRatio() {
		return heightRatio;
	}

	public void setHeightRatio(float heightRatio) {
		this.heightRatio = heightRatio;
	}

	public int getGameWidth() {
		return default_width;
	}
	
	public int getGameHeight() {
		return (int) (default_width * heightRatio);
	}

	public boolean isDebugMode() {
		return rapidConfig.debugMode;
	}

	public RapidConfig getRapidConfig() {
		return rapidConfig;
	}

	public List<String> getAssetsUrls() {
		return rapidConfig.remoteAssetsUrl;
	}	
}
