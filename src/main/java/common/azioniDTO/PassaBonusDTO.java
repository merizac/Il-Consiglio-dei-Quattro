package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import server.model.azioni.azioniBonus.PassaBonus;
import utility.ParameterException;

public class PassaBonusDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4763520988486769800L;
	private AzioneDTO bonus;
	
	@Override
	public PassaBonus accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return azioneVisitor.visit(this);
	}



	/**
	 * @return the bonus
	 */
	public AzioneDTO getBonus() {
		return bonus;
	}



	/**
	 * @param bonus the bonus to set
	 */
	public void setBonus(AzioneDTO bonus) {
		this.bonus = bonus;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Passa  [Passa Bonus]";
	}

}
