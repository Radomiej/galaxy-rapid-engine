package galaxy.rapid.asset;

import java.awt.image.VolatileImage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;

public enum RapidAsset {
	INSTANCE;

	private volatile AssetManager manager;
	private Map<String, SpineAssetModel> spineMap = new HashMap<String, SpineAssetModel>(10);
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private RapidAsset() {
		manager = new AssetManager(new RapidFileHandleResolver());
		executor = Executors.newFixedThreadPool(1);
	}

	public <T> void loadGdx(String fileName, Class<T> type) {
		manager.load(fileName, type);
	}

	public void loadPredefinedSpine(final String spineOldUID, final String skinName) {
		executor.execute(new Runnable() {			
			@Override
			public void run() {
				if (spineMap.containsKey(skinName)) {
					throw new SpineAssetExistOnMemory(skinName);
				}
				SpineAssetModel assetSpine = getSpine(spineOldUID);
				SpineAssetModel newSpineAssetModel = new SpineAssetModel();
				newSpineAssetModel.setSkeleton(new Skeleton(assetSpine.getSkeleton()));
				newSpineAssetModel.getSkeleton().setSkin(skinName);
				newSpineAssetModel.setAnimationStateData(assetSpine.getAnimationStateData());
				newSpineAssetModel.setTextureAtlas(assetSpine.getTextureAtlas());
				spineMap.put(skinName, newSpineAssetModel);
			}
		});
	}

	public void loadSpine(final String spineSaveName, final String spineJson, final String spineAtlas, final float scale) {
		manager.load(spineAtlas, TextureAtlas.class);
		executor.execute(new Runnable() {			
			@Override
			public void run() {
				while(!manager.isLoaded(spineAtlas)){					
					Thread.yield();
				}
				TextureAtlas atlas = manager.get(spineAtlas);
				
				SkeletonJson json = new SkeletonJson(atlas); // This loads skeleton JSON
				json.setScale(scale); 
				SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(spineJson));
				
				Skeleton skeleton = new Skeleton(skeletonData); 
				AnimationStateData stateData = new AnimationStateData(skeletonData); // Defines
																						// (crossfading)
																						// between
																						// animations.

				SpineAssetModel spineAsset = new SpineAssetModel();
				spineAsset.setAnimationStateData(stateData);
				spineAsset.setSkeleton(skeleton);
				spineAsset.setTextureAtlas(atlas);

				spineMap.put(spineSaveName, spineAsset);
			}
		});		
		
	}

	public SpineAssetModel getSpine(String spineSaveName) {
		SpineAssetModel spineModel = spineMap.get(spineSaveName);
		if(spineModel == null){
			throw new UnsupportedOperationException("Brak asseta o nazwie: " + spineSaveName);
		}
		return spineMap.get(spineSaveName);
	}

	public Texture getTexture(String asset) {
		return manager.get(asset);
	}

	public boolean loadedComplete() {
		return manager.update();
	}

	public float getProgress() {
		return manager.getProgress();
	}

	public void dispose() {
		manager.dispose();
		for (SpineAssetModel asset : spineMap.values()) {
			asset.getTextureAtlas().dispose();
		}
	}

	public AssetManager getInternalAssetManager() {
		return manager;
	}
}
