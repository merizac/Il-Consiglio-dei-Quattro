package controller;

import java.util.List;
import game.GameState;
import utility.exception.AzioneNonEseguibile;

public class Stato10 implements Stato {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4129691255828354834L;
	private List<String> azioni;
	

	/**
	 * @param azioni
	 */
	public Stato10() {
		riempiAzioni();
	}

	private void riempiAzioni() {
		azioni.add("Azioni prinipali :");
		azioni.add("Eleggere un consigliere");
		azioni.add("Acquistare una tessera permesso di costruzione");
		azioni.add("Costruire un emporio usando una tessera permesso");
		azioni.add("costruire un emporio con l'aiuto del re");
	}

	@Override
	public void transizionePrincipale(GameState gameState) throws AzioneNonEseguibile {
		if(!gameState.isBonusAzionePrincipale()){
			gameState.nextPlayer();
			gameState.setStato(new StartEnd(gameState));
		}
		else
			gameState.setBonusAzionePrincipale(false); 
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
		return "Stato10";
	}

	

}
