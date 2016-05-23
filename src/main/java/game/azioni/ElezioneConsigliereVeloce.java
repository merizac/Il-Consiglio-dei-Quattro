package game.azioni;

import game.Regione;
import game.notify.ErrorParameterNotify;
import game.Consigliere;
import game.GameState;

public class ElezioneConsigliereVeloce extends AzioneVeloce {
	
	private Regione regione;
	private Consigliere consigliere;

	/**
	 * check the number of aiutanti and if giocatoreCorrente has 1 aiutante elect a counselor 
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		/*PassaggioParametri passaggioParametri = new PassaggioParametri(gameState);
		consigliere = passaggioParametri.selezionaConsiglieri();
		regione = passaggioParametri.selezionaRegione();*/
		
		if(!gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(1))
			gameState.notifyObserver(new ErrorParameterNotify("Errore: gli aiutanti non sono sufficienti"));
		
		else {
			Consigliere consigliereTolto = this.regione.getBalcone().aggiungiConsigliere(consigliere);
			gameState.getConsiglieri().add(consigliereTolto);
			setStatoTransizioneVeloce(gameState); 
			}
		
		}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Elezione Consigliere Veloce";
	}
	
}
