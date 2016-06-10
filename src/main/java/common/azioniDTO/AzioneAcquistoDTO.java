package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.gameDTO.GiocatoreDTO;
import server.model.azioni.Azione;

public class AzioneAcquistoDTO implements AzioneDTO {

	private static final long serialVersionUID = -7696287909942735586L;
	private int offerta;
	private GiocatoreDTO giocatoreDTO;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Per acquistare  [Acquista]";
	}

	/**
	 * @param offerta the offerta to set
	 */
	public void setOfferta(int offerta) {
		if(offerta<1)
			throw new IllegalArgumentException("L'indice dell'offerta deve esere maggiore di zero!");
		this.offerta = offerta;
	}

	/**
	 * @param giocatoreDTO the giocatoreDTO to set
	 */
	public void setGiocatoreDTO(GiocatoreDTO giocatoreDTO) {
		if(giocatoreDTO==null)
			throw new IllegalArgumentException("Il giocatoreDTO non può essere null!");
		this.giocatoreDTO = giocatoreDTO;
	}

}
