package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import controller.StartEnd;
import controller.Stato;
import game.market.Offerta;
import game.notify.Notify;
import it.polimi.ingsw.cg17.Reader_new;
import utility.Observable;

public class GameState extends Observable<Notify> implements Model{
	private final Mappa mappa;
	private final ArrayList<Regione> regioni;
	private final PlanciaRe planciaRe;
	private final Re pedinaRe;
	private final ArrayList<Consigliere> consiglieri;
	private final Mazzo<CartaPolitica> mazzoCartePolitica;
	private ArrayList<Giocatore> giocatori;
	private Giocatore giocatoreCorrente;
	private Stato stato;
	private boolean BonusAzionePrincipale;
	private int numeroTurni=-1;
	private final List<Offerta> offerteMarket;

	/**
	 * @param mappa
	 * @param regioni
	 * @param planciaRe
	 * @param pedinaRe
	 * @param consiglieri
	 * @param mazzoCartePolitica
	 * @throws IOException 
	 */
	public GameState() throws IOException {
		this.consiglieri = Reader_new.letturaConsigliere();
		this.regioni = Reader_new.letturaRegioni();
		this.planciaRe = Reader_new.creazionePlanciaRe();
		this.mazzoCartePolitica = Reader_new.letturaCartePolitica("src/main/resources/cartaPolitica.txt");
		this.mappa = Reader_new.creazioneMappa("src/main/resources/città.txt");
		this.pedinaRe = Reader_new.creazioneRe();
		this.giocatori = new ArrayList<Giocatore>();
		this.stato= new StartEnd(this);
		this.offerteMarket=new ArrayList<>();
	}
	/**
	 * @return the numeroTurni
	 */
	public int getNumeroTurni() {
		return numeroTurni;
	}

	public void setNumeroTurni(int numeroTurni) {
		this.numeroTurni=numeroTurni;
	}
	/**
	 * @param numeroTurni the numeroTurni to set
	 */
	public void prossimoTurno() {
		this.numeroTurni = this.numeroTurni++;
	}
	/**
	 * 
	 * @param coloreConsigliere
	 * @return Consigliere of the color which passed the player
	 */
	public Consigliere getConsigliere (String coloreConsigliere){
		Consigliere consigliere=null;
		for (Consigliere c: consiglieri){
			if(c.getColore().getColore().equals(coloreConsigliere)){
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
	 * @param giocatoreCorrente the giocatoreCorrente to set
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
	 * @param giocatori the giocatori to set
	 */
	public void setGiocatori(ArrayList<Giocatore> giocatori) {
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
	@Override
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
	public ArrayList<Giocatore> getGiocatori() {
		return giocatori;
	}
	/**
	 * @return the stato
	 */
	public Stato getStato() {
		return stato;
	}
	/**
	 * @param stato the stato to set
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
	 * @param bonusAzionePrincipale the bonusAzionePrincipale to set
	 */
	public void setBonusAzionePrincipale(boolean bonusAzionePrincipale) {
		BonusAzionePrincipale = bonusAzionePrincipale;
	}
	
	public void creaGiocatori(int numeroGiocatori){
		int monete=10;
		Set<Colore> coloreGiocatori=creaColori(numeroGiocatori);
		/*for(int i=0; i<numeroGiocatori; i++){
			Colore coloreGiocatore=coloreGiocatori.iterator().next();
			Giocatore giocatore=new Giocatore(coloreGiocatore, assegnaCartePolitica(6), new Aiutante(3), 0, monete+i,
					planciaRe.getPercorsoNobiltà().get(0) , creaEmpori(10, coloreGiocatore));
			giocatori.add(giocatore); 
		}*/
		
		this.giocatori.add(new Giocatore(new Colore("Giallo"), assegnaCartePolitica(6), new Aiutante(3), 0, monete,
					planciaRe.getPercorsoNobiltà().get(0) , creaEmpori(10, new Colore("Giallo"))));
		this.giocatori.add(new Giocatore(new Colore("Verde"), assegnaCartePolitica(6), new Aiutante(3), 0, monete+1,
				planciaRe.getPercorsoNobiltà().get(0) , creaEmpori(10, new Colore("Verde"))));
		this.giocatori.add(new Giocatore(new Colore("Blu"), assegnaCartePolitica(6), new Aiutante(3), 0, monete+2,
				planciaRe.getPercorsoNobiltà().get(0) , creaEmpori(10, new Colore("Blu"))));
		giocatoreCorrente=giocatori.get(0);
	}
	private ArrayList<Emporio> creaEmpori(int numeroEmpori, Colore coloreGiocatore) {
		ArrayList<Emporio> empori=new ArrayList<>();
		for(int i=0; i<numeroEmpori; i++){
			Emporio emporio=new Emporio(coloreGiocatore);
			empori.add(emporio);
		}
		return empori;
	}
	private ArrayList<CartaPolitica> assegnaCartePolitica(int numeroCarte) {
		ArrayList<CartaPolitica> carte=new ArrayList<>(); 
		for(int i=0; i<numeroCarte; i++){
			carte.add(mazzoCartePolitica.pescaCarte());
		}
		
		return carte;
	}
	private HashSet<Colore> creaColori(int numeroGiocatori) {
		HashSet<Colore> colori=new HashSet<>();
		/*for(int i=0; i<numeroGiocatori; i++ ){
			colori.add(new Colore("Giallo"));
		}*/
		colori.add(new Colore("Giallo"));
		colori.add(new Colore("Verde"));
		colori.add(new Colore("Blu"));
		return colori;
	}

	public void nextPlayer(){
		Giocatore fineTurno = giocatori.remove(0);
		giocatori.add(fineTurno);
		
	}
	
	public void cambiaGiocatore(){
		int indice = giocatori.indexOf(giocatoreCorrente);
		if (indice != giocatori.size()-1)
			giocatoreCorrente = giocatori.get(indice+1);
		else giocatoreCorrente =giocatori.get(0);
	}
	public void decrementaTurno() {
		this.numeroTurni=this.numeroTurni--;
		
	}
}
