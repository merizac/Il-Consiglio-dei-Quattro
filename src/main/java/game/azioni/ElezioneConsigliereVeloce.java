package game.azioni;

import game.Regione;
import game.Consigliere;
import game.GameState;

public class ElezioneConsigliereVeloce extends AzioneVeloce {
	
	private Regione regione;
	private Consigliere consigliere;
	/**
	 * constructor
	 * @param gameState
	 * @param regione 
	 * @param consigliere 
	 * @param balcone
	 * @param consigliere
	 */
	public ElezioneConsigliereVeloce(GameState gameState, Regione regione, Consigliere consigliere) {
		super(gameState);
		this.regione=regione;
		this.consigliere=consigliere;
	}
	/**
	 * check the number of aiutanti and if giocatoreCorrente has 1 aiutante elect a counselor 
	 */
	@Override
	public boolean eseguiAzione() {
		/*PassaggioParametri passaggioParametri = new PassaggioParametri(gameState);
		consigliere = passaggioParametri.selezionaConsiglieri();
		regione = passaggioParametri.selezionaRegione();*/
		
		if(gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(1)){
			Consigliere consigliereTolto = this.regione.getBalcone().aggiungiConsigliere(consigliere);
			this.gameState.getConsiglieri().add(consigliereTolto);
			setStatoTransizioneVeloce(); 
			return true;
		}
		
		else return false;
	}
}
