package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game.GameState;
import game.notify.AzioniNotify;
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
	public Stato10(GameState gameState) {
		System.out.println(this);
		riempiAzioni();
		gameState.notifyObserver(new AzioniNotify(getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}

	private void riempiAzioni() {
		this.azioni = new ArrayList<String>();
		azioni.add("AZIONI PRINCIPALI:");
		azioni.add("Eleggere un consigliere [P1]");
		azioni.add("Acquistare una tessera permesso di costruzione [P2]");
		azioni.add("Costruire un emporio usando una tessera permesso [P3]");
		azioni.add("costruire un emporio con l'aiuto del re [P4]");
	}

	@Override
	public void transizionePrincipale(GameState gameState) throws AzioneNonEseguibile {

		if (!gameState.isBonusAzionePrincipale()) {
			gameState.nextPlayer();
			gameState.prossimoTurno();

			if (gameState.getNumeroTurni() != gameState.getGiocatori().size())
				gameState.setStato(new StartEnd(gameState));
			else
				gameState.setStato(new StatoOffertaMarket(gameState));
		} else
			gameState.setBonusAzionePrincipale(false);
	}

	@Override
	public List<String> getAzioni() {
		return azioni;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stato10";
	}

}
