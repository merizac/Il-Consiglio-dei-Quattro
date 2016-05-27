package view;

import game.azioni.Azione;
import game.notify.Notify;
import java.util.Scanner;
import utility.Observable;
import utility.Observer;

public abstract class View extends Observable <Azione> implements Observer <Notify>{
	

	public View(){
	}
	

	@Override
	public void update(Notify notify) {
		
	}
	
}
