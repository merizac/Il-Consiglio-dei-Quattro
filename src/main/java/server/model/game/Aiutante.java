package server.model.game;

import common.gameDTO.AiutanteDTO;
import common.gameDTO.MarketableDTO;
import server.model.market.Marketable;
import server.model.market.Offerta;

public class Aiutante implements Marketable {

	private int numeroAiutanti;

	/**
	 * costructor of Aiutante
	 * @param numeroAiutanti
	 */
	public Aiutante(int numeroAiutanti) {
		if (numeroAiutanti < 0)
			throw new IllegalArgumentException("Gli aiutanti devono essere un numero positivo o nullo");
		this.numeroAiutanti = numeroAiutanti;
	}

	/**
	 * get the private variable numeroAiutanti
	 * 
	 * @return numeroAiutanti
	 */
	public int getAiutante() {
		return this.numeroAiutanti;
	}

	/**
	 * Add the numeroAiutanti passed as a parameter to the local variable
	 * numeroAiutanti
	 * 
	 * @param numeroAiutanti
	 *            the number of Aiutanti to add
	 */
	public void aggiungiAiutanti(int numeroAiutanti) {
		if (numeroAiutanti <= 0)
			throw new IllegalArgumentException("Gli aiutanti da aggiungere devono essere un numero positivo");
		this.numeroAiutanti = this.numeroAiutanti + numeroAiutanti;
	}

	/**
	 * Decrement the numeroAiutanti
	 * 
	 * @param numeroAiutanti
	 * @return true if it possible to decrease the variable numeroAiutanti and
	 *         false in the other case
	 */
	public boolean togliAiutanti(int numeroAiutanti) {

		if (numeroAiutanti < 0)
			throw new IllegalArgumentException("Gli aiutanti da togliere devono essere un numero positivo");

		if (this.numeroAiutanti < numeroAiutanti)
			return false;
		else {
			this.numeroAiutanti = this.numeroAiutanti - numeroAiutanti;
			return true;
		}

	}

	/**
	 * buy action for market. if one player want to buy one aiutante he has to
	 * pay the money that the other player chose
	 * 
	 */
	@Override
	public boolean acquista(Giocatore acquirente, Offerta offerta) {
		if (!acquirente.diminuisciRicchezza(offerta.getPrezzo()))
			return false;
		else {
			offerta.getVenditore().getAiutanti().togliAiutanti(1);
			acquirente.getAiutanti().aggiungiAiutanti(1);
			offerta.getVenditore().aumentaRicchezza(offerta.getPrezzo());
			return true;
		}
	}

	/**
	 * check if player can sell aiutante
	 */
	@Override
	public boolean possiede(Giocatore venditore) {
		if (venditore.getAiutanti().getAiutante() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Aiutanti:" + numeroAiutanti;
	}

	/**
	 * return a new AiutanteDTO for market
	 */
	@Override
	public MarketableDTO instance() {
		return new AiutanteDTO();
	}

}
