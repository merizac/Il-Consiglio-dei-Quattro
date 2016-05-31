package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.RegioneDTO;

public class ElezioneConsigliereVeloceDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4829189371404681263L;
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
	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}
	
}
