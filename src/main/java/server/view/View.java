package server.view;

import server.model.azioni.Azione;
import server.model.game.GameState;
import server.model.notify.Notify;
import utility.Observable;
import utility.Observer;

public abstract class View extends Observable <Azione> implements Observer <Notify>{
	

	public View(){
	}
	

	@Override
	public void update(Notify notify) {
		
	}
	public abstract void setGameState(GameState gameState);


	public abstract void disconnetti();
}
