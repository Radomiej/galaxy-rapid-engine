package galaxy.rapid.configuration;

public enum Scale {
	HD(1) {
		@Override
		public int getDefaultWidth() {
			return 2048;
		}
	},
	SD(0.5f) {
		@Override
		public int getDefaultWidth() {
			return 1024;
		}
	},
	SMALL(0.46875f) {
		@Override
		public int getDefaultWidth() {
			return 960;
		}
	},
	TINY(0.3125f) {
		@Override
		public int getDefaultWidth() {
			return 640;
		}
	};

	private float scale;

	private Scale(float scaleMultiplexer) {
		scale = scaleMultiplexer;
	}

	public int getDefaultWidth() {
		return 1024;
	}

	public float getScaleValue() {
		return scale;
	}

	public static Scale getByWidth(float w) {
		if (w > 2048) {
			return Scale.HD;
		} else if (w >= 992) {
			return Scale.SD;
		} else if (w >= 800) {
			return Scale.SMALL;
		}
		return Scale.TINY;
	}
}
