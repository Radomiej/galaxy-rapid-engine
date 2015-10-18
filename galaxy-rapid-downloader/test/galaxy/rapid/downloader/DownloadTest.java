package galaxy.rapid.downloader;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import galaxy.rapid.downloader.AsynAdvenceDownloader;
import galaxy.rapid.downloader.DownloadInfo;
import galaxy.rapid.downloader.DownloadListener;
import galaxy.rapid.downloader.DownloadUtils;
import galaxy.rapid.downloader.exceptions.CanNotStartDownload;

public class DownloadTest {

	@Test
	public void test() {
		
		URL url = null;
		try {
			url = new URL("http://www.helloschool.com.pl/uploads/8/3/1/5/8315217/unit13.zip");
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
				tempFile.delete();
			}
		});
		
		try {
			downloader.startDownload();
		} catch (CanNotStartDownload e) {
			e.printStackTrace();
			fail("B³ad pobierania");
		}
		
		while(!downloader.isComplete()){
			
		}
	}

}
