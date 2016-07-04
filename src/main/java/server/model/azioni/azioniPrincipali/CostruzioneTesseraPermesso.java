package server.model.azioni.azioniPrincipali;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import server.model.azioni.azioniBonus.Bonusable;
import server.model.bonus.Bonus;
import server.model.bonus.BonusPuntiNobiltà;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Colore;
import server.model.game.ColoreCittà;
import server.model.game.Emporio;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import server.model.notify.MessageNotify;

public class CostruzioneTesseraPermesso extends AzionePrincipale implements Bonusable {

	private TesseraPermesso tesseraPermessoScoperta;
	private Città cittàCostruzione;

	/**
	 * check if player had enough money build in the city that player choose win
	 * bonus if the city is a bonus city or if it's next to bonus cities of the
	 * same player notify changes check if player win nobility points, in that
	 * case check if there are bonus in nobility track. set the right transition
	 * in state pattern
	 * 
	 */
	@Override
	public void eseguiAzione(GameState gameState) {
		boolean nob = false;
		if (!pagoAiutanti(gameState)) {
			gameState.notifyObserver(new MessageNotify("Errore: gli aiutanti non sono sufficienti\n",
					Arrays.asList(gameState.getGiocatoreCorrente()), false));
			return;
		}

		costruisci(gameState);
		nob=prendiBonus(gameState, nob);
		copriTessera(gameState);

		if (cittàCostruzione instanceof CittàBonus)
			controllaCittàColore((ColoreCittà) cittàCostruzione.getColoreCittà(), gameState.getGiocatoreCorrente(),
					gameState.getPlanciaRe().getBonusPremioRe());
		controllaCittàRegione(cittàCostruzione.getRegione(), gameState.getGiocatoreCorrente(),
				gameState.getPlanciaRe().getBonusPremioRe());

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
	 * check if player can win bonus of the region
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
	 * move the permit tile used from tesserePermesso to tesserePermessoUsate
	 */
	private void copriTessera(GameState gameState) {
		gameState.getGiocatoreCorrente().getTesserePermesso().remove(tesseraPermessoScoperta);
		gameState.getGiocatoreCorrente().getTesserePermessoUsate().add(tesseraPermessoScoperta);
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

		if (!cittàCostruzione.getEmpori().isEmpty()
				&& gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(numeroEmpori)) {
			return true;
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
	private boolean prendiBonus(GameState gameState, boolean nob) {
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
		
		return nob;
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

	/**
	 * @return DTO action
	 */
	@Override
	public AzioneDTO getAzioneDTO() {
		return new CostruzioneTesseraPermessoDTO();
	}

}
