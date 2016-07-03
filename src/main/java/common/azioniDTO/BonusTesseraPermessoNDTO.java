package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioniSetParametri.BonusTesseraPermessoParametri;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.azioni.Azione;
import utility.ParameterException;

public class BonusTesseraPermessoNDTO implements AzioneDTO, AzioneParametri {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 5768338832160630143L;
	/**
	 * region 
	 */
	private RegioneDTO regione;
	/**
	 * permit tile from the region selected
	 */
	private TesseraPermessoDTO tesseraScoperta;

	
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
	public TesseraPermessoDTO getTesseraScoperta() {
		return tesseraScoperta;
	}



	/**
	 * @param indiceTesseraScoperta the indiceTesseraScoperta to set
	 */
	public void setTesseraScoperta(TesseraPermessoDTO tesseraScoperta) {
		this.tesseraScoperta = tesseraScoperta;
	}
	

	/**
	 * visitor pattern
	 */
	@Override
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return azioneVisitor.visit(this);	
	}



	/*
	 * to string
	 */
	@Override
	public String toString() {
		return "BonusTesseraPermesso [B1]";
	}


	/**
	 * set parameter
	 */
	@Override
	public BonusTesseraPermessoParametri parametri() {
		return new BonusTesseraPermessoParametri(this);
	}

}
