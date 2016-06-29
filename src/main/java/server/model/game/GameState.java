package server.model.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import server.model.bonus.Bonus;
import server.model.game.comparator.ComparatorClassifica;
import server.model.game.comparator.ComparatorPuntiNobiltà;
import server.model.game.comparator.ComparatorTesserePermesso;
import server.model.macchinaStati.StartEnd;
import server.model.macchinaStati.Stato;
import server.model.market.Offerta;
import server.model.notify.AvversarioNotify;
import server.model.notify.ClassificaNotify;
import server.model.notify.GameStateNotify;
import server.model.notify.GiocatoreNotify;
import server.model.notify.Notify;
import server.model.notify.PerdenteNotify;
import server.model.notify.VincitoreNotify;
import utility.Observable;

public class GameState extends Observable<Notify> {

	private Mappa mappa;
	private List<Regione> regioni;
	private PlanciaRe planciaRe;
	private Re pedinaRe;
	private List<Consigliere> consiglieri;
	private Mazzo<CartaPolitica> mazzoCartePolitica;
	private List<Giocatore> giocatori;
	private Giocatore giocatoreCorrente;
	private Stato stato;
	private boolean BonusAzionePrincipale;
	private int numeroTurni = 0;
	private List<Offerta> offerteMarket;
	private boolean ultimoGiro = false;
	private List<Giocatore> giocatoriFinePartita;
	private String nomeMappa;

	/**
	 * 
	 * @param coloreConsigliere
	 * @return Consigliere of the color which passed the player
	 */
	public Consigliere getConsigliere(String coloreConsigliere) {
		Consigliere consigliere = null;
		for (Consigliere c : consiglieri) {
			if (c.getColore().getColore().equals(coloreConsigliere)) {
				consigliere = c;
				break;
			}
		}
		return consigliere;
	}

	/**
	 * @return the giocatoreCorrente
	 */
	public Giocatore getGiocatoreCorrente() {
		return giocatoreCorrente;
	}

	/**
	 * @param giocatoreCorrente
	 *            the giocatoreCorrente to set
	 */
	public void setGiocatoreCorrente(Giocatore giocatoreCorrente) {
		this.giocatoreCorrente = giocatoreCorrente;
	}

	/**
	 * @return the offerteMarket
	 */
	public List<Offerta> getOfferteMarket() {
		return offerteMarket;
	}

	/**
	 * @param giocatori
	 *            the giocatori to set
	 */
	public void setGiocatori(List<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}

	/**
	 * @return the mappa
	 */
	public Mappa getMappa() {
		return mappa;
	}

	/**
	 * @return the regioni
	 */

	public List<Regione> getRegioni() {
		return regioni;
	}

	/**
	 * @return the planciaRe
	 */
	public PlanciaRe getPlanciaRe() {
		return planciaRe;
	}

	/**
	 * @return the pedinaRe
	 */
	public Re getPedinaRe() {
		return pedinaRe;
	}

	/**
	 * @return the consiglieri
	 */

	public List<Consigliere> getConsiglieri() {
		return consiglieri;
	}

	/**
	 * @return the mazzoCartePolitica
	 */
	public Mazzo<CartaPolitica> getMazzoCartePolitica() {
		return mazzoCartePolitica;
	}

	public Set<Città> getCittà() {
		return this.mappa.getGrafo().vertexSet();
	}

	/**
	 * @return the giocatori
	 */
	public List<Giocatore> getGiocatori() {
		return giocatori;
	}

	/**
	 * @return the stato
	 */
	public Stato getStato() {
		return stato;
	}

	/**
	 * @param stato
	 *            the stato to set
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}

	/**
	 * @return the bonusAzionePrincipale
	 */
	public boolean isBonusAzionePrincipale() {
		return BonusAzionePrincipale;
	}

	/**
	 * @param bonusAzionePrincipale
	 *            the bonusAzionePrincipale to set
	 */
	public void setBonusAzionePrincipale(boolean bonusAzionePrincipale) {
		BonusAzionePrincipale = bonusAzionePrincipale;
	}

	/**
	 * @return the numeroTurni
	 */
	public int getNumeroTurni() {
		return numeroTurni;
	}

	public void setNumeroTurni(int numeroTurni) {
		this.numeroTurni = numeroTurni;
	}

	/**
	 * @return the ultimoGiro
	 */
	public boolean isUltimoGiro() {
		return ultimoGiro;
	}

	/**
	 * @param ultimoGiro
	 *            the ultimoGiro to set
	 */
	public void setUltimoGiro(boolean ultimoGiro) {
		this.ultimoGiro = ultimoGiro;
	}

	/**
	 * @return the giocatoriFinePartita
	 */
	public List<Giocatore> getGiocatoriFinePartita() {
		return giocatoriFinePartita;
	}

	/**
	 * @param giocatoriFinePartita
	 *            the giocatoriFinePartita to set
	 */
	public void setGiocatoriFinePartita(List<Giocatore> giocatoriFinePartita) {
		this.giocatoriFinePartita = giocatoriFinePartita;
	}

	/**
	 * @return the giocatoriDisconnessi
	 */

	/**
	 * @param numeroTurni
	 *            the numeroTurni to set
	 */
	public void prossimoTurno() {
		this.numeroTurni++;
	}

	
	/**
	 * create players at start of the game
	 * 
	 * @param giocatori
	 */
	/*public void creaGiocatori(List<Giocatore> giocatori) {
		int i = 0;
		for (Giocatore g : giocatori) {
			g.setAiutanti(new Aiutante(18 + i));
			g.setPunteggioNobiltà(this.getPlanciaRe().getPercorsoNobiltà().get(13));
			g.setPunteggioRicchezza(19 + i);
			g.setPunteggioVittoria(0);
			g.setColoreGiocatore(new Colore(String.valueOf(i)));
			g.creaEmpori(g.getColoreGiocatore());
			g.getCartePolitica().addAll(assegnaCartePolitica(10));
			this.giocatori.add(g);
			i++;
		}

		if (giocatori.size() == 2) {
			for (Regione r : getRegioni()) {
				Random random = new Random();
				int numeroEmpori = random.nextInt(3) + 1;

				for (int s = 0; s < numeroEmpori; s++) {
					int rnd = random.nextInt(r.getCittàRegione().size());
					if (r.getCittàRegione().get(rnd).getEmpori().isEmpty())
						r.getCittàRegione().get(rnd).aggiungiEmporio(new Emporio(new Colore("2")));
					else
						s--;
				}

			}
		}
		this.giocatoreCorrente = this.giocatori.get(0);
	}*/

	
	public void creaGiocatori(List<Giocatore> giocatori) {
		int i = 0;
		for (Giocatore g : giocatori) {
			g.setAiutanti(new Aiutante(3 + i));
			g.setPunteggioNobiltà(this.getPlanciaRe().getPercorsoNobiltà().get(0));
			g.setPunteggioRicchezza(10 + i);
			g.setPunteggioVittoria(0);
			g.setColoreGiocatore(new Colore(String.valueOf(i)));
			g.creaEmpori(g.getColoreGiocatore());
			g.getCartePolitica().addAll(assegnaCartePolitica(6));
			this.giocatori.add(g);
			i++;
		}

		if (giocatori.size() == 2) {
			for (Regione r : getRegioni()) {
				Random random = new Random();
				int numeroEmpori = random.nextInt(3) + 1;

				for (int s = 0; s < numeroEmpori; s++) {
					int rnd = random.nextInt(r.getCittàRegione().size());
					if (r.getCittàRegione().get(rnd).getEmpori().isEmpty())
						r.getCittàRegione().get(rnd).aggiungiEmporio(new Emporio(new Colore("2")));
					else
						s--;
				}

			}
		}
		this.giocatoreCorrente = this.giocatori.get(0);
	}
	/**
	 * set politic cards to each player in the game
	 * 
	 * @param numeroCarte
	 * @return
	 */
	private List<CartaPolitica> assegnaCartePolitica(int numeroCarte) {
		ArrayList<CartaPolitica> carte = new ArrayList<>();
		for (int i = 0; i < numeroCarte; i++) {
			carte.add(mazzoCartePolitica.pescaCarte());
		}

		return carte;
	}

	/**
	 * change player at the end of the turn
	 */
	public void nextPlayer() {
		Giocatore fineTurno = giocatori.remove(0);
		giocatori.add(fineTurno);
		this.giocatoreCorrente = giocatori.get(0);

	}

	/**
	 * 
	 * @return true if next player is the last
	 */
	public boolean lastNextPlayer() {
		Giocatore ultimoGiro = giocatori.remove(0);
		giocatoriFinePartita.add(ultimoGiro);
		if (giocatori.isEmpty()) {
			return true;
		} else {
			this.giocatoreCorrente = giocatori.get(0);
			return false;
		}
	}

	/**
	 * return turn -1
	 */
	public void decrementaTurno() {
		this.numeroTurni--;
	}

	/**
	 * initialize game
	 * 
	 * @param giocatori
	 * @param mappa 
	 * @throws IOException
	 */
	public void start(List<Giocatore> giocatori, String mappa) throws IOException {
		this.nomeMappa=mappa;
		Reader reader = new Reader();
		this.consiglieri = reader.letturaConsigliere();
		this.regioni = reader.letturaRegioni();
		this.planciaRe = reader.creazionePlanciaRe();
		this.mazzoCartePolitica = reader.letturaCartePolitica();
		this.mappa = reader.creazioneMappa(mappa);
		this.pedinaRe = reader.creazioneRe();
		this.giocatori = new ArrayList<>();
		this.offerteMarket = new ArrayList<>();
		this.giocatoriFinePartita = new ArrayList<>();
		creaGiocatori(giocatori);
		this.notifyObserver(new GameStateNotify(this, giocatori));

		for (Giocatore g : giocatori) {
			this.notifyObserver(new GiocatoreNotify(g, Arrays.asList(g)));
			List<Giocatore> avversari = new ArrayList<>(giocatori);
			avversari.remove(g);
			for (Giocatore a : avversari) {
				this.notifyObserver(new AvversarioNotify(a, Arrays.asList(g)));
			}

		}
		this.stato = new StartEnd(this);
	}

	/**
	 * calculate the winner at the end of the game
	 * 
	 * @return plyers who won
	 */
	public List<Giocatore> calcolaVincitore() {
		calcolaTesserePermesso();
		calcolaPunteggioNobiltà();
		assegnaTessereBonus();
		return vincitore();
	}

	/**
	 * @return players that have best points and so the winners
	 */
	private List<Giocatore> vincitore() {

		ArrayList<Giocatore> giocatoritmp = new ArrayList<>(this.getGiocatoriFinePartita());
		ArrayList<Giocatore> giocatoriPerdenti = new ArrayList<>();
		ComparatorClassifica comparator = new ComparatorClassifica();
		Collections.sort(giocatoritmp, comparator);
		List<Giocatore> vincitori = new ArrayList<>();
		vincitori.add(giocatoritmp.get(0));
		giocatoritmp.remove(vincitori.get(0));

		for (Giocatore g : giocatoritmp) {
			if (comparator.compare(g, vincitori.get(0)) != 0) {
				giocatoriPerdenti.add(g);
				break;
			} else {
				vincitori.add(g);
				// giocatoriPerdenti.remove(g);

				if (giocatoritmp.isEmpty())
					break;
			}
		}

		for (Giocatore g : vincitori) {
			this.notifyObserver(new VincitoreNotify(Arrays.asList(g), g));
		}

		for (Giocatore g : giocatoriPerdenti) {
			this.notifyObserver(new PerdenteNotify(Arrays.asList(g), g));
		}

		this.notifyObserver(new ClassificaNotify(vincitori, giocatoriPerdenti, giocatoriFinePartita));
		return vincitori;
	}

	/**
	 * add points of TessereBonus to the players who owns that.
	 * 
	 * @param gameState
	 */
	private void assegnaTessereBonus() {
		for (Giocatore g : giocatoriFinePartita) {
			this.setGiocatoreCorrente(g);
			if (!g.getTessereBonus().isEmpty()) {
				for (Bonus b : g.getTessereBonus()) {
					b.usaBonus(this);
				}
			}
		}
	}

	/**
	 * Sort giocatori by order decrescent of number of TesserePermesso (coperte
	 * e scoperte) Each player with the best score of number of tessere permesso
	 * win 3 points in Punteggio Vittoria
	 * 
	 * @param gameState
	 */
	private void calcolaTesserePermesso() {
		Collections.sort(giocatoriFinePartita, new ComparatorTesserePermesso());

		int i = 0;
		while (i < giocatoriFinePartita.size()) {
			if (giocatoriFinePartita.get(i).getNumeroTesserePermesso() == giocatoriFinePartita.get(0)
					.getNumeroTesserePermesso()) {
				giocatoriFinePartita.get(i).aumentaPuntiVittoria(3);
				i++;
			} else
				break;
		}
	}

	/**
	 * this method creates two arraylist: one for players who obtain the best
	 * points on Punteggio Nobiltà, and the second for players who do the second
	 * best points in Punteggio Nobiltà Comparator is the method that sort the
	 * array giocatori in order decrescent of points of nobility
	 * 
	 * @param gameState
	 */
	private void calcolaPunteggioNobiltà() {
		Collections.sort(giocatoriFinePartita, new ComparatorPuntiNobiltà());
		ArrayList<Giocatore> giocatori = new ArrayList<>(giocatoriFinePartita);
		ArrayList<Giocatore> primo = new ArrayList<>();
		primo.add(giocatori.get(0));
		giocatori.remove(primo.get(0));
		ArrayList<Giocatore> secondo = new ArrayList<>();

		for (Giocatore g : giocatori) {
			int punti = g.getPunteggioNobiltà().getPuntiNobiltà();

			if (punti == primo.get(0).getPunteggioNobiltà().getPuntiNobiltà()) {
				primo.add(g);
			} else if ((punti != primo.get(0).getPunteggioNobiltà().getPuntiNobiltà() && secondo.isEmpty())
					|| punti == secondo.get(0).getPunteggioNobiltà().getPuntiNobiltà()) {
				secondo.add(g);
				// break;
			}
		}
		assegnaPunti(primo, secondo);

	}

	/**
	 * This method add at players points extra for the best points if there are
	 * more then one player in primo, all that player win 5 points in Punteggio
	 * vittoria and there isn't a second position if there is just one player
	 * with the best score, he win 5 points extra in Punteggio Vittoria and all
	 * players with second best score win 3 points of Punteggio Vittoria.
	 * 
	 * @param primo
	 * @param secondo
	 */
	private void assegnaPunti(ArrayList<Giocatore> primo, ArrayList<Giocatore> secondo) {
		for (Giocatore g : primo) {
			g.aumentaPuntiVittoria(5);
			if (primo.size() == 1) {
				for (Giocatore g2 : secondo) {
					g2.aumentaPuntiVittoria(2);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return mappa + "\nRegioni = [" + regioni + "\nplanciaRe=" + planciaRe + ", \npedinaRe=" + pedinaRe
				+ "\nconsiglieri=" + consiglieri + "\nmazzoCartePolitica=" + mazzoCartePolitica + "\ngiocatori="
				+ giocatori + "\ngiocatoreCorrente=" + giocatoreCorrente + "\nstato=" + stato
				+ "\nBonusAzionePrincipale=" + BonusAzionePrincipale + "\nnumeroTurni=" + numeroTurni
				+ "\nofferteMarket=" + offerteMarket + "]";
	}

	/**
	 * @return the nomeMappa
	 */
	public String getNomeMappa() {
		return nomeMappa;
	}

}
