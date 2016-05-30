package game.notify;

import game.CartaPolitica;
import game.GameState;
import game.TesseraPermesso;

public class GiocatoreNotify extends NotifyGiocatoreCorrente implements Notify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7988103202713464659L;

	@Override
	public void stamp(GameState gameState) {

		System.out.println("Punti ricchezza :"+gameState.getGiocatoreCorrente().getPunteggioRicchezza());
		System.out.println("Punti vittoria :"+gameState.getGiocatoreCorrente().getPunteggioVittoria());
		System.out.println("Punti nobiltà :"+gameState.getGiocatoreCorrente().getPunteggioNobiltà().getPuntiNobiltà());
		System.out.println("Tessere permesso acquistate");
		for(TesseraPermesso t: gameState.getGiocatoreCorrente().getTesserePermesso()){
			System.out.println(t);
		}
		System.out.println("Carte politica");
		for(CartaPolitica c: gameState.getGiocatoreCorrente().getCartePolitica()){
			System.out.println(c);
		}
		System.out.println("numero Aiutanti:" +gameState.getGiocatoreCorrente().getAiutanti());
		System.out.println("numero Empori:" +gameState.getGiocatoreCorrente().getEmpori().size());
	}
}