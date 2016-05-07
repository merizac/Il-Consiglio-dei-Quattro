package mvc;

import azioni.*;
import game.Partita;

public class Controllore extends Observable implements Observer {

	private final Partita partita;
	
	/**
	 * @param partita
	 */
	public Controllore(Partita partita) {
		this.partita = partita;
	}
	
	
	@Override
	public void update() {
		
		
	}

	@Override
	public <C> void update(C change) {
		switch((String) change){
			case "1p":
				AzionePrincipale elezioneConsigliere = this.partita.getGiocatoreCorrente().getAzioniPrincipali().get(1);
		}
			
	}

	
}
