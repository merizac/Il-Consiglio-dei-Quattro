package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bonus.Bonus;
import game.GameState;
import game.Giocatore;
import game.notify.ClassificaNotify;
import game.notify.PerdenteNotify;
import game.notify.VincitoreNotify;

public class StatoFinePartita implements Stato {

	/**
	 * 
	 */
	private static final long serialVersionUID = -972179062051125451L;

	public StatoFinePartita(GameState gameState) {
		calcolaVincitore(gameState);

	}

	private void calcolaVincitore(GameState gameState) {
		calcolaPunteggioNobiltà(gameState);
		calcolaTesserePermesso(gameState);
		assegnaTessereBonus(gameState);
		vincitore(gameState);
		
	}
	
	private void vincitore(GameState gameState) {
		ArrayList<Giocatore> giocatori = new ArrayList<>(gameState.getGiocatoriFinePartita());
		GiocatoreComparator comparator= new GiocatoreComparator();
		Collections.sort(giocatori, comparator);
		List<Giocatore> vincitori=Arrays.asList(giocatori.get(0));
		giocatori.remove(vincitori.get(0));
		
		
		for(Giocatore g: giocatori){
			if(comparator.compare(g, vincitori.get(0))!=0)
				continue;
			else{
				vincitori.add(g);
				giocatori.remove(g);
			}
			
		}
		
		
		for(Giocatore g: vincitori){
			gameState.notifyObserver(new VincitoreNotify(Arrays.asList(g), g));
		}
		
		for(Giocatore g: giocatori){
			gameState.notifyObserver(new PerdenteNotify(Arrays.asList(g), g));
		}
		
		gameState.notifyObserver(new ClassificaNotify(vincitori, giocatori, gameState.getGiocatoriFinePartita()));
	}
	
	private class GiocatoreComparator implements Comparator<Giocatore>{

		@Override
		public int compare(Giocatore g1, Giocatore g2) {
			int puntiVittoria1 = g1.getPunteggioVittoria();
			int puntiVittoria2 = g2.getPunteggioVittoria();
			int numeroAiutanti1 =g1.getAiutanti().getAiutante();
			int numeroAiutanti2 =g2.getAiutanti().getAiutante();
			int cartePolitica1 =g1.getCartePolitica().size();
			int cartePolitica2 =g2.getCartePolitica().size();

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
	
/**
 * add points of TessereBonus to the players who owns that.
 * @param gameState
 */
	private void assegnaTessereBonus(GameState gameState) {
		for(Giocatore g: gameState.getGiocatoriFinePartita()){
			if(!g.getTessereBonus().isEmpty()){
				for(Bonus b: g.getTessereBonus()){
					b.usaBonus(gameState);
				}
			}
		}
	}

	/**
	 * Sort giocatori by order decrescent of number of TesserePermesso (coperte e scoperte)
	 * Each player with the best score of number of tessere permesso win 3 points in Punteggio Vittoria
	 * @param gameState
	 */
private void calcolaTesserePermesso(GameState gameState) {
	ArrayList<Giocatore> giocatori = new ArrayList<>(gameState.getGiocatoriFinePartita());
	Collections.sort(giocatori, new Comparator<Giocatore>() {

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

		});	

	int i=0;
	while(giocatori.get(i).getNumeroTesserePermesso()==giocatori.get(0).getNumeroTesserePermesso() && i<giocatori.size()-1){
		giocatori.get(i).aumentaPuntiVittoria(3);
		i++;
	 }
}


	
/**
 * this method creaates two arraylist: one for players who obtain the best points on Punteggio Nobiltà, 
 * and the second for players who do the second best points in Punteggio Nobiltà
 * Comparator is the method that sort the array giocatori in order decrescent of points of nobility
 * @param gameState
 */
private void calcolaPunteggioNobiltà(GameState gameState) {
ArrayList<Giocatore> giocatori = new ArrayList<>(gameState.getGiocatoriFinePartita());
	Collections.sort(giocatori, new Comparator<Giocatore>() {

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

		});
		
		ArrayList<Giocatore> primo = new ArrayList<>();
		primo.add(giocatori.get(0));
		ArrayList<Giocatore> secondo = new ArrayList<>();
		
		for(Giocatore g: giocatori){
		 int punti = g.getPunteggioNobiltà().getPuntiNobiltà();
		 
		 if( punti==primo.get(0).getPunteggioNobiltà().getPuntiNobiltà()){
			 primo.add(g);
		 }
		 if((punti!= primo.get(0).getPunteggioNobiltà().getPuntiNobiltà() && secondo.isEmpty())
				 || punti == secondo.get(0).getPunteggioNobiltà().getPuntiNobiltà()){
			 secondo.add(g);
		break;
		 }
		 assegnaPunti(primo, secondo);
		}
	
	}
/**
 * This method add at players points extra for the best points
 * if there are more then one player in primo, all that player win 5 points in Punteggio vittoria 
 * and there isn't a second position 
 * if there is just one player with the best score, he win 5 points extra in Punteggio Vittoria and all players with 
 * second best score win 3 points of Punteggio Vittoria.
 * @param primo
 * @param secondo
 */
private void assegnaPunti(ArrayList<Giocatore> primo, ArrayList<Giocatore> secondo) {
	for (Giocatore g: primo){
		g.aumentaPuntiVittoria(5);
		if(primo.size()==1){
			for(Giocatore g2: secondo){
				g2.aumentaPuntiVittoria(2);
				}
			  }
			}
	}

	@Override
	public List<String> getAzioni() {
		return null;
	}

}
