package galaxy.rapid.asset;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

import galaxy.radpid.configuration.RapidConfiguration;

public class RapidFileHandleResolver implements FileHandleResolver {
	public FileHandle resolve(String fileName) {
		FileHandle file = null;
		try {
			file = getFromExternal(fileName);
			if(file.exists()) return file;
			System.out.println("Brak pliku w pobranych");
			
			file = Gdx.files.internal(fileName);
			if(file.exists()) return file;
			System.out.println("Brak internal");
			file = Gdx.files.local(fileName);
			if(file.exists()) return file;			
			System.out.println("Brak lokalnego");			
			file = Gdx.files.absolute(fileName);
			if(file.exists()) return file;
			System.out.println("Brak absolute");	
			
		} catch (Exception e) {
			System.out.println("Catch get native fileHandler");
			file = new FileHandle(fileName);
			return file;
		}
		return file;		
	}

	private FileHandle getFromExternal(String fileName) {
		FileHandle file = null;
		file = Gdx.files.internal(fileName);		
		if(file.exists()) return file;
		System.out.println("Brak pliku w: " + file.path());
		
		file = Gdx.files.internal(RapidConfiguration.INSTANCE.getAppName() + File.separator + fileName);
		if(file.exists()) return file;
		System.out.println("Brak pliku w: " + file.path());
		
		return file;
	}
}
