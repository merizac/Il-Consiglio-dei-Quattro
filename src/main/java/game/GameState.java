package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import game.macchinaStati.StartEnd;
import game.macchinaStati.Stato;
import game.market.Offerta;
import game.notify.GameStateNotify;
import game.notify.GiocatoreNotify;
import game.notify.Notify;
import it.polimi.ingsw.cg17.Reader;
import utility.Observable;

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
	private int numeroTurni = 0;
	private List<Offerta> offerteMarket;
	private boolean ultimoGiro= false;
	private List<Giocatore> giocatoriFinePartita;

	
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
		this.giocatoreCorrente = giocatori.get(0);

	}

	/*public void cambiaGiocatore() {
		int indice = giocatori.indexOf(giocatoreCorrente);
		if (indice != giocatori.size() - 1)
			giocatoreCorrente = giocatori.get(indice + 1);
		else
			giocatoreCorrente = giocatori.get(0);
	}*/
	
	public boolean lastNextPlayer(){
		Giocatore ultimoGiro = giocatori.remove(0);
		giocatoriFinePartita.add(ultimoGiro);
		System.out.println("last next player giocatori:" +giocatori);
		System.out.println("lastnextplayer giocatoriFinePartita: "+ giocatoriFinePartita);
		if(giocatori.isEmpty()){
			return true;
		}
		else{
		this.giocatoreCorrente = giocatori.get(0);
		System.out.println("giocatore corrente lastnextplayer: "+ giocatoreCorrente);
		return false;
		}
	}

	public void decrementaTurno() {
		this.numeroTurni--;
	}

	public void start(List<Giocatore> giocatori) throws IOException {
		Reader reader= new Reader();
		this.consiglieri = reader.letturaConsigliere();
		this.regioni = reader.letturaRegioni();
		this.planciaRe = reader.creazionePlanciaRe();
		this.mazzoCartePolitica = reader.letturaCartePolitica();
		this.mappa = reader.creazioneMappa("mappa1");
		this.pedinaRe = reader.creazioneRe();
		//Reader.clear();
		this.giocatori = new ArrayList<Giocatore>();
		this.offerteMarket = new ArrayList<>();
		this.giocatoriFinePartita= new ArrayList<>();
		creaGiocatori(giocatori);
		this.notifyObserver(new GameStateNotify(this, giocatori));
		
		for(Giocatore g: giocatori){
			this.notifyObserver(new GiocatoreNotify(g, Arrays.asList(g)));
		}
		this.stato = new StartEnd(this);

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
	 * @return the ultimoGiro
	 */
	public boolean isUltimoGiro() {
		return ultimoGiro;
	}

	/**
	 * @param ultimoGiro the ultimoGiro to set
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
	 * @param giocatoriFinePartita the giocatoriFinePartita to set
	 */
	public void setGiocatoriFinePartita(List<Giocatore> giocatoriFinePartita) {
		this.giocatoriFinePartita = giocatoriFinePartita;
	}

}
