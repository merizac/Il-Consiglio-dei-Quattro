package server.model.azioni.azioniVeloci;

import server.model.game.Balcone;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.notify.GameStateNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.MessageNotify;

import java.util.Arrays;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.ElezioneConsigliereDTO;

public class ElezioneConsigliereVeloce extends AzioneVeloce {
	
	private Balcone balcone;
	private final int ID=7;
	private Consigliere consigliere;

	
	/**
	 * @return the balcone
	 * 
	 */
	public Balcone getRegione() {
		return balcone;
	}




	/**
	 * @param balcone the balcone to set
	 */
	public void setBalcone(Balcone balcone) {
		if(balcone==null)
			throw new NullPointerException("Il balcone passato è null");
		this.balcone = balcone;
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
			Consigliere consigliereTolto = this.balcone.aggiungiConsigliere(consigliere);
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
