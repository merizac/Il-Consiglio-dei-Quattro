package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class BonusTesseraAcquistataNDTO implements AzioneDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1866697657818100542L;
	private TesseraPermessoDTO tesseraPermesso;
	
	
	/**
	 * @return the indiceTesseraPermesso
	 */
	public TesseraPermessoDTO getTesseraPermesso() {
		return tesseraPermesso;
	}
	/**
	 * @param indiceTesseraPermesso the indiceTesseraPermesso to set
	 */
	public void setTesseraPermesso(TesseraPermessoDTO tesseraPermesso) {
		this.tesseraPermesso = tesseraPermesso;
	}
	
	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);	
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusTesseraAcquistata [B2]";
	}
	
	
	
}
