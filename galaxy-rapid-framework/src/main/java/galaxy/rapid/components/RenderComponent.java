package galaxy.rapid.components;

import com.artemis.Component;

public class RenderComponent extends Component {	
	private int layout;
	private int orderZ;

	public int getLayout() {
		return layout;
	}

	public void setLayout(int layout) {
		this.layout = layout;
	}

	public int getOrderZ() {
		return orderZ;
	}

	public void setOrderZ(int orderZ) {
		this.orderZ = orderZ;
	}
}
