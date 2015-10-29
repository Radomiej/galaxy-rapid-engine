package galaxy.rapid.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

import galaxy.rapid.common.RenderBody;
import galaxy.rapid.common.RenderOffset;

public class RenderComponent extends Component {
	// TODO zrobi wsparcie do foramtowania spritów
	private RenderOffset wOffset = RenderOffset.CENTER;
	private RenderOffset hOffset = RenderOffset.CENTER;
	private Vector2 cornerPosition = new Vector2();
	private RenderBody renderBody;
	private boolean flipX, flipY;

	private int layer;
	private int orderZ;

	public RenderComponent() {
	}

	public RenderComponent(int orderZ) {
		this(orderZ, 0);
	}

	public RenderComponent(int orderZ, int layer) {
		this.layer = layer;
		this.orderZ = orderZ;
	}

	public int getLayout() {
		return layer;
	}

	public void setLayout(int layout) {
		this.layer = layout;
	}

	public int getOrderZ() {
		return orderZ;
	}

	public void setOrderZ(int orderZ) {
		this.orderZ = orderZ;
	}

	public boolean isFlipX() {
		return flipX;
	}

	public void setFlipX(boolean flipX) {
		this.flipX = flipX;
	}

	public boolean isFlipY() {
		return flipY;
	}

	public void setFlipY(boolean flipY) {
		this.flipY = flipY;
	}
}
