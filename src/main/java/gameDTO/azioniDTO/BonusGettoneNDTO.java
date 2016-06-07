package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.CittàBonusDTO;

public class BonusGettoneNDTO implements AzioneDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5454333598048906851L;
	private CittàBonusDTO città;


	/**
	 * @return the città
	 */
	public CittàBonusDTO getCittà() {
		return città;
	}

	/**
	 * @param città the città to set
	 */
	public void setCittà(CittàBonusDTO città) {
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
		return "BonusGettone [B3]";
	}
	

}
