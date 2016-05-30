package gameDTO.azioniDTO;

import java.io.Serializable;
import java.util.List;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.CartaPoliticaDTO;
import gameDTO.gameDTO.CittàDTO;

public class CostruzioneAiutoReDTO implements AzioneDTO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6608314626155739931L;
	private List<CartaPoliticaDTO> carteGiocatore;
	private CittàDTO città;
	/**
	 * @param carteGiocatore
	 * @param città
	 */
	public CostruzioneAiutoReDTO(List<CartaPoliticaDTO> carteGiocatore, CittàDTO città) {
		this.carteGiocatore = carteGiocatore;
		this.città = città;
	}
	/**
	 * @return the carteGiocatore
	 */
	public List<CartaPoliticaDTO> getCarteGiocatore() {
		return carteGiocatore;
	}
	/**
	 * @param carteGiocatore the carteGiocatore to set
	 */
	public void setCarteGiocatore(List<CartaPoliticaDTO> carteGiocatore) {
		this.carteGiocatore = carteGiocatore;
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