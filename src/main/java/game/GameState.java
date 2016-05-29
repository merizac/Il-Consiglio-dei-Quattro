package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import controller.StartEnd;
import controller.Stato;
import game.market.Offerta;
import game.notify.GameNotify;
import game.notify.Notify;
import gameDTO.gameDTO.GameStateDTO;
import it.polimi.ingsw.cg17.Reader;
import utility.Observable;
import utility.exception.AzioneNonEseguibile;

public class GameState extends Observable<Notify> {

	private Mappa mappa;
	private ArrayList<Regione> regioni;
	private PlanciaRe planciaRe;
	private Re pedinaRe;
	private ArrayList<Consigliere> consiglieri;
	private Mazzo<CartaPolitica> mazzoCartePolitica;
	private List<Giocatore> giocatori;
	private Giocatore giocatoreCorrente;
	private Stato stato;
	private boolean BonusAzionePrincipale;
	private int numeroTurni = -1;
	private List<Offerta> offerteMarket;

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
	 * @param numeroTurni
	 *            the numeroTurni to set
	 */
	public void prossimoTurno() {
		this.numeroTurni++;
	}

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

	public ArrayList<Regione> getRegioni() {
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

	public ArrayList<Consigliere> getConsiglieri() {
		return consiglieri;
	}

	/**
	 * @return the mazzoCartePolitica
	 */
	public Mazzo<CartaPolitica> getMazzoCartePolitica() {
		return mazzoCartePolitica;
	}

	public List<CartaPolitica> getCartePoliticaGiocatore() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TesseraPermesso> getTesserePermessoGiocatore() {
		// TODO Auto-generated method stub
		return null;
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

	public void creaGiocatori(List<Giocatore> giocatori) {
		int i = 0;
		for (Giocatore g : giocatori) {
			g.setAiutanti(new Aiutante(1 + i));
			g.setPunteggioNobiltà(this.getPlanciaRe().getPercorsoNobiltà().get(0));
			g.setPunteggioRicchezza(10 + i);
			g.setPunteggioVittoria(0);
			g.setColoreGiocatore(new Colore(String.valueOf(i)));
			g.creaEmpori(g.getColoreGiocatore());
			g.getCartePolitica().addAll(assegnaCartePolitica(6));
			this.giocatori.add(g);
			i++;
		}
		this.giocatoreCorrente = this.giocatori.get(0);
	}

	private ArrayList<CartaPolitica> assegnaCartePolitica(int numeroCarte) {
		ArrayList<CartaPolitica> carte = new ArrayList<>();
		for (int i = 0; i < numeroCarte; i++) {
			carte.add(mazzoCartePolitica.pescaCarte());
		}

		return carte;
	}

	public void nextPlayer() {
		Giocatore fineTurno = giocatori.remove(0);
		giocatori.add(fineTurno);

	}

	public void cambiaGiocatore() {
		int indice = giocatori.indexOf(giocatoreCorrente);
		if (indice != giocatori.size() - 1)
			giocatoreCorrente = giocatori.get(indice + 1);
		else
			giocatoreCorrente = giocatori.get(0);
	}

	public void decrementaTurno() {
		this.numeroTurni--;

	}

	public void start(List<Giocatore> giocatori) throws IOException {
		this.consiglieri = Reader.letturaConsigliere();
		this.regioni = Reader.letturaRegioni();
		this.planciaRe = Reader.creazionePlanciaRe();
		this.mazzoCartePolitica = Reader.letturaCartePolitica();
		this.mappa = Reader.creazioneMappa("mappa1");
		this.pedinaRe = Reader.creazioneRe();
		this.giocatori = new ArrayList<Giocatore>();
		this.offerteMarket = new ArrayList<>();
		creaGiocatori(giocatori);
		try {
			this.stato = new StartEnd(this);
		} catch (AzioneNonEseguibile e) {
			e.printStackTrace();
		}
		GameStateDTO gameStateDTO=new GameStateDTO();
		gameStateDTO.inizializza(this);
		System.out.println(gameStateDTO);
		this.notifyObserver(new GameNotify(gameStateDTO));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  mappa + "\nRegioni = [" + regioni + "\nplanciaRe=" + planciaRe + ", \npedinaRe="
				+ pedinaRe + "\nconsiglieri=" + consiglieri + "\nmazzoCartePolitica=" + mazzoCartePolitica
				+ "\ngiocatori=" + giocatori + "\ngiocatoreCorrente=" + giocatoreCorrente + "\nstato=" + stato
				+ "\nBonusAzionePrincipale=" + BonusAzionePrincipale + "\nnumeroTurni=" + numeroTurni
				+ "\nofferteMarket=" + offerteMarket + "]";
	}

}
