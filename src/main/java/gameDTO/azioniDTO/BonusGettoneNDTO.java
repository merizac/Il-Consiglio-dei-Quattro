package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.CittàBonusDTO;

public class BonusGettoneNDTO implements AzioneDTO{
	
	private CittàBonusDTO città;

	/**
	 * @param città
	 */
	public BonusGettoneNDTO(CittàBonusDTO città) {
		this.città = città;
	}

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
	

}
