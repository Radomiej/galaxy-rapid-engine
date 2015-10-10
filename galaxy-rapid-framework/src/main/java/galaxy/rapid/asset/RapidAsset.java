package galaxy.rapid.asset;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;

public enum RapidAsset {
	INSTANCE;
	
	private AssetManager manager;
	private Map<String, SpineAssetModel> spineMap = new HashMap<String, SpineAssetModel>(10);
	
	public <T> void loadGdx(String fileName, Class<T> type){
		manager.load(fileName, type);
	}
	
	
	public void loadPredefinedSpine(String spineOldUID, String skinName){
		if(spineMap.containsKey(skinName)){
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
	public void loadSpine(String spineSaveName, String spineJson, String spineAtlas, float scale){
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(spineAtlas));	
		SkeletonJson json = new SkeletonJson(atlas); // This loads skeleton JSON data, which is stateless.
		json.setScale(scale); // Load the skeleton at 100% the size it was in Spine.
		
		
		SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(spineJson));
		
		Skeleton skeleton = new Skeleton(skeletonData); // Skeleton holds skeleton state (bone positions, slot attachments, etc).

		AnimationStateData stateData = new AnimationStateData(skeletonData); // Defines mixing (crossfading) between animations.
		
		SpineAssetModel spineAsset = new SpineAssetModel();
		spineAsset.setAnimationStateData(stateData);
		spineAsset.setSkeleton(skeleton);
		spineAsset.setTextureAtlas(atlas);
		
		spineMap.put(spineSaveName, spineAsset);		
	}
	
	public SpineAssetModel getSpine(String spineSaveName){
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
	
	public void dispose(){
		manager.dispose();
		for(SpineAssetModel asset : spineMap.values()){
			asset.getTextureAtlas().dispose();
		}	
	}

	public AssetManager getInternalAssetManager() {
		return manager;
	}
}
