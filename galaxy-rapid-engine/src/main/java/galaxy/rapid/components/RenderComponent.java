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

	private boolean render;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((cornerPosition == null) ? 0 : cornerPosition.hashCode());
		result = prime * result + (flipX ? 1231 : 1237);
		result = prime * result + (flipY ? 1231 : 1237);
		result = prime * result + ((heightOffset == null) ? 0 : heightOffset.hashCode());
		result = prime * result + layer;
		result = prime * result + orderZ;
		result = prime * result + ((renderBody == null) ? 0 : renderBody.hashCode());
		result = prime * result + ((widthOffset == null) ? 0 : widthOffset.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RenderComponent other = (RenderComponent) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (cornerPosition == null) {
			if (other.cornerPosition != null)
				return false;
		} else if (!cornerPosition.equals(other.cornerPosition))
			return false;
		if (flipX != other.flipX)
			return false;
		if (flipY != other.flipY)
			return false;
		if (heightOffset != other.heightOffset)
			return false;
		if (layer != other.layer)
			return false;
		if (orderZ != other.orderZ)
			return false;
		if (renderBody == null) {
			if (other.renderBody != null)
				return false;
		} else if (!renderBody.equals(other.renderBody))
			return false;
		if (widthOffset != other.widthOffset)
			return false;
		return true;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

}
