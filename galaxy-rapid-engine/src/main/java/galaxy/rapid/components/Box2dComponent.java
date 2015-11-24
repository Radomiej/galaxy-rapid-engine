package galaxy.rapid.components;


import com.artemis.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

import galaxy.rapid.components.marker.TransientNetworkElement;

public class Box2dComponent extends Component implements TransientNetworkElement{
	private transient Body body;
	private transient Fixture fixture;

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}
	
	@Override
	public String toString() {
		String info = "body pos: " + body.getPosition();
		return info;
	}
}
