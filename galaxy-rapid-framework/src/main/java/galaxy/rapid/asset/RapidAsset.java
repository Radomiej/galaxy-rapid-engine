package galaxy.rapid.asset;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;

import galaxy.rapid.configuration.RapidConfiguration;
import galaxy.rapid.log.RapidLog;
import galaxy.rapid.log.RapidLogFactory;

public enum RapidAsset {
	INSTANCE;

	private static RapidLog logger = RapidLogFactory.getLogger(RapidAsset.class);

	private volatile AssetManager manager;
	private Map<String, SpineAssetModel> spineMap = new HashMap<String, SpineAssetModel>(10);
	private Map<String, Sprite> spriteMap = new HashMap<String, Sprite>(10);
	private Map<String, MultiTextureAtlas> atlasMap = new HashMap<String, MultiTextureAtlas>(10);
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private FileHandleResolver handleResolver;

	private AtomicInteger externalOperations = new AtomicInteger(0);

	private RapidAsset() {
		handleResolver = new RapidFileHandleResolver();
		if (RapidConfiguration.INSTANCE.isDebugMode()) {
			handleResolver = new InternalFileHandleResolver();
		}

		manager = new AssetManager(handleResolver);
		executor = Executors.newFixedThreadPool(1);
	}

	public void loadTexture(String fileName) {
		manager.load(fileName, Texture.class);
	}

	public void loadSprite(final String regionName, final String atlasName) {

		if (spriteMap.containsKey(regionName)) {
			logger.warn("Override existing region name: " + regionName);
		}
		externalOperations.incrementAndGet();
		executor.execute(new Runnable() {
			@Override
			public void run() {
				MultiTextureAtlas multiTextureAtlas = atlasMap.get(atlasName);
				while(!multiTextureAtlas.loadingComplete()){
					Thread.yield();
				}
				Sprite sprite = multiTextureAtlas.getSprite(regionName);
				logger.debug("Dodaje sprite: " + regionName);
				spriteMap.put(regionName, sprite);
				externalOperations.decrementAndGet();
			}
		});
		
		
		
	}

	public Sprite getSprite(String regionName) {
		return spriteMap.get(regionName);
	}


	public void loadTextureAtlas(final String spineAtlas) {
		String atlasFullName = addFullNameAtlas("images" ,spineAtlas);
		MultiTextureAtlas multiTextureAtlas = new MultiTextureAtlas(atlasFullName);
		multiTextureAtlas.findAllAtlas(handleResolver, manager);
		atlasMap.put(spineAtlas, multiTextureAtlas);
	}

	public void loadPredefinedSpine(final String spineOldUID, final String skinName) {
		externalOperations.incrementAndGet();
		executor.execute(new Runnable() {
			@Override
			public void run() {
				while (!spineMap.containsKey(spineOldUID)) {
					Thread.yield();
				}
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
				externalOperations.decrementAndGet();
			}
		});
	}

	public void loadSpine(final String spineSaveName, String spineAssetsName) {
		externalOperations.incrementAndGet();
		final String spineAtlasFullName = "spine/" + spineAssetsName + "-" + RapidConfiguration.INSTANCE.getScale().name().toLowerCase() + ".atlas";
		final String jsonFullName = "spine/" + spineAssetsName + ".json";
		logger.info("spineAtlas: " + spineAtlasFullName);
		logger.info("jsonData: " + jsonFullName);
		
		manager.load(spineAtlasFullName, TextureAtlas.class);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				while (!manager.isLoaded(spineAtlasFullName)) {
					Thread.yield();
				}
				TextureAtlas atlas = manager.get(spineAtlasFullName);

				SkeletonJson json = new SkeletonJson(atlas); // This loads
																// skeleton JSON
//				json.setScale(scale);

				SkeletonData skeletonData = json.readSkeletonData(handleResolver.resolve(jsonFullName));

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
				externalOperations.decrementAndGet();
			}
		});
	}

	@Deprecated
	public void loadSpine(final String spineSaveName, final String spineJson, String spineAtlas, final float scale) {
		externalOperations.incrementAndGet();
		final String spineAtlasFullName = addFullNameAtlas("spine", spineAtlas);
		final String jsonFullName = "spine/" + spineJson + "-" + RapidConfiguration.INSTANCE.getScale().name().toLowerCase() + ".atlas";
		logger.info("spineAtlas: " + spineAtlasFullName);
		logger.info("jsonData: " + jsonFullName);
		
		manager.load(spineAtlasFullName, TextureAtlas.class);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				while (!manager.isLoaded(spineAtlasFullName)) {
					Thread.yield();
				}
				TextureAtlas atlas = manager.get(spineAtlasFullName);

				SkeletonJson json = new SkeletonJson(atlas); // This loads
																// skeleton JSON
				json.setScale(scale);

				SkeletonData skeletonData = json.readSkeletonData(handleResolver.resolve(jsonFullName));

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
				externalOperations.decrementAndGet();
			}
		});

	}

	private String addFullNameAtlas(String prefix, String spineAtlas) {
		return prefix + "/" + spineAtlas + "-" + RapidConfiguration.INSTANCE.getScale().name().toLowerCase();
	}

	public SpineAssetModel getSpine(String spineSaveName) {
		SpineAssetModel spineModel = spineMap.get(spineSaveName);
		if (spineModel == null) {
			throw new UnsupportedOperationException("Brak asseta o nazwie: " + spineSaveName);
		}
		return spineMap.get(spineSaveName);
	}

	public boolean loadedComplete() {
		return manager.update() && externalOperations.get() == 0;
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

	@Deprecated
	public <T> void loadGdx(String fileName, Class<T> type) {
		manager.load(fileName, type);
	}

	@Deprecated
	public Texture getTexture(String asset) {
		return manager.get(asset);
	}

	@Deprecated
	public AssetManager getInternalAssetManager() {
		return manager;
	}
}
