package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.gameDTO.TesseraPermessoDTO;
import server.model.azioni.Azione;

public class BonusTesseraAcquistataNDTO implements AzioneDTO{

	private boolean usata;
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
	/**
	 * @return the usata
	 */
	public boolean isUsata() {
		return usata;
	}
	/**
	 * @param usata the usata to set
	 */
	public void setUsata(boolean usata) {
		this.usata = usata;
	}
	
	
	
}
