package game;

public class PunteggioRicchezza {

	private int puntiRicchezza;
	

	/**
	 * @param puntiRicchezza
	 */
	public PunteggioRicchezza(int puntiRicchezza) {
		super();
		this.puntiRicchezza = puntiRicchezza;
	}

	public int getPuntiRicchezza() {
		return this.puntiRicchezza;
	}
	

	public void aggiungiPuntiRicchezza(int puntiRicchezza) {
		this.puntiRicchezza = this.puntiRicchezza + puntiRicchezza;
	}

	/**
	 * Decrease puntiRicchezza
	 * @param puntiRicchezza
	 * @return true if it is possible to decrease puntiRicchezza, otherwise return false
	 */
	public Boolean togliPuntiRicchezza(int puntiRicchezza) {
		if(this.puntiRicchezza < puntiRicchezza) return false;
		else{
			this.puntiRicchezza = this.puntiRicchezza - puntiRicchezza;
			return true;
		}
	}
}
