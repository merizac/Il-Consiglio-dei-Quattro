package server.model.azioni.azioniBonus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.BonusGettoneNDTO;
import server.model.azioni.Azione;
import server.model.bonus.Bonus;
import server.model.game.CittàBonus;
import server.model.game.Emporio;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.AvversarioNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.MessageNotify;

public class BonusGettoneN extends Azione {

	/*
	 * ottieni il bonus di un gettone ricompensa in cui hai un emporio. non puoi
	 * scegliere uno dei gettoni avanzamento ul percorso nobiltà
	 */
	private List<CittàBonus> città;
	/**
	 * number of gettoni which the player can get
	 */
	private int numeroGettoni;

	/**
	 * current player had to choose a city in which he has an emporio then the
	 * player took bonus that are on GettoneBonus in that city that he choose.
	 * 
	 * @param gameState
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		for (CittàBonus c : città) {
			if (!c.getEmpori().contains(new Emporio(gameState.getGiocatoreCorrente().getColoreGiocatore()))) {
				gameState.notifyObserver(new MessageNotify(
						"Errore:" + gameState.getGiocatoreCorrente().getNome() + "non hai un emporio in " + c.getNome()+"\n",
						Arrays.asList(gameState.getGiocatoreCorrente()), false));
				return;
			}
		}
		if (numeroGettoni > 1 && città.get(0).getBonus().equals(città.get(1).getBonus())) {
				gameState.notifyObserver(new MessageNotify(
						"Errore:" + gameState.getGiocatoreCorrente().getNome()
								+ "devi scegliere due gettoni ricompensa diversi tra loro"+"\n",
						Arrays.asList(gameState.getGiocatoreCorrente()), false));
				return;
		}
		ArrayList<Bonus> bonus = new ArrayList<>();

		for (CittàBonus city : città) {
			bonus.addAll(city.getBonus());
		}
		for (Bonus b : bonus) {
			b.usaBonus(gameState);
		}
		gameState.getGiocatoreCorrente().getBonusNobiltà().remove(this);
		List<Giocatore> avversari = new ArrayList<>(gameState.getGiocatori());
		avversari.remove(gameState.getGiocatoreCorrente());
		gameState.notifyObserver(new AvversarioNotify(gameState.getGiocatoreCorrente(), avversari));
		gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(),
				Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.getStato().transizioneBonus(gameState);
	}

	/**
	 * @return the città
	 */
	public List<CittàBonus> getCittà() {
		return città;
	}

	/**
	 * @param città
	 *            the città to set
	 */
	public void setCittà(List<CittàBonus> città) {
		if(città==null)
			throw new NullPointerException("La lista di città è null");
		this.città = città;
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		BonusGettoneNDTO bonus = new BonusGettoneNDTO();
		bonus.setNumeroGettoni(numeroGettoni);
		return bonus;
	}

	/**
	 * @return the numeroGettoni
	 */
	public int getNumeroGettoni() {
		return numeroGettoni;
	}

	/**
	 * @param numeroGettoni
	 *            the numeroGettoni to set
	 */
	public void setNumeroGettoni(int numeroGettoni) {
		if(numeroGettoni<1 || numeroGettoni>2)
			throw new IllegalArgumentException("Il numero di gettoni è negativo");
		this.numeroGettoni = numeroGettoni;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeroGettoni;
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
		BonusGettoneN other = (BonusGettoneN) obj;
		if (numeroGettoni != other.numeroGettoni)
			return false;
		return true;
	}


}
