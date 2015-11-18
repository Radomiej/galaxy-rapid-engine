package galaxy.rapid.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class BodyComponent extends Component {
	private Vector2 size, origin;
	
	public BodyComponent() {
		this(0, 0);
	}

	public BodyComponent(float width, float height) {
		this(width, height, 0, 0);
	}

	public BodyComponent(float width, float height, float originX, float originY) {
		size = new Vector2(width, height);
		origin = new Vector2(originX, originY);
	}

	public BodyComponent(BodyComponent body) {
		this(body.getSize().x, body.getSize().y, body.getOrigin().x, body.getOrigin().y);
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public Vector2 getOrigin() {
		return origin;
	}

	public void setOrigin(Vector2 origin) {
		this.origin = origin;
	}


	public void set(BodyComponent body) {
		size.set(body.size);
		origin.set(body.origin);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		BodyComponent other = (BodyComponent) obj;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BodyComponent [size=" + size + ", origin=" + origin + "]";
	}

}
