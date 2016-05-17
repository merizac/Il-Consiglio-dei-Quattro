package game;

import game.market.Marketable;
import game.market.Offerta;

public class Aiutante implements Marketable{

	private int numeroAiutanti;
	/**
	 * 
	 * @param numeroAiutanti
	 */
	public Aiutante(int numeroAiutanti){
		this.numeroAiutanti= numeroAiutanti;
	}
	/**
	 * get the private variable numeroAiutanti
	 * @return numeroAiutanti
	 */
	public int getAiutante() {
		return this.numeroAiutanti;
	}
	/**
	 * Add the numeroAiutanti passed as a parameter to the local variable numeroAiutanti
	 * @param numeroAiutanti the number of Aiutanti to add
	 */
	public void aggiungiAiutanti(int numeroAiutanti) {
		this.numeroAiutanti = this.numeroAiutanti + numeroAiutanti;
	}
	/**
	 * Decrement the numeroAiutanti 
	 * @param numeroAiutanti
	 * @return true if it possible to decrease the variable numeroAiutanti and false in the other case
	 */
	public boolean togliAiutanti(int numeroAiutanti) {
		
		if(this.numeroAiutanti< numeroAiutanti) return false;
		else {
			this.numeroAiutanti = this.numeroAiutanti - numeroAiutanti;
			return true;
		}

	}
	@Override
	public boolean acquista(Giocatore acquirente, Offerta offerta) {
		if(!acquirente.diminuisciRicchezza(offerta.getPrezzo()))
			return false;
		else
		{
			offerta.getVenditore().getAiutanti().togliAiutanti(1);
			acquirente.getAiutanti().aggiungiAiutanti(1);
			offerta.getVenditore().aumentaRicchezza(offerta.getPrezzo());
			return true;
		}
	}
	@Override
	public boolean possiede(Giocatore venditore) {
		if(venditore.getAiutanti().getAiutante()==0)
			return false;
		else 
			return true;
	}
	
	
}
