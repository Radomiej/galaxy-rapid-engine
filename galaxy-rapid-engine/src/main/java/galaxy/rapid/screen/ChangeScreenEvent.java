package galaxy.rapid.screen;

import com.badlogic.gdx.Screen;

import galaxy.rapid.asset.AssetQueue;
import net.mostlyoriginal.api.event.common.Event;

public final class ChangeScreenEvent implements Event {
	private Screen newScene;
	private boolean disposeCurrent;
	private AssetQueue assetQueue;

	public ChangeScreenEvent(Screen newScene) {
		this(newScene, true);
	}

	public ChangeScreenEvent(Screen newScene, boolean disposeCurrent) {
		this.newScene = newScene;
		this.disposeCurrent = disposeCurrent;
	}

	public ChangeScreenEvent(Screen newScene, AssetQueue assetQueue) {
		this.newScene = newScene;
		this.disposeCurrent = true;
		this.assetQueue = assetQueue;
	}

	public Screen getNewScene() {
		return newScene;
	}

	public void setNewScene(Screen newScene) {
		this.newScene = newScene;
	}

	public boolean isDisposeCurrent() {
		return disposeCurrent;
	}

	public void setDisposeCurrent(boolean disposeCurrent) {
		this.disposeCurrent = disposeCurrent;
	}

	public AssetQueue getAssetQueue() {
		return assetQueue;
	}

	public void setAssetQueue(AssetQueue assetQueue) {
		this.assetQueue = assetQueue;
	}
}
