package galaxy.rapid.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import galaxy.rapid.common.RenderBody;
import galaxy.rapid.common.RenderOffset;

public class RenderComponent extends Component {
	// TODO zrobi wsparcie do foramtowania spritów
	private RenderOffset widthOffset = RenderOffset.CENTER;
	private RenderOffset heightOffset = RenderOffset.CENTER;
	private Vector2 cornerPosition = new Vector2();
	private RenderBody renderBody;
	private Color color;
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

	public RenderOffset getWidthOffset() {
		return widthOffset;
	}

	public void setWidthOffset(RenderOffset widthOffset) {
		this.widthOffset = widthOffset;
	}

	public RenderOffset getHeightOffset() {
		return heightOffset;
	}

	public void setHeightOffset(RenderOffset heightOffset) {
		this.heightOffset = heightOffset;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
