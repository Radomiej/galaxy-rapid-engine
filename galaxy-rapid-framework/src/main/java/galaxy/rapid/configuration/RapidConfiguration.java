package galaxy.rapid.configuration;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;

public enum RapidConfiguration {
	INSTANCE;

	private final String CONFIG_ASSET = "static/configuration/rapid.properties";
	private final int default_width = 1024;
	private float heightRatio = 1;
	private float gameRatio = 1;

	private Scale scale;
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

		scale = Scale.getByWidth(w);
		Gdx.app.log("Config" , "Device scale" + scale.name());

		gameRatio = scale.getDefaultWidth() / (float) default_width;
		
		Gdx.app.log("Config" , "Game ratio: " + gameRatio);
	}

	private void loadRapidConfiguration() {
		Json json = new Json();
		FileHandle jsonFile = Gdx.files.internal(CONFIG_ASSET);
		if (jsonFile.exists()) {
			try {
				rapidConfig = json.fromJson(RapidConfig.class, jsonFile);
			} catch (SerializationException e) {
				Gdx.app.error("Config" , "Cannot deserialize RapidCofig, please check this file in: " + CONFIG_ASSET, e);
			}
		}else{
			Gdx.app.error("Config" , "Configuration file not exist in: " + CONFIG_ASSET);
			rapidConfig = new RapidConfig();
		}
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

	public Scale getScale() {
		return scale;
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

	public float getGameRatio() {
		return gameRatio;
	}
}
