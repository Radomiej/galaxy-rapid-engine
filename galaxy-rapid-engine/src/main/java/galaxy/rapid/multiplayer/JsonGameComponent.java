package galaxy.rapid.multiplayer;

import galaxy.rapid.common.ComponentsBag;

public class JsonGameComponent {
	private Long mostSignBits;
	private Long lestSignBits;
	private ComponentsBag components;
	private ComponentsBag removedComponents;
	private boolean delete = false;

//	public String getJsonComponents() {
//		return jsonComponents;
//	}
//
//	public void setJsonComponents(String jsonComponents) {
//		this.jsonComponents = jsonComponents;
//	}
	
	@Override
	public String toString() {
		return "uuid: " + mostSignBits.byteValue() + " | " + lestSignBits.byteValue();
	}

	public long getMostSignBit() {
		return mostSignBits;
	}

	public void setMostSignBit(long mostSignBit) {
		this.mostSignBits = mostSignBit;
	}

	public long getLestSignBit() {
		return lestSignBits;
	}

	public void setLestSignBit(long lestSignBit) {
		this.lestSignBits = lestSignBit;
	}

	public ComponentsBag getComponents() {
		return components;
	}

	public void setComponents(ComponentsBag components) {
		this.components = components;
	}

	public ComponentsBag getRemovedComponents() {
		return removedComponents;
	}

	public void setRemovedComponents(ComponentsBag removedComponents) {
		this.removedComponents = removedComponents;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}
}
