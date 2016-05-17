package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import controller.Stato;
import controller.Stato11;
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
	/**
	 * @param mappa
	 * @param regioni
	 * @param planciaRe
	 * @param pedinaRe
	 * @param consiglieri
	 * @param mazzoCartePolitica
	 */
	public GameState(Mappa mappa, ArrayList<Regione> regioni, PlanciaRe planciaRe, Re pedinaRe,
			ArrayList<Consigliere> consiglieri, Mazzo<CartaPolitica> mazzoCartePolitica) {
		this.mappa = mappa;
		this.regioni = regioni;
		this.planciaRe = planciaRe;
		this.pedinaRe = pedinaRe;
		this.consiglieri = consiglieri;
		this.mazzoCartePolitica = mazzoCartePolitica;
		this.giocatori = new ArrayList<Giocatore>();
		this.stato= new Stato11();
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

}
