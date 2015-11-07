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
		rec1.getCollisionRectangle().set(body1.getPosition().x, body1.getPosition().y, body1.getSize().x,
				body1.getSize().y);
		rec1.getOrigin().set(body1.getOrigin());
		rec1.setRotation(body1.getRotation());

		rec2.getCollisionRectangle().set(body2.getPosition().x, body2.getPosition().y, body2.getSize().x,
				body2.getSize().y);
		rec2.getOrigin().set(body2.getOrigin());
		rec2.setRotation(body2.getRotation());
		return rec1.intersects(rec2);
	}

	public static Rectangle getBodyRectangle(BodyComponent bodyComponent) {
		return new Rectangle(bodyComponent.getPosition().x, bodyComponent.getPosition().y, bodyComponent.getSize().x,
				bodyComponent.getSize().y);
	}

	public static void setBodySizeByRectangle(BodyComponent body, Rectangle rectangle) {
		setBodySizeByRectangle(body, rectangle, 0);
	}

	public static void setBodySizeByRectangle(BodyComponent body, Rectangle rectangle, float rotation) {
		body.getPosition().set(rectangle.x, rectangle.y);
		body.getSize().set(rectangle.width, rectangle.height);
		body.setRotation(rotation);
	}

	public static Vector2 getCenterVector(BodyComponent bodyFollower) {
		Vector2 center = new Vector2(bodyFollower.getPosition().x + bodyFollower.getSize().x, bodyFollower.getPosition().y + bodyFollower.getSize().y);
		return center;
	}

	public static void setBodyByBody(BodyComponent sourceBody, BodyComponent targetBody) {
		sourceBody.getPosition().set(targetBody.getPosition());
		sourceBody.getSize().set(targetBody.getSize());
		sourceBody.getOrigin().set(targetBody.getOrigin());
		sourceBody.setRotation(targetBody.getRotation());
	}

}
