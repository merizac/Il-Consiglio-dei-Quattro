package view;

import game.GameState;
import game.Parser;
import game.azioni.Azione;

public class ViewCLI extends View{

	public ViewCLI(GameState gameState, Parser parser) {
		super(gameState, parser);
	}

	@Override
	public void input(String input) {
		
		Azione azione = parser.parser(input);
		this.notifyObserver(azione);
	}
	
	@Override
	public void update(Object c) {
		
	}

}
