package server.model.game.comparator;

import java.util.Comparator;

import server.model.game.Giocatore;

public class ComparatorClassifica implements Comparator<Giocatore>{

	@Override
	public int compare(Giocatore g1, Giocatore g2) {
		int puntiVittoria1 = g1.getPunteggioVittoria();
		int puntiVittoria2 = g2.getPunteggioVittoria();
		int numeroAiutanti1 = g1.getAiutanti().getAiutante();
		int numeroAiutanti2 = g2.getAiutanti().getAiutante();
		int cartePolitica1 = g1.getCartePolitica().size();
		int cartePolitica2 = g2.getCartePolitica().size();

		if (puntiVittoria2 < puntiVittoria1)
			return -1;
		else if (puntiVittoria1 == puntiVittoria2){
			if(numeroAiutanti2<numeroAiutanti1)
				return -1;
			else if(numeroAiutanti2>numeroAiutanti1)
				return 1;
			else
			{
				if(cartePolitica2<cartePolitica1)
					return -1;
				else if(cartePolitica2==cartePolitica1)
					return 0;
				else
					return 1;
			}
		}
			
		else
			return 1;
	}
	
}
