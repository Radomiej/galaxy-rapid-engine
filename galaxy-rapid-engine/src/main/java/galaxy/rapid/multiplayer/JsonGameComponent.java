package galaxy.rapid.multiplayer;

import java.util.List;
import java.util.UUID;

import com.artemis.Component;

import galaxy.rapid.common.ComponentsBag;

public class JsonGameComponent {
	private Long mostSignBits;
	private Long lestSignBits;
	private ComponentsBag components;

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
}
