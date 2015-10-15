package galaxy.rapid.components;

import com.artemis.Component;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Skeleton;

import galaxy.rapid.asset.SpineAssetModel;

public class SpineComponent extends Component {
	private Skeleton skeleton;
	private AnimationState state;

	public SpineComponent() {
	}
	
	public SpineComponent(SpineAssetModel spine) {
		skeleton = spine.getSkeleton();
		state = new AnimationState(spine.getAnimationStateData());
	}
	
	public Skeleton getSkeleton() {
		return skeleton;
	}

	public void setSkeleton(Skeleton skeleton) {
		this.skeleton = skeleton;
	}

	public AnimationState getState() {
		return state;
	}

	public void setState(AnimationState state) {
		this.state = state;
	}
}
