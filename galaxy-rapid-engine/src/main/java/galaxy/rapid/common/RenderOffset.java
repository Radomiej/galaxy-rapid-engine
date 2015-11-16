package galaxy.rapid.common;

public enum RenderOffset {
	CENTER {

		@Override
		float getPosition(float spriteX, float spriteSize, float bodyX, float bodySize) {
			float result = bodyX +  (bodySize / 2) - (spriteSize / 2);
			return result;
		}

	},
	MAXIMUM {

		@Override
		float getPosition(float spriteX, float spriteSize, float bodyX, float bodySize) {
			float result = bodyX +  bodySize;
			return result;
		}

	},
	HALF_BODY_SIZE {

		@Override
		float getPosition(float spriteX, float spriteSize, float bodyX, float bodySize) {
			float result = bodyX + bodySize / 2;
			return result;
		}

	};

	public float getWidthOffset(RenderBody spriteBody, RenderBody body) {
		return getPosition(spriteBody.getX(), spriteBody.getWidth(), body.getX(), body.getWidth());
	}

	public float getHeightOffset(RenderBody spriteBody, RenderBody body) {
		return getPosition(spriteBody.getY(), spriteBody.getHeight(), body.getY(), body.getHeight());
	}

	abstract float getPosition(float spriteX, float spriteSize, float bodyX, float bodySize);
}
