package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioniSetParametri.AzioneAcquistoParametri;
import common.gameDTO.GiocatoreDTO;
import server.model.azioni.Azione;
import utility.ParameterException;

public class AzioneAcquistoDTO implements AzioneDTO, AzioneParametri {

	private static final long serialVersionUID = -7696287909942735586L;
	private int offertaDTO;
	private GiocatoreDTO giocatoreDTO;

	/**
	 * @return the offerta
	 */
	public int getOfferta() {
		return offertaDTO;
	}
	

	/**
	 * @return the giocatoreDTO
	 */
	public GiocatoreDTO getGiocatoreDTO() {
		return giocatoreDTO;
	}

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException {
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
	public void setOfferta(int offertaDTO) {
		if (offertaDTO<=0)
			throw new IllegalArgumentException("L'offerta deve essere positiva");
		this.offertaDTO = offertaDTO;
	}

	/**
	 * @param giocatoreDTO the giocatoreDTO to set
	 */
	public void setGiocatoreDTO(GiocatoreDTO giocatoreDTO) {
		if(giocatoreDTO==null)
			throw new IllegalArgumentException("Il giocatoreDTO non può essere null!");
		this.giocatoreDTO = giocatoreDTO;
	}

	@Override
	public AzioneAcquistoParametri parametri() {
		return new AzioneAcquistoParametri(this);
	}

}
