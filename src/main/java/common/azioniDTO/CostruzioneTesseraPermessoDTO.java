package common.azioniDTO;


import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.gameDTO.CittàDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.azioni.Azione;
import utility.ParameterException;

public class CostruzioneTesseraPermessoDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2520930894055638122L;
	private TesseraPermessoDTO tesseraPermesso;
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
	@Override
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return azioneVisitor.visit(this);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Costruire con una tessera permesso  [P3]";
	}
	
	
}
