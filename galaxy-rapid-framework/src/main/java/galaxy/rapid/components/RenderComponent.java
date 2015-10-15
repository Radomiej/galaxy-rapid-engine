package galaxy.rapid.components;

import com.artemis.Component;

public class RenderComponent extends Component {
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
}
