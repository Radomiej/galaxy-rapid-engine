package galaxy.rapid.asset;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import galaxy.rapid.configuration.RapidConfiguration;
import galaxy.rapid.downloader.AsynAdvenceDownloader;
import galaxy.rapid.downloader.DownloadInfo;
import galaxy.rapid.downloader.DownloadListener;
import galaxy.rapid.downloader.DownloadUtils;
import galaxy.rapid.downloader.exceptions.CanNotStartDownload;
import galaxy.rapid.log.RapidLog;
import galaxy.rapid.log.RapidLogFactory;
import galaxy.rapid.unzip.Unzipp;

/**
 * This asset downloader download all asset defined in rapid.properties file.
 * 
 * @author Radomiej
 */
public class RapidMultiAssetDownloader {
	private static RapidLog logger = RapidLogFactory.getLogger(RapidMultiAssetDownloader.class);

	private List<String> textUrls;
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private AtomicInteger loadingCounter = new AtomicInteger(0);
	private int maximumCounter;
	private boolean complete;
	private double speed;
	
	public RapidMultiAssetDownloader() {
		textUrls = RapidConfiguration.INSTANCE.getAssetsUrls();
	}

	public void startDownload() {
		for(final String url : textUrls){	
			incramentCounter();			
			executor.execute(new Runnable() {			
				@Override
				public void run() {
					RapidAssetDownloader downloader = new RapidAssetDownloader(url);
					downloader.startDownload();
					while(!downloader.isComplete()){
						speed = downloader.getSpeed();
						Thread.yield();
					}
					decramentCounter();
				}
			});
		}
	}

	private synchronized void incramentCounter() {
		int current = loadingCounter.incrementAndGet();		
		if(current > maximumCounter) maximumCounter = current;
	}

	private synchronized void decramentCounter(){
		int current = loadingCounter.decrementAndGet();
		if(current > 0){
			return;
		}
		
		complete = true;
	}
	
	public boolean isComplete() {
		if(maximumCounter == 0) return true;
		return complete;
	}
	
	public float getProgress(){
		return 1 - loadingCounter.get() / (float)maximumCounter;
	}

	public int getSpeed() {
		return (int) speed;
	}
}
