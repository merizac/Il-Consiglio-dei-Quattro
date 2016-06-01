package game.azioni;

import game.Consigliere;
import game.GameState;
import game.Regione;
import game.notify.GameStateNotify;
import game.notify.GiocatoreDTONotify;

public class ElezioneConsigliere extends AzionePrincipale {

	private Regione regione;
	private Consigliere consigliere;
	
	/**
	 * @param regione the regione to set
	 */
	public void setRegione(Regione regione) {
		if(regione==null)
			throw new NullPointerException("La regione deve essere definita");
		this.regione = regione;
	}

	/**
	 * @param consigliere the consigliere to set
	 */
	public void setConsigliere(Consigliere consigliere) {
		if(consigliere==null)
			throw new NullPointerException("La regione deve essere definita");
		this.consigliere = consigliere;
	}



/**
 * Remove and add consigliere from the selected balcony
 * Add consigliereTolto to the tabellone
 * player win 4 coins
 */
	@Override
	public void eseguiAzione(GameState gameState) {


		Consigliere consigliereTolto = this.regione.getBalcone().aggiungiConsigliere(consigliere);
		gameState.getConsiglieri().add(consigliereTolto);
		gameState.getGiocatoreCorrente().aumentaRicchezza(4);
		setStatoTransizionePrincipale(gameState); 
		gameState.notifyObserver(new GameStateNotify(gameState));
		gameState.notifyObserver(new GiocatoreDTONotify(gameState.getGiocatoreCorrente()));
	}

}
