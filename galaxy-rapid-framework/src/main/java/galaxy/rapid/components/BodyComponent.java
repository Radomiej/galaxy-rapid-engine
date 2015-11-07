package galaxy.rapid.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class BodyComponent extends Component {
	private Vector2 position, size, origin;
	private float rotation;

	
	public BodyComponent() {
		this(0, 0);
	}

	public BodyComponent(float x, float y) {
		this(x, y, 1, 1);
	}

	public BodyComponent(float x, float y, float width, float height) {
		this(x, y, width, height, 0, 0);
	}

	public BodyComponent(float x, float y, float width, float height, float originX, float originY) {
		position = new Vector2(x, y);
		size = new Vector2(width, height);
		origin = new Vector2(originX, originY);
	}

	public BodyComponent(BodyComponent body) {
		this(body.getPosition().x, body.getPosition().y, body.getSize().x, body.getSize().y, body.getOrigin().x, body.getOrigin().y);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
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

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	@Override
	public String toString() {
		return "position: " + position + " size: " + size + " origin: " + origin + " rotation: " + rotation;
	}

	public void setCenterPosition(float x, float y) {
		position.x = x - (size.x / 2);
		position.y = y - (size.y / 2);
	}

	public void set(BodyComponent body) {
		position.set(body.position);
		size.set(body.size);
		origin.set(body.origin);
		rotation = body.rotation;
	}

}
