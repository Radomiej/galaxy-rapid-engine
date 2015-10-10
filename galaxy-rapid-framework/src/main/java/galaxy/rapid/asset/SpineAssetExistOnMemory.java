package galaxy.rapid.asset;

public class SpineAssetExistOnMemory extends RuntimeException
{

	public SpineAssetExistOnMemory(String skinName) {
		super(skinName);
	}

}
