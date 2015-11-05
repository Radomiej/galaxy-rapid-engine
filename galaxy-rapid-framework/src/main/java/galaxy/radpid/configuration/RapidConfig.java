package galaxy.radpid.configuration;

import java.util.ArrayList;
import java.util.List;

public class RapidConfig {
	public String appName = "NoNameApp";
	public int appVersion = 0;
	public boolean multipleAsset;
	public boolean debugMode;
	public String skinAsset;
	public int defaultAssetScale = 1;
	public List<String> remoteAssetsUrl = new ArrayList<String>();
}
