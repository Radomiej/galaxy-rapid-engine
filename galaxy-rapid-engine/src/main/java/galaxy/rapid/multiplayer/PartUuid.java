package galaxy.rapid.multiplayer;

import java.util.UUID;

public class PartUuid {
	private Long mostSignBits = 0l;
	private Long lestSignBits = 0l;

	public PartUuid() {
	}

	public PartUuid(UUID uuidFromEntity) {
		mostSignBits = uuidFromEntity.getMostSignificantBits();
		lestSignBits = uuidFromEntity.getLeastSignificantBits();
	}

	public Long getLestSignBit() {
		return lestSignBits;
	}

	public void setLestSignBit(Long lestSignBit) {
		this.lestSignBits = lestSignBit;
	}

	public Long getMostSignBit() {
		return mostSignBits;
	}

	public void setMostSignBit(Long mostSignBit) {
		this.mostSignBits = mostSignBit;
	}

	public UUID getUuid() {
		return new UUID(mostSignBits, lestSignBits);
	}
}
