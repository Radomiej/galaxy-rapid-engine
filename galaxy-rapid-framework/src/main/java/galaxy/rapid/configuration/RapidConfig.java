package galaxy.rapid.configuration;

import java.util.ArrayList;
import java.util.List;

public class RapidConfig {
	public String appName = "NoNameApp";
	public int appVersion = 0;
	public boolean multipleAsset = true;
	public boolean debugMode;
	public String skinAsset;
	public int defaultAssetScale = 2;
	public List<String> remoteAssetsUrl = new ArrayList<String>();
}
