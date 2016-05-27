package gameDTO.azioniDTO;

import java.io.Serializable;
import java.util.List;

import gameDTO.gameDTO.CartaPoliticaDTO;
import gameDTO.gameDTO.RegioneDTO;

public class AcquistoTesseraPermessoDTO implements AzioneDTO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2837487620338099231L;
	private RegioneDTO regione;
	private List<CartaPoliticaDTO> carte;
	private int indiceTessera;
	/**
	 * @param regione
	 * @param carte
	 * @param indiceTessera
	 */
	public AcquistoTesseraPermessoDTO(RegioneDTO regione, List<CartaPoliticaDTO> carte, int indiceTessera) {
		this.regione = regione;
		this.carte = carte;
		this.indiceTessera = indiceTessera;
	}
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
	/**
	 * @return the indiceTessera
	 */
	public int getIndiceTessera() {
		return indiceTessera;
	}
	/**
	 * @param indiceTessera the indiceTessera to set
	 */
	public void setIndiceTessera(int indiceTessera) {
		this.indiceTessera = indiceTessera;
	}
	
	
	

}
