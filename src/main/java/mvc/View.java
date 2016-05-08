package mvc;

import game.Partita;

public abstract class View extends Observable implements Observer {
	
	private Partita partita;
	
	/**
	 * 
	 */
	public View(Partita partita) {
		this.partita=partita;
	}
	
	public abstract String scegliAzione();
	
	public abstract void scegliAzionePrincipale();
	
	public abstract void scegliCarte();

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <C> void update(C change) {
		// TODO Auto-generated method stub
		
	}

	
			
}

	

