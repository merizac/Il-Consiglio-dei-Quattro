package gameDTO.azioniDTO;

import java.util.List;
import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.CartaPoliticaDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class AcquistoTesseraPermessoDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2837487620338099231L;
	private RegioneDTO regione;
	private List<CartaPoliticaDTO> carte;
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
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
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
}
