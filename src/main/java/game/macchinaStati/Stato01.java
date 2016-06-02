package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game.GameState;
import game.notify.AzioniNotify;
import utility.exception.AzioneNonEseguibile;

public class Stato01 implements Stato {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1159916777727661681L;
	private List<String> azioni;

	public Stato01(GameState gameState) {
		System.out.println(this);
		this.azioni = new ArrayList<String>();
		riempiAzioni();
		gameState.notifyObserver(new AzioniNotify(getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
		
	}

	private void riempiAzioni() {
		azioni.add("AZIONI VELOCI");
		azioni.add("Ingaggiare un aiutante [V1]");
		azioni.add("Cambiare le tessere permesso di costruzione [V2]");
		azioni.add("Mandare un aiutante ad eleggere un consigliere [V3]");
		azioni.add("Compiere un'azione principale[V4]");
		azioni.add("Oppure Passa il turno! [Passa]");
	}

	@Override
	public void transizioneVeloce(GameState gameState) throws AzioneNonEseguibile {
		if(!gameState.isUltimoGiro()){
		gameState.nextPlayer();
		gameState.setStato(new StartEnd(gameState));}
		else {
			if(gameState.lastNextPlayer());
			gameState.setStato(new StatoFinePartita());
		}
	}

	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		gameState.setStato(new Stato10(gameState));
		
	}
	

	@Override
	public List<String> getAzioni() {
		return azioni;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stato01";
	}


	
	

	
}
