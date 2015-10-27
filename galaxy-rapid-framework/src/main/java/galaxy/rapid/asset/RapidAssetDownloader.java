package galaxy.rapid.asset;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import galaxy.radpid.configuration.RapidConfiguration;
import galaxy.rapid.downloader.AsynAdvenceDownloader;
import galaxy.rapid.downloader.DownloadInfo;
import galaxy.rapid.downloader.DownloadListener;
import galaxy.rapid.downloader.DownloadUtils;
import galaxy.rapid.downloader.exceptions.CanNotStartDownload;
import galaxy.rapid.unzip.Unzipp;

public enum RapidAssetDownloader {
	INSTANCE;
	
	public void loadAsset(String urlString) {
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			fail("B³ad utworzenia adresu URL");
		}
		AsynAdvenceDownloader downloader = new AsynAdvenceDownloader(url, new DownloadListener() {
			
			@Override
			public void downloadProgress(DownloadInfo downloadInfo) {
				String currentDownload = Double.toString(DownloadUtils.getMb(downloadInfo.getCurrentDownloadLenght()));
				String allToDownload = Double.toString(DownloadUtils.getMb(downloadInfo.getLenght()));
				String speedDownload = Double.toString(DownloadUtils.getKb(downloadInfo.getDownloadSpeed()));
				
				System.out.println("download progress: " + currentDownload + " MB/ " + allToDownload + " MB speed: " + speedDownload + " Kb/s");
			}
			
			@Override
			public void downloadComplete(File tempFile, DownloadInfo downloadInfo) {
				System.out.println("download complete");
				FileHandle assetFolder = Gdx.files.external(RapidConfiguration.INSTANCE.getAppName());
				File file = assetFolder.file();
				System.out.println("Unzip do: " + file.getAbsolutePath());
				Unzipp unzipp = new Unzipp();
				unzipp.unzip(tempFile, file.getAbsolutePath());
				
				System.out.println("unzip complete");
				tempFile.delete();
			}
		});
		
		try {
			downloader.startDownload();
		} catch (CanNotStartDownload e) {
			e.printStackTrace();
		}
		
		while(!downloader.isComplete()){
			
		}
	}
}
