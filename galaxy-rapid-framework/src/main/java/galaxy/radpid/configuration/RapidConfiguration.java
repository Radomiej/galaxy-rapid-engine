package galaxy.radpid.configuration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public enum RapidConfiguration {
	INSTANCE;

	private final int default_width = 1024;
	
	private String appName = "NoNameApp";
	private int appVersion;
	private int defaultAssetScale = 1;
	private int currentScale = 1;
	private float heightRatio = 1;
	private boolean debugMode = false;	
	private boolean firstInitializationComplete = false;

	
	
	
	/**
	 * Load application configuration with device memory
	 * @param rapidConfig default user initial configuration.
	 * @return true if inject configuration will be apply.
	 */
	public boolean load(RapidConfig rapidConfig) {
		appName = rapidConfig.appName;
		debugMode = rapidConfig.debugMode;
		Preferences preferences = Gdx.app.getPreferences(rapidConfig.appName);		
		proccesFirstInitializationIfNeeded(rapidConfig, preferences);
		
		
		appVersion = preferences.getInteger("appVersion", 0);
		defaultAssetScale = preferences.getInteger("defaultAssetScale", 1);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		heightRatio = h / w;
		
		
		return false;
	}

	private void proccesFirstInitializationIfNeeded(RapidConfig rapidConfig, Preferences preferences) {
		firstInitializationComplete = preferences.getBoolean("firstInitializationComplete", false);	
		if(!firstInitializationComplete){
			save(rapidConfig);
		}
	}

	private void save(RapidConfig rapidConfig) {
		Preferences preferences = Gdx.app.getPreferences(rapidConfig.appName);
		preferences.putInteger("appVersion", rapidConfig.appVersion);
		preferences.putInteger("defaultAssetScale", rapidConfig.defaultAssetScale);
		preferences.putBoolean("firstInitializationComplete", true);
		preferences.flush();
	}
	
	public int getDefaultAssetScale() {
		return defaultAssetScale;
	}

	public void setDefaultAssetScale(int defaultAssetScale) {
		this.defaultAssetScale = defaultAssetScale;
	}	

	public int getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(int appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getCurrentScale() {
		return currentScale;
	}

	public void setCurrentScale(int currentScale) {
		this.currentScale = currentScale;
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
		return debugMode;
	}
	
	
}
