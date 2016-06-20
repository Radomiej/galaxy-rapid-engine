package galaxy.rapid.render;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import galaxy.rapid.asset.RapidAsset;
import galaxy.rapid.components.PositionComponent;
import galaxy.rapid.components.SpriteComponent;
import galaxy.rapid.components.TextComponent;
import galaxy.rapid.configuration.RapidConfiguration;

public enum TextRenderer implements Renderer {
	INSTANCE;

	public void render(Entity e, Batch batch) {
		ComponentMapper<TextComponent> textMapper = (ComponentMapper<TextComponent>) ComponentMapper.getFor(TextComponent.class, e.getWorld());
		ComponentMapper<PositionComponent> positionMapper = (ComponentMapper<PositionComponent>) ComponentMapper.getFor(PositionComponent.class,
				e.getWorld());

		// BodyComponent body = bodyMapper.get(e);
		PositionComponent position = positionMapper.get(e);

		if (position == null) {
			Gdx.app.error("SpriteRenderer", "PositionComponent doesn`t exist!");
			return;
		}

		TextComponent  textComponent = textMapper.get(e);
		String textToDraw = textComponent.getText();
		String assetName = textComponent.getBitmapAsset();
		BitmapFont bitmapFont = RapidAsset.INSTANCE.getBitmapFont(assetName);
		

		if (bitmapFont == null) {
			Gdx.app.error("TextRenderer", "Couldn`t load bitmap font: " + assetName);
			return;
		}

		
		float sizeX = bitmapFont.getSpaceWidth() * textToDraw.length();
		float sizeY = bitmapFont.getLineHeight();

		float posX = position.getPosition().x - sizeX / 2;
		float posY = position.getPosition().y - sizeY / 2;

		bitmapFont.setColor(textComponent.getColor());
		bitmapFont.draw(batch, textToDraw, posX, posY);
	}

}
