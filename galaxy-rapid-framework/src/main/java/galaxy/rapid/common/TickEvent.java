package galaxy.rapid.common;

public class TickEvent {
	private Object postingObject;
	private float invokeTime;
	private int repeatCount;
	private float repeatDeley;

	public TickEvent() {
	}

	public TickEvent(Object postingObject, float invokeTime) {
		this(postingObject, invokeTime, 0, 0);
	}

	public TickEvent(Object postingObject, float invokeTime, int repeatCount, float repeatDeley) {
		this.postingObject = postingObject;
		this.invokeTime = invokeTime;
		this.repeatCount = repeatCount;
		this.repeatDeley = repeatDeley;
	}

	public Object getPostingObject() {
		return postingObject;
	}

	public void setPostingObject(Object postingObject) {
		this.postingObject = postingObject;
	}

	public float getInvokeTime() {
		return invokeTime;
	}

	public void setInvokeTime(float invokeTime) {
		this.invokeTime = invokeTime;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public float getRepeatDeley() {
		return repeatDeley;
	}

	public void setRepeatDeley(float repeatDeley) {
		this.repeatDeley = repeatDeley;
	}
}
