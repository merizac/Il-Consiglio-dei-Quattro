package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioniSetParametri.CostruzioneTesseraPermessoParametri;
import common.gameDTO.CittàDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.azioni.Azione;
import utility.ParameterException;

public class CostruzioneTesseraPermessoDTO implements AzioneDTO, AzioneParametri {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = -2520930894055638122L;
	/**
	 * permit tile 
	 */
	private TesseraPermessoDTO tesseraPermesso;
	/**
	 * city from the permit tile
	 */
	private CittàDTO città;
	
	
	/**
	 * @return the tesseraPermesso
	 */
	public TesseraPermessoDTO getTesseraPermesso() {
		return tesseraPermesso;
	}
	/**
	 * @param tesseraPermesso the tesseraPermesso to set
	 */
	public void setTesseraPermesso(TesseraPermessoDTO tesseraPermesso) {
		this.tesseraPermesso = tesseraPermesso;
	}
	/**
	 * @return the città
	 */
	public CittàDTO getCittà() {
		return città;
	}
	/**
	 * @param città the città to set
	 */
	public void setCittà(CittàDTO città) {
		this.città = città;
	}
	
	/**
	 * visitor 
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
		return "Costruire con una tessera permesso  [P3]";
	}
	
	/**
	 * set parameter
	 */
	@Override
	public CostruzioneTesseraPermessoParametri parametri() {
		return new CostruzioneTesseraPermessoParametri(this);
	}
	
	
}
