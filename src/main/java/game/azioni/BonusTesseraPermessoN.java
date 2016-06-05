package game.azioni;

import game.GameState;
import game.Regione;
import game.TesseraPermesso;

public class BonusTesseraPermessoN extends Azione {

	/**
	 * Prendi una tesseraPermesso a faccia in su senza pagarne il costo
	 */
	
	private Regione regione;
	private int indiceTesseraScoperta;
	
	@Override
	public void eseguiAzione(GameState gameState){
		
		TesseraPermesso tesseraScelta = regione.getTesserePermessoScoperte().remove(indiceTesseraScoperta);
		gameState.getGiocatoreCorrente().getTesserePermesso().add(tesseraScelta);
		
		regione.getTesserePermessoScoperte().add(regione.getMazzoTesserePermesso().pescaCarte());
	}

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
	 * @return the indiceTesseraScoperta
	 */
	public int getIndiceTesseraScoperta() {
		return indiceTesseraScoperta;
	}

	/**
	 * @param indiceTesseraScoperta the indiceTesseraScoperta to set
	 */
	public void setIndiceTesseraScoperta(int indiceTesseraScoperta) {
		this.indiceTesseraScoperta = indiceTesseraScoperta;
	}
}
