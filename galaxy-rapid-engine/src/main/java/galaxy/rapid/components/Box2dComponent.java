package galaxy.rapid.components;

import com.artemis.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Box2dComponent extends Component {
	private Body body;
	private Fixture rectangleFixture;
	private Object userData;
	
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	@Override
	public String toString() {
		String info = "body pos: " + body.getPosition();
		return info;
	}

	public Fixture getRectangleFixture() {
		return rectangleFixture;
	}

	public void setRectangleFixture(Fixture rectangleFixture) {
		this.rectangleFixture = rectangleFixture;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((rectangleFixture == null) ? 0 : rectangleFixture.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Box2dComponent other = (Box2dComponent) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (rectangleFixture == null) {
			if (other.rectangleFixture != null)
				return false;
		} else if (!rectangleFixture.equals(other.rectangleFixture))
			return false;
		return true;
	}

	public Object getUserData() {
		return userData;
	}

	public void setUserData(Object userData) {
		this.userData = userData;
	}

}
