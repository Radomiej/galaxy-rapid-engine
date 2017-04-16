package galaxy.rapid.asset;

import java.util.HashSet;
import java.util.Set;

public class AssetQueue {
	private Set<String> atlases = new HashSet<String>();
	private Set<String> sounds = new HashSet<String>();
	
	public void loadSound(String sound){
		RapidAsset.INSTANCE.loadSound(sound);
		sounds.add(sound);
	}
	
	public void loadTextureAtlas(final String textureAtlasAsset) {
		RapidAsset.INSTANCE.loadTextureAtlas(textureAtlasAsset);
		atlases.add(textureAtlasAsset);
	}
	
	public void unloadAll(){
		for(String atlas : atlases){
			RapidAsset.INSTANCE.unloadTextureAtlas(atlas);
		}
		for(String sound : sounds){
			RapidAsset.INSTANCE.unloadSound(sound);
		}
	}
}
