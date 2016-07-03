package common.azioniDTO;

import java.util.List;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioniSetParametri.CostruzioneAiutoReParametri;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàDTO;
import server.model.azioni.Azione;
import utility.ParameterException;

public class CostruzioneAiutoReDTO implements AzioneDTO, AzioneParametri {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = -6608314626155739931L;
	/**
	 * list of politic card from the player
	 */
	private List<CartaPoliticaDTO> carteGiocatore;
	/**
	 * city that the player had to choose
	 */
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
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return azioneVisitor.visit(this);
	}
	/* 
	 * to string
	 */
	@Override
	public String toString() {
		return "Costruire con l'aiuto del re  [P4]";
	}
	
	/**
	 * set parameter
	 */
	@Override
	public CostruzioneAiutoReParametri parametri() {
		return new CostruzioneAiutoReParametri(this);
	}
	
	

}
