package mvc;

import java.util.ArrayList;
import game.Partita;

public abstract class View extends Observable implements Observer {
	
	protected Partita partita;
	
	/**
	 * 
	 */
	public View(Partita partita) {
		this.partita=partita;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <C> void update(C change) {
		// TODO Auto-generated method stub
		
	}

	
	public abstract String scegliAzione();
	
	public abstract void scegliAzionePrincipale();
		
	public abstract void scegliAzioneVeloce();
	
	public abstract String possibilitàAzioneVeloce();

	public abstract ArrayList<String> scegliCarte();
	
	public abstract String richiestaParametro(String string);
	


	
			
}

	

