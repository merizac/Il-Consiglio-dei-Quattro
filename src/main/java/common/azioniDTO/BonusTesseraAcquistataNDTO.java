package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioniSetParametri.BonusTesseraAcquistataParametri;
import common.gameDTO.TesseraPermessoDTO;
import server.model.azioni.Azione;
import utility.ParameterException;

public class BonusTesseraAcquistataNDTO implements AzioneDTO, AzioneParametri{

	private boolean usata;
	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = -1866697657818100542L;
	/**
	 * permit tile from the player
	 */
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
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return azioneVisitor.visit(this);	
	}
	/*
	 * to string
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
	
	@Override
	public BonusTesseraAcquistataParametri parametri() {
		return new BonusTesseraAcquistataParametri(this);
	}
	
}
