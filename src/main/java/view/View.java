package view;

import azioni.Azione;
import game.Model;
import game.GameState;
import utility.Observable;
import utility.Observer;

public class View extends Observable <Azione> implements Observer <Object>{

	private Model model;

	public View(GameState gameState, Model model){
		gameState.registerObserver(this);
		this.model=model;
	}
	
	public void input(String input){
	}

	@Override
	public void update(Object c) {
		
	}
	
}
