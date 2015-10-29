package galaxy.rapid.asset;

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

public class RapidAssetDownloader {

	private URL url = null;
	private double progress;
	private double speed;
	private boolean end;
	private String externalFolder;

	public RapidAssetDownloader(String assetsUrl, String externalFolder) {
		this(assetsUrl);
		this.externalFolder = externalFolder;
	}

	public RapidAssetDownloader(String assetsUrl) {
		externalFolder = RapidConfiguration.INSTANCE.getAppName();
		try {
			url = new URL(assetsUrl);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}

	public void startDownload() {
		Gdx.app.debug(this.getClass().getSimpleName(), "Starting download...");
		AsynAdvenceDownloader downloader = new AsynAdvenceDownloader(url, new DownloadListener() {

			@Override
			public void downloadProgress(DownloadInfo downloadInfo) {
				updateDownloadInfo(downloadInfo, false);
			}

			@Override
			public void downloadComplete(File tempFile, DownloadInfo downloadInfo) {
				Gdx.app.log(RapidAssetDownloader.class.getSimpleName(), "download complete");
				updateDownloadInfo(downloadInfo, true);
				unzippDownloadedFile(tempFile);
			}

			private void updateDownloadInfo(DownloadInfo downloadInfo, boolean downloadComplete) {

				String currentDownload = Double.toString(DownloadUtils.getMb(downloadInfo.getCurrentDownloadLenght()));
				String allToDownload = Double.toString(DownloadUtils.getMb(downloadInfo.getLenght()));
				String speedDownload = Double.toString(DownloadUtils.getKb(downloadInfo.getDownloadSpeed()));
				Gdx.app.debug(RapidAssetDownloader.class.getSimpleName(), "download progress: " + currentDownload
						+ " MB/ " + allToDownload + " MB speed: " + speedDownload + " Kb/s");

				progress = downloadInfo.getCurrentDownloadLenght() / downloadInfo.getLenght();
				speed = DownloadUtils.getKb(downloadInfo.getDownloadSpeed());
				end = downloadComplete;
			}

			private void unzippDownloadedFile(File tempFile) {
				FileHandle assetFolder = Gdx.files.external(externalFolder);
				Unzipp unzipp = new Unzipp();
				unzipp.unzip(tempFile, assetFolder);
				Gdx.app.log(this.getClass().getSimpleName(), "unzip complete");
				if (!tempFile.delete()) {
					Gdx.app.error(RapidAssetDownloader.class.getSimpleName(),
							"cannot delete tempFile: " + tempFile.getAbsolutePath());
				}
			}
		});

		try {
			downloader.startDownload();
		} catch (CanNotStartDownload e) {
			e.printStackTrace();
			Gdx.app.error("Download", "Download error for url: " + url.toString());
		}
	}

	public double getProgress() {
		return progress;
	}

	public double getSpeed() {
		return speed;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}
}
