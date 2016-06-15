package server.model.game;

import java.util.List;
import server.model.bonus.*;

public class PlanciaRe  {

	private final Balcone balconeRe;
	private final List<Bonus> bonusPremioRe;
	private final List<PunteggioNobiltà> percorsoNobiltà;
	/**
	 * @param balconeRe
	 * @param bonusPremioRe
	 * @param percorsoNobiltà
	 */
	public PlanciaRe(Balcone balconeRe, List<Bonus> bonusPremioRe,
			List<PunteggioNobiltà> percorsoNobiltà) {
		this.balconeRe = balconeRe;
		this.bonusPremioRe = bonusPremioRe;
		this.percorsoNobiltà = percorsoNobiltà;
	}
	/**
	 * @return the balconeRe
	 */
	public Balcone getBalconeRe() {
		return balconeRe;
	}
	/**
	 * @return the bonusPremioRe
	 */
	public List<Bonus> getBonusPremioRe() {
		return bonusPremioRe;
	}
	
	/**
	 * @return the percorsoNobiltà
	 */
	public List<PunteggioNobiltà> getPercorsoNobiltà() {
		return percorsoNobiltà;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "balcone Re = [" + balconeRe + "]\nbonus PremioRe = [" + bonusPremioRe + "]\npercorsoNobiltà = ["
				+ percorsoNobiltà + "]";
	}
}
