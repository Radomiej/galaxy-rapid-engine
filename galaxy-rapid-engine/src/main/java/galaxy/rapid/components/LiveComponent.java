package galaxy.rapid.components;

import com.artemis.Component;

public class LiveComponent extends Component {
	private float hp;
	private float hpMax;

	public float getHp() {
		return hp;
	}

	public void setHp(float hp) {
		this.hp = hp;
	}

	public float getHpMax() {
		return hpMax;
	}

	public void setHpMax(float hpMax) {
		this.hpMax = hpMax;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(hp);
		result = prime * result + Float.floatToIntBits(hpMax);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LiveComponent other = (LiveComponent) obj;
		if (Float.floatToIntBits(hp) != Float.floatToIntBits(other.hp))
			return false;
		if (Float.floatToIntBits(hpMax) != Float.floatToIntBits(other.hpMax))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LiveComponent [hp=" + hp + ", hpMax=" + hpMax + "]";
	}
}
