package game.azioni;

import game.Consigliere;
import game.GameState;
import game.Regione;

public class ElezioneConsigliere extends AzionePrincipale {

	private Regione regione;
	private Consigliere consigliere;
	
/**
 * Remove and add consigliere from the selected balcony
 * Add consigliereTolto to the tabellone
 * player win 4 coins
 */
	@Override
	public void eseguiAzione(GameState gameState) {
		/*PassaggioParametri passaggioParametri = new PassaggioParametri(gameState);
		consigliere = passaggioParametri.selezionaConsiglieri();
		regione = passaggioParametri.selezionaRegione();*/

		Consigliere consigliereTolto = this.regione.getBalcone().aggiungiConsigliere(consigliere);
		gameState.getConsiglieri().add(consigliereTolto);
		gameState.getGiocatoreCorrente().aumentaRicchezza(4);
		setStatoTransizionePrincipale(gameState); 
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Elezione Consigliere";
	}
	
	

}
