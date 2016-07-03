package common.azioniDTO;
import java.util.List;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioniSetParametri.AcquistoTesseraPermessoParametri;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.azioni.azioniPrincipali.AcquistoTesseraPermesso;
import utility.ParameterException;

public class AcquistoTesseraPermessoDTO implements AzioneDTO, AzioneParametri {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 2837487620338099231L;
	
	/**
	 * region where take the permit tile
	 */
	private RegioneDTO regione;
	/**
	 * list of cards that have to match with color of balcony
	 */
	private List<CartaPoliticaDTO> carte;
	/**
	 * permit tile that the player want
	 */
	private TesseraPermessoDTO tesseraPermesso;
	
	
	/**
	 * @return the regione
	 */
	public RegioneDTO getRegione() {
		return regione;
	}
	/**
	 * @param regione the regione to set
	 */
	public void setRegione(RegioneDTO regione) {
		this.regione = regione;
	}
	/**
	 * @return the carte
	 */
	public List<CartaPoliticaDTO> getCarte() {
		return carte;
	}
	/**
	 * @param carte the carte to set
	 */
	public void setCarte(List<CartaPoliticaDTO> carte) {
		this.carte = carte;
	}

	
	@Override
	public AcquistoTesseraPermesso accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return azioneVisitor.visit(this);
		
	}
	/* 
	 * to string
	 */
	@Override
	public String toString() {
		return "Acquistare una tessera permesso [P2]";
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
	 * set parameter
	 */
	@Override
	public AcquistoTesseraPermessoParametri parametri() {
		return new AcquistoTesseraPermessoParametri(this);
	}
}
