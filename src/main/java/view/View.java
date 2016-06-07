package view;

import game.GameState;
import game.azioni.Azione;
import game.notify.Notify;
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
