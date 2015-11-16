package galaxy.rapid.components;

import com.artemis.Component;

public class KeyboardComponent extends Component {
	private float knockX;
	private float knockY;

	public float getKnockX() {
		return knockX;
	}

	public void setKnockX(float knockX) {
		this.knockX = knockX;
	}

	public float getKnockY() {
		return knockY;
	}

	public void setKnockY(float knockY) {
		this.knockY = knockY;
	}
}
