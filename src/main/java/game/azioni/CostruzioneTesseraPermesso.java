package game.azioni;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import server.model.azioni.azioniPrincipali.AzionePrincipale;
import server.model.bonus.Bonus;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Colore;
import server.model.game.ColoreCittà;
import server.model.game.Emporio;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import server.model.notify.GameStateNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.MessageNotify;

public class CostruzioneTesseraPermesso extends AzionePrincipale implements Bonusable {

	private final int ID = 3;
	private TesseraPermesso tesseraPermessoScoperta;
	private Città cittàCostruzione;

	/**
	 * execute the action
	 */
	@Override
	public void eseguiAzione(GameState gameState) {

		if (!pagoAiutanti(gameState)) {
			gameState.notifyObserver(new MessageNotify("Errore: i soldi non sono sufficienti",
					Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}

		costruisci(gameState);
		prendiBonus(gameState);
		copriTessera(gameState);

		if (cittàCostruzione instanceof CittàBonus)
			controllaCittàColore(((ColoreCittà) cittàCostruzione.getColoreCittà()), gameState.getGiocatoreCorrente());
		controllaCittàRegione(cittàCostruzione.getRegione(), gameState.getGiocatoreCorrente());

		if (gameState.getGiocatoreCorrente().getEmpori().isEmpty()) {
			gameState.setUltimoGiro(true);
			gameState.getGiocatoreCorrente().aumentaPuntiVittoria(3);
		}

		gameState.notifyObserver(new GameStateNotify(gameState, gameState.getGiocatori()));
		gameState.notifyObserver(
				new GiocatoreNotify(gameState.getGiocatoreCorrente(), Arrays.asList(gameState.getGiocatoreCorrente())));

		ArrayList<Bonus> bonusCasella = gameState.getGiocatoreCorrente().getPunteggioNobiltà().getBonus();

		if (!bonusCasella.isEmpty()) {
			if (controlloBonus(gameState))
				setStatoTransizionePrincipale(gameState);
			else {
				gameState.getStato().transizioneBonus(gameState);

			}
		}

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
		// System.out.println(gameState.getGiocatoreCorrente().getTesserePermesso());
		gameState.getGiocatoreCorrente().getTesserePermesso().remove(tesseraPermessoScoperta);
		// System.out.println(gameState.getGiocatoreCorrente().getTesserePermesso());
		gameState.getGiocatoreCorrente().getTesserePermessoUsate().add(tesseraPermessoScoperta);
		// System.out.println(gameState.getGiocatoreCorrente().getTesserePermessoUsate());
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
		Set<CittàBonus> cittàCollegate = gameState.getMappa().trovaCittà(cittàCostruzione, coloreEmporio);
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

	@Override
	public AzioneDTO getAzioneDTO() {
		return new CostruzioneTesseraPermessoDTO();
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
		result = prime * result + ID;
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
		CostruzioneTesseraPermesso other = (CostruzioneTesseraPermesso) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

}
