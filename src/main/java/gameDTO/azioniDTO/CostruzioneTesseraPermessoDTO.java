package gameDTO.azioniDTO;


import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.CittàDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class CostruzioneTesseraPermessoDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2520930894055638122L;
	private TesseraPermessoDTO tesseraPermesso;
	private CittàDTO città;
	/**
	 * @param tesseraPermesso
	 * @param città
	 */
	public CostruzioneTesseraPermessoDTO(TesseraPermessoDTO tesseraPermesso, CittàDTO città) {
		this.tesseraPermesso = tesseraPermesso;
		this.città = città;
	}
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
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}
	
	
}
