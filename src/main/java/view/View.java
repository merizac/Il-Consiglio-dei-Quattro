package view;

import azioni.Azione;
import game.Model;
import game.GameState;
import utility.Observable;
import utility.Observer;

public class View extends Observable <Azione> implements Observer <Object>{

	private Model model;

	public View(GameState gameState){
		gameState.registerObserver(this);
		this.model=gameState;
	}
	
	public void input(String input){
	}

	@Override
	public void update(Object c) {
		// TODO Auto-generated method stub
		
	}
	
}
