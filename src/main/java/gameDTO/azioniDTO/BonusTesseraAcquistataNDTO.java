package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;

public class BonusTesseraAcquistataNDTO implements AzioneDTO{

	private int indiceTesseraPermesso;
	private boolean usata;
	/**
	 * @param indiceTesseraPermesso
	 * @param usata
	 */
	public BonusTesseraAcquistataNDTO(int indiceTesseraPermesso, boolean usata) {
		this.indiceTesseraPermesso = indiceTesseraPermesso;
		this.usata = usata;
	}
	/**
	 * @return the indiceTesseraPermesso
	 */
	public int getIndiceTesseraPermesso() {
		return indiceTesseraPermesso;
	}
	/**
	 * @param indiceTesseraPermesso the indiceTesseraPermesso to set
	 */
	public void setIndiceTesseraPermesso(int indiceTesseraPermesso) {
		this.indiceTesseraPermesso = indiceTesseraPermesso;
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
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);	
	}
	
	
	
}
