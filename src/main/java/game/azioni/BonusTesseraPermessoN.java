package game.azioni;

import game.GameState;
import game.Regione;
import game.TesseraPermesso;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.BonusTesseraPermessoNDTO;

public class BonusTesseraPermessoN extends Azione {

	/**
	 * Prendi una tesseraPermesso a faccia in su senza pagarne il costo
	 */
	private final int ID=15;
	private Regione regione;
	private TesseraPermesso tesseraScoperta;
	
	@Override
	public void eseguiAzione(GameState gameState){
		
		regione.getTesserePermessoScoperte().remove(tesseraScoperta);
		gameState.getGiocatoreCorrente().getTesserePermesso().add(tesseraScoperta);
		
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
	public TesseraPermesso getTesseraScoperta() {
		return tesseraScoperta;
	}

	/**
	 * @param indiceTesseraScoperta the indiceTesseraScoperta to set
	 */
	public void setTesseraScoperta(TesseraPermesso tesseraScoperta) {
		this.tesseraScoperta = tesseraScoperta;
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new BonusTesseraPermessoNDTO();
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
		BonusTesseraPermessoN other = (BonusTesseraPermessoN) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}
