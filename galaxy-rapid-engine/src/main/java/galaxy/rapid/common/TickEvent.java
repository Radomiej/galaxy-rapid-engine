package galaxy.rapid.common;

import net.mostlyoriginal.api.event.common.Event;

public class TickEvent {
	private Event postingObject;
	private float invokeTime;
	private int repeatCount;
	private float repeatDeley;

	public TickEvent() {
	}

	public TickEvent(Event postingObject, float invokeTime) {
		this(postingObject, invokeTime, 0, 0);
	}

	public TickEvent(Event postingObject, float invokeTime, int repeatCount, float repeatDeley) {
		this.postingObject = postingObject;
		this.invokeTime = invokeTime;
		this.repeatCount = repeatCount;
		this.repeatDeley = repeatDeley;
	}

	public Event getPostingObject() {
		return postingObject;
	}

	public void setPostingObject(Event postingObject) {
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
