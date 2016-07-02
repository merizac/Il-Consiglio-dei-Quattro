package server.model.azioni.azioniVeloci;

import server.model.game.Balcone;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.notify.MessageNotify;
import java.util.Arrays;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.ElezioneConsigliereVeloceDTO;

public class ElezioneConsigliereVeloce extends AzioneVeloce {

	private Balcone balcone;
	private Consigliere consigliere;

	/**
	 * @return the balcone
	 * 
	 */
	public Balcone getRegione() {
		return balcone;
	}

	/**
	 * @param balcone
	 *            the balcone to set
	 */
	public void setBalcone(Balcone balcone) {
		if (balcone == null)
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
	 * @param consigliere
	 *            the consigliere to set
	 */
	public void setConsigliere(Consigliere consigliere) {
		if (consigliere == null)
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
					Arrays.asList(gameState.getGiocatoreCorrente()), false));
			return;
		}

		else {
			Consigliere consigliereTolto = this.balcone.aggiungiConsigliere(consigliere);
			gameState.getConsiglieri().remove(consigliere);
			gameState.getConsiglieri().add(consigliereTolto);
			notify(gameState);
			setStatoTransizioneVeloce(gameState);
		}

	}

	/**
	 * @return DTO action
	 */
	@Override
	public AzioneDTO getAzioneDTO() {
		return new ElezioneConsigliereVeloceDTO();
	}

}
