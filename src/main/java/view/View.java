package view;

import game.azioni.Azione;
import game.notify.Notify;

import java.util.Scanner;

import game.GameState;
import game.Model;
import game.ParserAzione;
import utility.Observable;
import utility.Observer;

public abstract class View extends Observable <Azione> implements Observer <Notify>{
	
	protected ParserAzione parser;
	protected Model model;

	public View(GameState gameState, ParserAzione parser){
		gameState.registerObserver(this);
		this.parser = parser;
		this.model=gameState;
	}
	
	public abstract void input(Scanner input);

	@Override
	public void update(Notify notify) {
		
	}
	
}
