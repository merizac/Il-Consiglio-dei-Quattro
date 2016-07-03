package server.model.azioni.azioniPrincipali;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneDTO;
import server.model.azioni.azioniBonus.Bonusable;
import server.model.bonus.Bonus;
import server.model.bonus.BonusPuntiNobiltà;
import server.model.game.CartaPolitica;
import server.model.game.Colore;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import server.model.notify.MessageNotify;

public class AcquistoTesseraPermesso extends AzionePrincipale implements Bonusable {

	private List<CartaPolitica> carteGiocatore;
	private Regione regione;
	private TesseraPermesso tesseraScoperta;

	/**
	 * check if the color of cards passed are the same of balcone, and check if
	 * the player has enough money to do the action, then subtract the money due
	 * from the player
	 * 
	 * @throws AzioneNonEseguibile
	 */
	@Override
	public void eseguiAzione(GameState gameState) {

		boolean nob = false;

		if (carteGiocatore.isEmpty()) {
			gameState.notifyObserver(new MessageNotify("Errore: non sono presenti carte\n",
					Arrays.asList(gameState.getGiocatoreCorrente()), false));
			return;
		}

		if (!controllaColori()) {
			gameState.notifyObserver(
					new MessageNotify("Errore: i colori delle carte scelte non corrispondono con quelle del balcone!\n",
							Arrays.asList(gameState.getGiocatoreCorrente()), false));
			return;
		}

		if (!paga(calcolaMonete(), gameState)) {
			gameState.notifyObserver(new MessageNotify("Errore: i soldi non sono sufficienti!\n",
					Arrays.asList(gameState.getGiocatoreCorrente()), false));
			return;
		}

		for (CartaPolitica c : carteGiocatore) {
			gameState.getGiocatoreCorrente().getCartePolitica().remove(c);
			gameState.getMazzoCartePolitica().getCarte().add(c);
		}

		regione.getTesserePermessoScoperte().remove(tesseraScoperta);
		gameState.getGiocatoreCorrente().getTesserePermesso().add(tesseraScoperta);

		regione.getTesserePermessoScoperte().add(regione.getMazzoTesserePermesso().pescaCarte());

		for (Bonus b : tesseraScoperta.getBonus()) {
			b.usaBonus(gameState);
			if (b instanceof BonusPuntiNobiltà) {
				nob = true;
			}
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
	 * 
	 * @param moneteDovute
	 * @return true if the player can pay the money, false otherwise
	 */
	private boolean paga(int moneteDovute, GameState gameState) {
		if (!gameState.getGiocatoreCorrente().diminuisciRicchezza(moneteDovute))
			return false;
		return true;
	}

	/**
	 * 
	 * @return how many money the player have to pay
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
			monete = monete + 10;
			break;

		case 2:
			monete = monete + 7;
			break;

		case 3:
			monete = monete + 4;
			break;

		default:
			break;
		}
		return monete;

	}

	/**
	 * 
	 * @return false if the cards of the player didn't match
	 */
	private boolean controllaColori() {
		List<Consigliere> copiaConsiglieri = new ArrayList<>(regione.getBalcone().getConsigliere());
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
	 * @return the carteGiocatore
	 */
	public List<CartaPolitica> getCarteGiocatore() {
		return carteGiocatore;
	}

	/**
	 * @param carteGiocatore
	 *            the carteGiocatore to set
	 */
	public void setCarteGiocatore(List<CartaPolitica> carteGiocatore) {
		this.carteGiocatore = carteGiocatore;
	}

	/**
	 * @return the regione
	 */
	public Regione getRegione() {
		return regione;
	}

	/**
	 * @param regione
	 *            the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}

	/**
	 * @return the indiceTesseraScoperta
	 */
	public TesseraPermesso getTesseraScoperta() {
		return tesseraScoperta;
	}

	/**
	 * @return DTO action
	 */
	@Override
	public AzioneDTO getAzioneDTO() {
		return new AcquistoTesseraPermessoDTO();
	}

	/**
	 * @param tesseraScoperta
	 *            the tesseraScoperta to set
	 */
	public void setTesseraScoperta(TesseraPermesso tesseraScoperta) {
		if (tesseraScoperta == null)
			throw new IllegalArgumentException("La tessera permesso non può esserre null");
		this.tesseraScoperta = tesseraScoperta;
	}

}
