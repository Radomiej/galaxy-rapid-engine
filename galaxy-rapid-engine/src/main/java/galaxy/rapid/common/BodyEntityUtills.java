package galaxy.rapid.common;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.math.Rectangle;

import galaxy.rapid.components.BodyComponent;
import galaxy.rapid.components.PositionComponent;

public class BodyEntityUtills {
	
	private static RotatedRectangle rec1 = new RotatedRectangle(new Rectangle(), 0);
	private static RotatedRectangle rec2 = new RotatedRectangle(new Rectangle(), 0);

	public static boolean isCollisionBetween(Entity entity1, Entity entity2) {
		if(entity1.getWorld() != entity2.getWorld()){ return false;}
		World world = entity1.getWorld();
		ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class, world);
		ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class, world);
		
		BodyComponent body1 = bodyMapper.get(entity1);
		BodyComponent body2 = bodyMapper.get(entity2);
		
		
		PositionComponent pos1 = positionMapper.get(entity1);
		PositionComponent pos2 = positionMapper.get(entity2);
		
		rec1.getCollisionRectangle().set(pos1.getPosition().x, pos1.getPosition().y, body1.getSize().x,
				body1.getSize().y);
		rec1.getOrigin().set(body1.getOrigin());
		rec1.setRotation(pos1.getRotation());

		rec2.getCollisionRectangle().set(pos2.getPosition().x, pos2.getPosition().y, body2.getSize().x,
				body2.getSize().y);
		rec2.getOrigin().set(body2.getOrigin());
		rec2.setRotation(pos2.getRotation());
		return rec1.intersects(rec2);
	}

	public static Rectangle getBodyRectangle(BodyComponent bodyComponent, PositionComponent positionComponent) {
		return new Rectangle(positionComponent.getPosition().x, positionComponent.getPosition().y, bodyComponent.getSize().x,
				bodyComponent.getSize().y);
	}

	public static void setEntitySizeByRectangle(BodyComponent body, Rectangle rectangle) {
		body.getSize().set(rectangle.width, rectangle.height);
		body.getOrigin().set(rectangle.width / 2, rectangle.height / 2);
	}

	public static void setEntityPositionByRectangle(PositionComponent position, Rectangle rectangle, float rotation) {
		position.getPosition().set(rectangle.x, rectangle.y);
		position.setRotation(rotation);
	}

	public static void setBodyByBody(BodyComponent sourceBody, BodyComponent targetBody) {
		sourceBody.getSize().set(targetBody.getSize());
		sourceBody.getOrigin().set(targetBody.getOrigin());
	}

}
