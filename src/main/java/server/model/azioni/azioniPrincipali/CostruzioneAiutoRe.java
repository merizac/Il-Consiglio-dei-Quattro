package server.model.azioni.azioniPrincipali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.CostruzioneAiutoReDTO;
import server.model.azioni.azioniBonus.Bonusable;
import server.model.bonus.Bonus;
import server.model.bonus.BonusPuntiNobiltà;
import server.model.game.Balcone;
import server.model.game.CartaPolitica;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Colore;
import server.model.game.ColoreCittà;
import server.model.game.Consigliere;
import server.model.game.Emporio;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Mappa;
import server.model.game.Regione;
import server.model.notify.MessageNotify;

public class CostruzioneAiutoRe extends AzionePrincipale implements Bonusable {

	private Città cittàCostruzione;
	private Balcone balcone;
	private List<CartaPolitica> carteGiocatore;

	/**
	 * check if the cards of the player are of the same color of consiglieri in
	 * the balcony check if the player has enough aiutanti in case of in the
	 * city there are already emporium calculate how many money the player have
	 * to pay for the balcony move the king in the city in which the player ask
	 * to move build in that city check if the player had finisched emporium, in
	 * that case set transition in state pattern for the last round check if the
	 * player has wins point of nobility, in that case check if casella in
	 * nobility track contains bonus
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		boolean nob = false;
		balcone = gameState.getPlanciaRe().getBalconeRe();
		Mappa mappa = gameState.getMappa();
		if (!controllaColori()) {
			gameState.notifyObserver(
					new MessageNotify("Errore: i colori delle carte scelte non corrispondono con quelli del balcone!\n",
							Arrays.asList(gameState.getGiocatoreCorrente()), false));
			return;
		}

		if (!pagoAiutanti(gameState)) {
			gameState.notifyObserver(new MessageNotify("Errore: gli aiutanti non sono sufficienti!\n",
					Arrays.asList(gameState.getGiocatoreCorrente()), false));
			return;
		}

		int moneteDovute = calcolaMonete()
				+ 2 * mappa.minimaDistanza(gameState.getPedinaRe().getCittà(), cittàCostruzione);

		if (!paga(moneteDovute, gameState)) {
			gameState.notifyObserver(new MessageNotify("Errore: i soldi non sono sufficienti!\n",
					Arrays.asList(gameState.getGiocatoreCorrente()), false));
			return;
		}

		else {
			gameState.getPedinaRe().setCittà(cittàCostruzione);
			pagaAiutanti(gameState);
			gameState.getGiocatoreCorrente().getCartePolitica().removeAll(carteGiocatore);

			costruisci(gameState);
			prendiBonus(gameState, nob);
			if (cittàCostruzione instanceof CittàBonus)

				controllaCittàColore((ColoreCittà) cittàCostruzione.getColoreCittà(),
						gameState.getGiocatoreCorrente(), gameState.getPlanciaRe().getBonusPremioRe());
			controllaCittàRegione(cittàCostruzione.getRegione(), gameState.getGiocatoreCorrente()
					, gameState.getPlanciaRe().getBonusPremioRe());
		}

		if (gameState.getGiocatoreCorrente().getEmpori().isEmpty()) {
			gameState.setUltimoGiro(true);
			gameState.getGiocatoreCorrente().aumentaPuntiVittoria(3);
		}

		List<Bonus> bonusCasella = gameState.getGiocatoreCorrente().getPunteggioNobiltà().getBonus();

		if (nob && !bonusCasella.isEmpty()) {
			if (controlloBonus(gameState)) {
				setStatoTransizionePrincipale(gameState);
			} else {
				gameState.getStato().transizioneBonus(gameState);
			}
		} else {
			notify(gameState);
			setStatoTransizionePrincipale(gameState);
		}
	}

	/**
	 * cheack if player can win bonus of the region
	 * 
	 * @param regione
	 * @param giocatore
	 */
	private void controllaCittàRegione(Regione regione, Giocatore giocatore, List<Bonus> bonusRe) {
		for (Città c : regione.getCittàRegione()) {
			if (!c.emporioColore(giocatore.getColoreGiocatore()))
				return;
		}
		giocatore.getTessereBonus().add(regione.getBonusRegione());
		regione.setBonusAssegnato(true);
		if (!bonusRe.isEmpty()) {
			giocatore.getTessereBonus().add(bonusRe.remove(0));
		}

	}

	/**
	 * check if the player can win bonus of color
	 * 
	 * @param coloreCittà
	 * @param giocatore
	 */
	private void controllaCittàColore(ColoreCittà coloreCittà, Giocatore giocatore, List<Bonus> bonusRe) {
		if (coloreCittà.isAssegnatoBonus())
			return;
		for (Città c : coloreCittà.getCittà()) {
			if (!c.emporioColore(giocatore.getColoreGiocatore()))
				return;
		}
		giocatore.getTessereBonus().add(coloreCittà.getBonusColore());
		coloreCittà.setAssegnatoBonus(true);
		if (!bonusRe.isEmpty()) {
			giocatore.getTessereBonus().add(bonusRe.remove(0));
		}
	}

	/**
	 * pay the Aiutanti in case there is/are Emporium in the city where the
	 * player wants to build
	 */
	private void pagaAiutanti(GameState gameState) {
		int aiutantiDaPagare = cittàCostruzione.getEmpori().size();
		gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(aiutantiDaPagare);

	}

	/**
	 * add an emporium to the city where the player wants to build
	 */
	private void costruisci(GameState gameState) {
		Emporio emporio = gameState.getGiocatoreCorrente().getEmpori().remove(0);
		this.cittàCostruzione.aggiungiEmporio(emporio);
	}

	/**
	 * give to the player the bonus of the city connected to the city where he
	 * has built an emporium.
	 */
	private void prendiBonus(GameState gameState, boolean nob) {
		Colore coloreEmporio = gameState.getGiocatoreCorrente().getColoreGiocatore();
		Set<CittàBonus> cittàCollegate = gameState.getMappa().trovaCittà(cittàCostruzione, coloreEmporio);
		for (CittàBonus c : cittàCollegate) {
			for (Bonus b : c.getBonus()) {
				b.usaBonus(gameState);
				if (b instanceof BonusPuntiNobiltà) {
					nob = true;
				} else
					nob = false;
			}
		}
	}

	/**
	 * check if the player has enough money to do the action, then it subtract
	 * the money due from the player
	 * 
	 * @param moneteDovute
	 * @return true if the player has enough money, false in the other case
	 */
	private boolean paga(int moneteDovute, GameState gameState) {
		if (!gameState.getGiocatoreCorrente().diminuisciRicchezza(moneteDovute))
			return false;
		return true;
	}

	/**
	 * check if the player has enough aiutanti to do the action, then it
	 * substract the aiutanti due from the player
	 * 
	 * @return true if the player has enough aiutanti, false in the other case
	 */
	private boolean pagoAiutanti(GameState gameState) {
		int numeroEmpori = cittàCostruzione.getEmpori().size();
		if (!cittàCostruzione.getEmpori().isEmpty()) {
			return gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(numeroEmpori);
		} else
			return true;
	}

	/**
	 * calculate the amount of money due by the player
	 * 
	 * @return the value of money that the player has to pay
	 */
	private int calcolaMonete() {

		int monete = 0;
		int carte = carteGiocatore.size();
		for (CartaPolitica carta : carteGiocatore) {
			if (carta.equals(new CartaPolitica(new Colore("Multicolore")))) {
				monete++;
			}
		}

		switch (carte) {
		case 1:
			if (carte == 1) {
				monete = monete + 10;
			}
			break;

		case 2:
			if (carte == 2) {
				monete = monete + 7;
			}
			break;
		case 3:
			if (carte == 3) {
				monete = monete + 4;
			}
			break;
		default:
			break;
		}
		return monete;

	}

	/**
	 * check if the cards of the player match the counselors of the balcony
	 * 
	 * @return true if the cards match, false in the other case
	 */
	private boolean controllaColori() {
		List<Consigliere> copiaConsiglieri = new ArrayList<>(balcone.getConsigliere());
		for (CartaPolitica carta : carteGiocatore) {
			boolean ok = false;
			if (carta.equals(new CartaPolitica(new Colore("Multicolore")))) {
				continue;
			}

			for (Consigliere consigliere : copiaConsiglieri) {
				if (!consigliere.getColore().equals(carta.getColore())) {
					ok = false;
					continue;
				}

				else {
					ok = true;
					copiaConsiglieri.remove(consigliere);
					break;
				}
			}
			if (!ok)
				return false; // se la carta non matcha
		}
		return true;

	}

	/**
	 * @param cittàCostruzione
	 *            the cittàCostruzione to set
	 */
	public void setCittàCostruzione(Città cittàCostruzione) {
		if(cittàCostruzione==null)
			throw new NullPointerException("La città in cui vuoi costruire non puù essere null");
		this.cittàCostruzione = cittàCostruzione;
	}

	/**
	 * @param carteGiocatore
	 *            the carteGiocatore to set
	 */
	public void setCarteGiocatore(List<CartaPolitica> carteGiocatore) {
		if(carteGiocatore==null)
			throw new NullPointerException("Le carte del giocatore non possono essere null");
		this.carteGiocatore = carteGiocatore;
	}

	/**
	 * @return DTO action
	 */
	@Override
	public AzioneDTO getAzioneDTO() {
		return new CostruzioneAiutoReDTO();
	}

}
