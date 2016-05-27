package mapping.azioniDTO;

import mapping.gameToMap.ConsigliereDTO;
import mapping.gameToMap.RegioneDTO;

public class ElezioneConsigliereVeloceDTO implements AzioneDTO {

	private RegioneDTO regione;
	private ConsigliereDTO consigliere;
	/**
	 * @param regione
	 * @param consigliere
	 */
	public ElezioneConsigliereVeloceDTO(RegioneDTO regione, ConsigliereDTO consigliere) {
		super();
		this.regione = regione;
		this.consigliere = consigliere;
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
	/**
	 * @return the consigliere
	 */
	public ConsigliereDTO getConsigliere() {
		return consigliere;
	}
	/**
	 * @param consigliere the consigliere to set
	 */
	public void setConsigliere(ConsigliereDTO consigliere) {
		this.consigliere = consigliere;
	}
	
}
