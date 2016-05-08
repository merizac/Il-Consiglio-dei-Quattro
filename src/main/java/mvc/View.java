package mvc;

import java.util.ArrayList;

import game.Partita;
import game.Regione;

public abstract class View extends Observable implements Observer {
	
	protected Partita partita;
	
	/**
	 * 
	 */
	public View(Partita partita) {
		this.partita=partita;
	}
	
	public abstract String scegliAzione();
	
	public abstract void scegliAzionePrincipale();
	
	public abstract ArrayList<String> scegliCarte();
	
	public abstract void scegliAzioneVeloce();
	
	public abstract String possibilitàAzioneVeloce();


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <C> void update(C change) {
		// TODO Auto-generated method stub
		
	}

	 public abstract String scegliRegione();

	 public abstract int scegliTesseraScoperta(Regione regione);

	public abstract int erroreArrayList(String carta);

	


	
			
}

	

