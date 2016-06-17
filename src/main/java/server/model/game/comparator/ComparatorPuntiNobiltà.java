package server.model.game.comparator;

import java.util.Comparator;

import server.model.game.Giocatore;

public class ComparatorPuntiNobiltà implements Comparator<Giocatore> {
	/**
	 * compare and order players by nobility points
	 * 
	 */
	@Override
	public int compare(Giocatore g1, Giocatore g2) {
		int punti1 = g1.getPunteggioNobiltà().getPuntiNobiltà();
		int punti2 = g2.getPunteggioNobiltà().getPuntiNobiltà();
		if (punti2 < punti1)
			return -1;
		else if (punti1 == punti2)
			return 0;
		else
			return 1;
	}
}
