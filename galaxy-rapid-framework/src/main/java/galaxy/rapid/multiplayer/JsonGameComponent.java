package galaxy.rapid.multiplayer;

import java.util.List;
import java.util.UUID;

import com.artemis.Component;

import galaxy.rapid.common.ComponentsBag;

public class JsonGameComponent {
	private Long mostSignBit;
	private Long lestSignBit;
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
		return "uuid: " + mostSignBit.byteValue() + " | " + lestSignBit.byteValue();
	}

	public long getMostSignBit() {
		return mostSignBit;
	}

	public void setMostSignBit(long mostSignBit) {
		this.mostSignBit = mostSignBit;
	}

	public long getLestSignBit() {
		return lestSignBit;
	}

	public void setLestSignBit(long lestSignBit) {
		this.lestSignBit = lestSignBit;
	}

	public ComponentsBag getComponents() {
		return components;
	}

	public void setComponents(ComponentsBag components) {
		this.components = components;
	}
}
