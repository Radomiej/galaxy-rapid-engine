package galaxy.rapid.common;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import galaxy.rapid.components.BodyComponent;

public class BodyUtills {
	public static void setCenterBodyInPosition(Vector2 centerPosition, BodyComponent body) {
		float halfWidth = body.getSize().x / 2;
		float halfHeight = body.getSize().y / 2;

		float left = centerPosition.x - halfWidth;
		float bottom = centerPosition.y - halfHeight;
		body.getPosition().set(left, bottom);
	}

	private static RotatedRectangle rec1 = new RotatedRectangle(new Rectangle(), 0);
	private static RotatedRectangle rec2 = new RotatedRectangle(new Rectangle(), 0);

	public static boolean isCollisionBetween(BodyComponent body1, BodyComponent body2) {
		rec1.getCollisionRectangle().set(body1.getPosition().x, body1.getPosition().y, body1.getSize().x, body1.getSize().y);
		rec1.getOrigin().set(body1.getOrigin());
		rec1.setRotation(body1.getRotation());
		
		rec2.getCollisionRectangle().set(body2.getPosition().x, body2.getPosition().y, body2.getSize().x, body2.getSize().y);
		rec2.getOrigin().set(body2.getOrigin());
		rec2.setRotation(body2.getRotation());
		return rec1.intersects(rec2);
	}
	
	
}
