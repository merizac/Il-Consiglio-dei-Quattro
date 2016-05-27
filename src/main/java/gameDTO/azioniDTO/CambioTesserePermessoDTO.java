package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.RegioneDTO;

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

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}
	
}
