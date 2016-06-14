package server.model.azioni.azioniBonus;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.PassaBonusDTO;
import server.model.azioni.Azione;
import server.model.game.GameState;

public class PassaBonus extends Azione{

	private Azione bonus;
	private final int ID = 16;

	@Override
	public void eseguiAzione(GameState gameState) {
		gameState.getGiocatoreCorrente().getBonusNobiltà().remove(this.bonus);
		gameState.getStato().transizionePassa(gameState);
		System.out.println("Stato :" + gameState.getStato());

	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new PassaBonusDTO();
	}

	/**
	 * @return the bonus
	 */
	public Azione getBonus() {
		return bonus;
	}

	/**
	 * @param bonus the bonus to set
	 */
	public void setBonus(Azione bonus) {
		this.bonus = bonus;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PassaBonus other = (PassaBonus) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
		
	
	
}
