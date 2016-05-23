package game.query;

import java.io.Serializable;
import game.GameState;
import game.notify.Notify;

public abstract class Query implements Serializable {

	private static final long serialVersionUID = 3612850427337974953L;
	
	public abstract Notify perform(GameState gameState);

}
