package game.azioni;

import game.Regione;
import game.notify.MessageNotify;
import game.notify.GameStateNotify;
import game.notify.GiocatoreNotify;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.ElezioneConsigliereDTO;

import java.util.Arrays;

import game.Consigliere;
import game.GameState;

public class ElezioneConsigliereVeloce extends AzioneVeloce {

	private final int ID=7;
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
		if(regione==null)
			throw new NullPointerException("La regione passata è null");
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
		if(consigliere==null)
			throw new NullPointerException("Il consigliere passato è null");
		this.consigliere = consigliere;
	}




	/**
	 * check the number of aiutanti and if giocatoreCorrente has 1 aiutante
	 * elect a counselor
	 */
	@Override
	public void eseguiAzione(GameState gameState) {

		if (!gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(1)) {
			gameState.notifyObserver(new MessageNotify("Errore: gli aiutanti non sono sufficienti",
					Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}

		else {
			Consigliere consigliereTolto = this.regione.getBalcone().aggiungiConsigliere(consigliere);
			gameState.getConsiglieri().remove(consigliere);
			gameState.getConsiglieri().add(consigliereTolto);
			
			gameState.notifyObserver(new GameStateNotify(gameState, gameState.getGiocatori()));
			gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(),
					Arrays.asList(gameState.getGiocatoreCorrente())));
			setStatoTransizioneVeloce(gameState);
		}

	}




	@Override
	public AzioneDTO getAzioneDTO() {
		return new ElezioneConsigliereDTO();
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElezioneConsigliereVeloce other = (ElezioneConsigliereVeloce) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}
