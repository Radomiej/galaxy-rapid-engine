package galaxy.rapid.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

public class RapidFileHandleResolver implements FileHandleResolver {
	public FileHandle resolve(String fileName) {
		FileHandle file = null;
		try {
			file = Gdx.files.internal(fileName);
			if(file.exists()) return file;
			System.out.println("Brak internal");
			file = Gdx.files.local(fileName);
			if(file.exists()) return file;
			System.out.println("Brak lokalnego");
			file = Gdx.files.absolute(fileName);
			if(file.exists()) return file;
			
		} catch (Exception e) {
			System.out.println("Catch get native fileHandler");
			file = new FileHandle(fileName);
			return file;
		}
		return file;		
	}
}
