package galaxy.rapid.scene2d.json;

import com.badlogic.gdx.utils.Align;

public enum JsonAlign {
	TOP(Align.top), BOTTOM(Align.bottom), LEFT(Align.left), RIGHT(Align.right), TOP_LEFT(Align.topLeft), TOP_RIGHT(Align.topRight), BOTTOM_LEFT(Align.bottomLeft), BOTTOM_RIGHT(Align.bottomRight), CENTER(Align.center);
	
	private final int valueAlign;
	
	private JsonAlign(int align) {
		valueAlign = align;
	}
	
	public int getAlign(){
		return valueAlign;
	}
}
