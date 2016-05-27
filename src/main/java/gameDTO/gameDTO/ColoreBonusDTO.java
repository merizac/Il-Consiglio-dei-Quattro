package gameDTO.gameDTO;

import java.io.Serializable;

public class ColoreBonusDTO extends ColoreDTO implements Serializable {

	private static final long serialVersionUID = 3272694886315865223L;
	private BonusDTO bonusDTO;
	/**
	 * @return the bonusDTO
	 */
	public BonusDTO getBonusDTO() {
		return bonusDTO;
	}
	/**
	 * @param bonusDTO the bonusDTO to set
	 */
	public void setBonusDTO(BonusDTO bonusDTO) {
		this.bonusDTO = bonusDTO;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ColoreBonusDTO [bonusDTO=" + bonusDTO + "]";
	}
	
	

}
