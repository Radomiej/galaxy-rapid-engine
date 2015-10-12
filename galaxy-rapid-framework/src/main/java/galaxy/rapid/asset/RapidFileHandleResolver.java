package galaxy.rapid.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

public class RapidFileHandleResolver implements FileHandleResolver {
	public FileHandle resolve(String fileName) {
		FileHandle file = Gdx.files.internal(fileName);
		if(file != null) return file;
		file = new FileHandle(fileName);
		return file;
	}
}
