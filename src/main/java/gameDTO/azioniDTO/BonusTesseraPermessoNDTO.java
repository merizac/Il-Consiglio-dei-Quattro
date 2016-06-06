package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.RegioneDTO;

public class BonusTesseraPermessoNDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5768338832160630143L;
	private RegioneDTO regione;
	private int indiceTesseraScoperta;

	
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
	

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);	
	}

}
