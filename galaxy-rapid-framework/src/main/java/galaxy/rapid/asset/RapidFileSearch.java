package galaxy.rapid.asset;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.files.FileHandle;

public class RapidFileSearch {
	public static List<String> getChildreenAssets(FileHandle rootDirectory, String... extansions){
		List<String> assetNames = new ArrayList();
		if(!rootDirectory.isDirectory()){
			System.err.println(rootDirectory.name() + " nie jest folderem!");
			return null;
		}		
		addAssetsFromThisDirectory(assetNames, rootDirectory, extansions);		
		return assetNames;		
	}

	private static void addAssetsFromThisDirectory(List<String> assetNames, FileHandle directory,
			String... extansions) {
		FileHandle[] childrens = directory.list();
		for(FileHandle file : childrens){
			if(file.isDirectory()) {
				addAssetsFromThisDirectory(assetNames, file, extansions);
				continue;
			}
			String extansion = file.extension();
			if(isOneWithSomeExtansions(extansion, extansions)){
				assetNames.add(file.path());
			}
		}
	}

	private static boolean isOneWithSomeExtansions(String extansion, String[] extansions) {
		for(String equalExtansion : extansions){
			if(equalExtansion.equalsIgnoreCase(extansion)) return true;
		}
		return false;
	}
}
