package game.azioni;

import java.util.Arrays;
import java.util.HashSet;

import bonus.Bonus;
import game.Città;
import game.CittàBonus;
import game.Colore;
import game.ColoreCittà;
import game.Emporio;
import game.GameState;
import game.Giocatore;
import game.Regione;
import game.TesseraPermesso;
import game.notify.ErrorNotify;
import game.notify.GameStateNotify;
import game.notify.GiocatoreNotify;

public class CostruzioneTesseraPermesso extends AzionePrincipale {

	private TesseraPermesso tesseraPermessoScoperta;
	private Città cittàCostruzione;

	/**
	 * execute the action
	 */
	@Override
	public void eseguiAzione(GameState gameState) {

		if (!pagoAiutanti(gameState)) {
			gameState.notifyObserver(new ErrorNotify("Errore: i soldi non sono sufficienti",
					Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}

		costruisci(gameState);
		prendiBonus(gameState);
		copriTessera(gameState);

		if (cittàCostruzione instanceof CittàBonus)
			controllaCittàColore(((ColoreCittà) cittàCostruzione.getColoreCittà()), gameState.getGiocatoreCorrente());
		controllaCittàRegione(cittàCostruzione.getRegione(), gameState.getGiocatoreCorrente());

		gameState.notifyObserver(new GameStateNotify(gameState, gameState.getGiocatori()));
		gameState.notifyObserver(
				new GiocatoreNotify(gameState.getGiocatoreCorrente(), Arrays.asList(gameState.getGiocatoreCorrente())));
		setStatoTransizionePrincipale(gameState);

	}

	private void controllaCittàRegione(Regione regione, Giocatore giocatore) {
		for (Città c : regione.getCittàRegione()) {
			if (!c.emporioColore(giocatore.getColoreGiocatore()))
				return;
		}
		giocatore.getTessereBonus().add(regione.getBonusRegione());
		regione.setBonusAssegnato(true);

	}

	private void controllaCittàColore(ColoreCittà coloreCittà, Giocatore giocatore) {
		if (coloreCittà.isAssegnatoBonus())
			return;
		for (Città c : coloreCittà.getCittà()) {
			if (!c.emporioColore(giocatore.getColoreGiocatore()))
				return;
		}
		giocatore.getTessereBonus().add(coloreCittà.getBonusColore());
		coloreCittà.setAssegnatoBonus(true);
	}

	/**
	 * move the permit tile used from tesserePermesso to tesserePermessoUsate
	 */
	private void copriTessera(GameState gameState) {
		System.out.println(gameState.getGiocatoreCorrente().getTesserePermesso());
		gameState.getGiocatoreCorrente().getTesserePermesso().remove(tesseraPermessoScoperta);
		System.out.println(gameState.getGiocatoreCorrente().getTesserePermesso());
		gameState.getGiocatoreCorrente().getTesserePermessoUsate().add(tesseraPermessoScoperta);
		System.out.println(gameState.getGiocatoreCorrente().getTesserePermessoUsate());
	}

	/**
	 * check if the player has enough aiutanti and then it subtract them from
	 * the player
	 * 
	 * @return true if the player has enough aiutanti, false in the other case
	 */
	private boolean pagoAiutanti(GameState gameState) {
		int numeroEmpori = cittàCostruzione.getEmpori().size();

		if (cittàCostruzione.getEmpori().isEmpty()) {
			return true;
		}

		if (!cittàCostruzione.getEmpori().isEmpty()) {
			if (gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(numeroEmpori)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * build an emporio to the city selected
	 */
	private void costruisci(GameState gameState) {
		Emporio emporio = gameState.getGiocatoreCorrente().getEmpori().remove(0);
		this.cittàCostruzione.aggiungiEmporio(emporio);
	}

	/**
	 * give to the player the bonus of the city connected to the city where the
	 * player has built
	 */
	private void prendiBonus(GameState gameState) {
		Colore coloreEmporio = gameState.getGiocatoreCorrente().getColoreGiocatore();
		HashSet<CittàBonus> cittàCollegate = gameState.getMappa().trovaCittà(cittàCostruzione, coloreEmporio);
		for (CittàBonus c : cittàCollegate) {
			for (Bonus b : c.getBonus()) {
				b.usaBonus(gameState);
			}
		}
	}

	/**
	 * @param tesseraPermessoScoperta
	 *            the tesseraPermessoScoperta to set
	 */
	public void setTesseraPermessoScoperta(TesseraPermesso tesseraPermessoScoperta) {
		this.tesseraPermessoScoperta = tesseraPermessoScoperta;
	}

	/**
	 * @param cittàCostruzione
	 *            the cittàCostruzione to set
	 */
	public void setCittàCostruzione(Città cittàCostruzione) {
		this.cittàCostruzione = cittàCostruzione;
	}

	/**
	 * @return the tesseraPermessoScoperta
	 */
	public TesseraPermesso getTesseraPermessoScoperta() {
		return tesseraPermessoScoperta;
	}

	/**
	 * @return the cittàCostruzione
	 */
	public Città getCittàCostruzione() {
		return cittàCostruzione;
	}

}
