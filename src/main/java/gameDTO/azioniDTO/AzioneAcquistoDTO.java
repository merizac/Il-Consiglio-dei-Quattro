package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.GiocatoreDTO;

public class AzioneAcquistoDTO implements AzioneDTO {

	private static final long serialVersionUID = -7696287909942735586L;
	private int offerta;
	private GiocatoreDTO giocatoreDTO;

	/**
	 * @param offerta
	 * @param giocatoreDTO 
	 */
	public AzioneAcquistoDTO(int offerta, GiocatoreDTO giocatoreDTO) {
		super();
		this.offerta = offerta;
		this.giocatoreDTO=giocatoreDTO;
	}

	/**
	 * @return the offerta
	 */
	public int getOfferta() {
		return offerta;
	}
	

	/**
	 * @return the giocatoreDTO
	 */
	public GiocatoreDTO getGiocatoreDTO() {
		return giocatoreDTO;
	}

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}

}
