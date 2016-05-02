package game;

public class Partita {

	private Tabellone tabellone;
	private Giocatore[] giocatori;
	private Giocatore giocatoreCorrente;
	
	

	/*private void creaTabellone() {
		throw new UnsupportedOperationException();
	}

	private void creaGiocatori() {
		throw new UnsupportedOperationException();
	}*/

	/**
	 * @return the tabellone
	 */
	public Tabellone getTabellone() {
		return tabellone;
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

	public void gestisciPartita() {
		
	}
}
