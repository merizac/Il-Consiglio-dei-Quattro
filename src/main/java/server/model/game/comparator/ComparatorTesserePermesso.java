package server.model.game.comparator;

import java.util.Comparator;

import server.model.game.Giocatore;

public class ComparatorTesserePermesso implements Comparator<Giocatore>{

		@Override
		public int compare(Giocatore g1, Giocatore g2) {
			int numeroTessere1 = g1.getNumeroTesserePermesso();
			int numeroTessere2 = g2.getNumeroTesserePermesso();

			if (numeroTessere2 < numeroTessere1)
				return -1;
			else if (numeroTessere1 == numeroTessere2)
				return 0;
			else
				return 1;
		}

}