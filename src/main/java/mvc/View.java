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

	 public abstract String scegliTesseraScoperta();

	public abstract String erroreArrayList(String carta);

	public abstract String scegliCittà();

	public abstract String scegliTesseraPermesso();

	


	
			
}

	

