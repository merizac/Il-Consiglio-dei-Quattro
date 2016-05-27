package mapping.azioniDTO;

import mapping.gameToMap.RegioneDTO;

public class CambioTesserePermessoDTO implements AzioneDTO {

	private RegioneDTO regione;

	/**
	 * @param regione
	 */
	public CambioTesserePermessoDTO(RegioneDTO regione) {
		super();
		this.regione = regione;
	}

	/**
	 * @return the regione
	 */
	public RegioneDTO getRegione() {
		return regione;
	}

	/**
	 * @param regione the regione to set
	 */
	public void setRegione(RegioneDTO regione) {
		this.regione = regione;
	}
	
}
