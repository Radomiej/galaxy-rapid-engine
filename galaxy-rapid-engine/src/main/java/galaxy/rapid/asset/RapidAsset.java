package galaxy.rapid.asset;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;

import galaxy.rapid.asset.spine.SkeletonDataLoader;
import galaxy.rapid.asset.spine.SkeletonDataLoaderParameter;
import galaxy.rapid.configuration.RapidConfiguration;

public enum RapidAsset {
	INSTANCE;

	private volatile AssetManager manager;
	private Map<String, Sprite> spriteMap = new HashMap<String, Sprite>(10);
	private Map<String, MultiTextureAtlas> atlasMap = new HashMap<String, MultiTextureAtlas>(10);
	private FileHandleResolver handleResolver;

	private RapidAsset() {
		handleResolver = new RapidFileHandleResolver();
		if (RapidConfiguration.INSTANCE.isDebugMode()) {
			handleResolver = new InternalFileHandleResolver();
		}

		manager = new AssetManager(handleResolver);
		manager.setLoader(SkeletonData.class, new SkeletonDataLoader());
	}

	public void loadTexture(String fileName) {
		manager.load(fileName, Texture.class);
	}

	private void loadSprite() {
		Gdx.app.log("RapidAsset" , "Loading all sprites in current avaiable in device memory");
		
		for (MultiTextureAtlas multiTextureAtlas : atlasMap.values()) {
			multiTextureAtlas.signAllAssets(spriteMap);
		}
	}

	public Sprite getSprite(String regionName) {
		Gdx.app.log("RapidAsset" , "get Sprite: " + regionName);
		return spriteMap.get(regionName);
	}

	public void loadTextureAtlas(final String spineAtlas) {
		String atlasFullName = addFullNameAtlas("images", spineAtlas);
		MultiTextureAtlas multiTextureAtlas = new MultiTextureAtlas(atlasFullName);
		multiTextureAtlas.findAllAtlas(handleResolver, manager);
		atlasMap.put(spineAtlas, multiTextureAtlas);
	}

	public SpineAssetModel getSpine(String skeletonAssetName, final String skinName) {
		final String jsonFullName = "spine/" + skeletonAssetName + ".json";
		
		SkeletonData skeletonData = manager.get(jsonFullName, SkeletonData.class);
		SpineAssetModel newSpineAssetModel = new SpineAssetModel();
		newSpineAssetModel.setSkeleton(new Skeleton(skeletonData));
		newSpineAssetModel.getSkeleton().setSkin(skinName);

		AnimationStateData animationStateData = new AnimationStateData(skeletonData);
		AnimationState animationState = new AnimationState(animationStateData);
		newSpineAssetModel.setAnimationState(animationState);

		return newSpineAssetModel;
	}

	public void loadSpine(String spineAssetsName) {
		final String spineAtlasFullName = "spine/" + spineAssetsName + "-"
				+ RapidConfiguration.INSTANCE.getScale().name().toLowerCase() + ".atlas";
		final String jsonFullName = "spine/" + spineAssetsName + ".json";
		Gdx.app.log("RapidAsset" , "spineAtlas: " + spineAtlasFullName);
		Gdx.app.log("RapidAsset" , "jsonData: " + jsonFullName);

		SkeletonDataLoaderParameter parameter = new SkeletonDataLoaderParameter(spineAtlasFullName, 1);
		manager.load(jsonFullName, SkeletonData.class, parameter);

	}

	private String addFullNameAtlas(String prefix, String spineAtlas) {
		return prefix + "/" + spineAtlas + "-" + RapidConfiguration.INSTANCE.getScale().name().toLowerCase();
	}

	public boolean loadedComplete() {
		if(manager.update()){
			loadSprite();
			return true;
		}
		return false;
	}

	public float getProgress() {
		return manager.getProgress();
	}

	public void dispose() {
		manager.dispose();
	}
}