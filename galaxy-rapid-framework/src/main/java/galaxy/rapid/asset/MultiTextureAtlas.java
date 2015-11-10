package galaxy.rapid.asset;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import galaxy.rapid.log.RapidLog;
import galaxy.rapid.log.RapidLogFactory;

public class MultiTextureAtlas {
	private static RapidLog logger = RapidLogFactory.getLogger(MultiTextureAtlas.class);
	String symbolicName;
	String fullName;
	
	AtomicInteger end = new AtomicInteger();
	Set<TextureAtlas> atlases = new HashSet<TextureAtlas>();
	ExecutorService executor = Executors.newFixedThreadPool(1);
	/**
	 * 
	 * @param fullName this is name in format [atlas-simple-name][scale-name]
	 */
	public MultiTextureAtlas(String fullName) {
		this.fullName = fullName;
	}
	
	public void findAllAtlas(FileHandleResolver fileHandleResolver, final AssetManager manager){
		int startIndex = 0;
		while(true){
			final String searchAtlas = fullName + startIndex + ".atlas";
			FileHandle fileAtlas = fileHandleResolver.resolve(searchAtlas);
			if(fileAtlas.exists()){
				end.incrementAndGet();
				manager.load(searchAtlas, TextureAtlas.class);
				executor.execute(new Runnable() {
					@Override
					public void run() {
						while (!manager.isLoaded(searchAtlas)) {
							Thread.yield();
						}
						atlases.add(manager.get(searchAtlas, TextureAtlas.class));
						end.decrementAndGet();
						logger.info(fullName + " dodaje do mapy atlas: " + searchAtlas);
					}
				});
				startIndex++;
			}	
			else{
				return;
			}
		}		
	}
	
	public Sprite getSprite(String regionName){
		while(end.get() != 0){
			Thread.yield();
		}
		for(TextureAtlas atlas : atlases){
			if(atlas.findRegion(regionName) != null){
				logger.info("Znaleziono texture: " + regionName + " w " + atlas.toString());
				return atlas.createSprite(regionName);
			}
		}
		return null;		
	}

	public boolean loadingComplete() {
		return end.get() == 0;
	}

}
