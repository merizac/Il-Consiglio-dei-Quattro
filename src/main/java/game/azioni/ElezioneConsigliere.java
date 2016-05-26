package game.azioni;

import game.Consigliere;
import game.GameState;
import game.Regione;
import game.notify.GiocatoreNotify;

public class ElezioneConsigliere extends AzionePrincipale {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3036409471209884102L;
	private Regione regione;
	private Consigliere consigliere;
	
	/**
	 * @param regione the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}

	/**
	 * @param consigliere the consigliere to set
	 */
	public void setConsigliere(Consigliere consigliere) {
		this.consigliere = consigliere;
	}



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
		System.out.println(gameState.getStato());
		gameState.notifyObserver(new GiocatoreNotify());
		
	}

}
