package game.azioni;

import game.Regione;
import game.notify.ErrorParameterNotify;
import game.notify.GiocatoreNotify;
import game.Consigliere;
import game.GameState;

public class ElezioneConsigliereVeloce extends AzioneVeloce {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1882277645819280237L;
	private Regione regione;
	private Consigliere consigliere;

	
	/**
	 * @return the regione
	 */
	public Regione getRegione() {
		return regione;
	}




	/**
	 * @param regione the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}




	/**
	 * @return the consigliere
	 */
	public Consigliere getConsigliere() {
		return consigliere;
	}




	/**
	 * @param consigliere the consigliere to set
	 */
	public void setConsigliere(Consigliere consigliere) {
		this.consigliere = consigliere;
	}




	/**
	 * check the number of aiutanti and if giocatoreCorrente has 1 aiutante
	 * elect a counselor
	 */
	@Override
	public void eseguiAzione(GameState gameState) {

		if (!gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(1)) {
			gameState.notifyObserver(new ErrorParameterNotify("Errore: gli aiutanti non sono sufficienti"));
			return;
		}

		else {
			Consigliere consigliereTolto = this.regione.getBalcone().aggiungiConsigliere(consigliere);
			gameState.getConsiglieri().add(consigliereTolto);
			setStatoTransizioneVeloce(gameState);
			gameState.notifyObserver(new GiocatoreNotify());
		}

	}
}
