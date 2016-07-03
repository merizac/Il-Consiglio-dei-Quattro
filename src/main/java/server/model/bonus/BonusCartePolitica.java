package server.model.bonus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.model.game.CartaPolitica;
import server.model.game.GameState;
import server.model.game.Mazzo;
import server.model.notify.MessageNotify;

public class BonusCartePolitica extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 380560969549363148L;
	private final int cartePolitica;

	public BonusCartePolitica(int cartePolitica) {
		super();
		if (cartePolitica < 0) {
			throw new IllegalArgumentException();
		}
		this.cartePolitica = cartePolitica;
	}

	/**
	 * add politic cards to the player
	 */
	@Override
	public void usaBonus(GameState gameState) {
		Mazzo<CartaPolitica> mazzo = gameState.getMazzoCartePolitica();
		List<CartaPolitica> carte=new ArrayList<>();
		for (int numCarte = 0; numCarte < cartePolitica; numCarte++) {
			CartaPolitica cartaPolitica=mazzo.pescaCarte();
			gameState.getGiocatoreCorrente().aggiungiCartaPolitica(cartaPolitica);
			carte.add(cartaPolitica);
			
		}
		gameState.notifyObserver(new MessageNotify((cartePolitica==1) ? "Hai vinto "+cartePolitica+" carta politica\n"+carte+"\n" 
					:"Hai vinto "+cartePolitica+" carte politica\n"+carte+"\n", Arrays.asList(gameState.getGiocatoreCorrente()), false));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusCartePolitica " + cartePolitica;
	}

	/**
	 * @return the cartePolitica
	 */
	public int getCartePolitica() {
		return cartePolitica;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartePolitica;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		BonusCartePolitica other = (BonusCartePolitica) obj;
		if (cartePolitica != other.cartePolitica)
			return false;
		return true;
	}

}
