package galaxy.rapid.components;

import com.artemis.Component;

public class PlayerComponent extends Component {
	private String playerName;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
