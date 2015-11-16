package galaxy.rapid.multiplayer;

import java.util.UUID;

public class PartUuid {
	private Long mostSignBit = 0l;
	private Long lestSignBit = 0l;

	public PartUuid() {
	}

	public PartUuid(UUID uuidFromEntity) {
		mostSignBit = uuidFromEntity.getMostSignificantBits();
		lestSignBit = uuidFromEntity.getLeastSignificantBits();
	}

	public Long getLestSignBit() {
		return lestSignBit;
	}

	public void setLestSignBit(Long lestSignBit) {
		this.lestSignBit = lestSignBit;
	}

	public Long getMostSignBit() {
		return mostSignBit;
	}

	public void setMostSignBit(Long mostSignBit) {
		this.mostSignBit = mostSignBit;
	}
}
