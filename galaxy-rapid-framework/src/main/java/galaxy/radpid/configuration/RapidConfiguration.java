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
	
	private boolean firstInitializationComplete = false;
	private ResizingStrategy resizingStrategy;

	
	public void load(){
		Preferences preferences = Gdx.app.getPreferences(appName);
		appVersion = preferences.getInteger("appVersion", -1);
		defaultAssetScale = preferences.getInteger("appVersion", 1);
		firstInitializationComplete = preferences.getBoolean("firstInitializationComplete", false);
		resizingStrategy = ResizingStrategy.valueOf(preferences.getString("resizingStrategy", ResizingStrategy.WITHOUT_RESIZING.name()));
	
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		heightRatio = h / w;
	}
	
	public void save(){
		Preferences preferences = Gdx.app.getPreferences(appName);
		preferences.putInteger("appVersion", appVersion);
		preferences.putInteger("defaultAssetScale", defaultAssetScale);
		preferences.putBoolean("firstInitializationComplete", firstInitializationComplete);
		preferences.putString("resizingStrategy", resizingStrategy.name());
		preferences.flush();
	}
	
	
	
	
	
	
	public int getDefaultAssetScale() {
		return defaultAssetScale;
	}

	public void setDefaultAssetScale(int defaultAssetScale) {
		this.defaultAssetScale = defaultAssetScale;
	}

	public boolean isFirstInitializationComplete() {
		return firstInitializationComplete;
	}

	public void setFirstInitializationComplete(boolean firstInitializationComplete) {
		this.firstInitializationComplete = firstInitializationComplete;
	}

	public ResizingStrategy getResizingStrategy() {
		return resizingStrategy;
	}

	public void setResizingStrategy(ResizingStrategy resizingStrategy) {
		this.resizingStrategy = resizingStrategy;
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
	
	
}
