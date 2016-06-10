package common.azioniDTO;

import java.util.List;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàDTO;
import server.model.azioni.Azione;

public class CostruzioneAiutoReDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6608314626155739931L;
	private List<CartaPoliticaDTO> carteGiocatore;
	private CittàDTO città;
	
	
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Costruire con l'aiuto del re  [P4]";
	}
	
	

}
