package view;

import game.azioni.Azione;
import game.GameState;
import game.Parser;
import utility.Observable;
import utility.Observer;

public abstract class View extends Observable <Azione> implements Observer <Object>{
	
	protected Parser parser;

	public View(GameState gameState, Parser parser){
		gameState.registerObserver(this);
		this.parser = parser;
	}
	
	public abstract void input(String input);

	@Override
	public void update(Object c) {
		
	}
}
