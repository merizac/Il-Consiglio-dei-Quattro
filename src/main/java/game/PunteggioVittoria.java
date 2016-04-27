package game;

public class PunteggioVittoria {

	private int puntiVittoria;
	
	//costruttore
	public PunteggioVittoria(int puntiVittoria) {
		this.puntiVittoria = puntiVittoria;
	}

	public int getPuntiVittoria() {
		return this.puntiVittoria;
	}
	/**
	 * add puntiVittoria (param) to the local variable puntiVittoria
	 * @param puntiVittoria
	 */
	public void aggiungiPuntiVittoria(int puntiVittoria) {
		this.puntiVittoria = this.puntiVittoria + puntiVittoria;
	}
	
}
