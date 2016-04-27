package game;

import java.util.ArrayList;

public class Tabellone {
	private final Map mappa;
	private final ArrayList<Regione> regioni;
	private final PlanciaRe planciaRe;
	private final Re pedinaRe;
	private final ArrayList<Consigliere> consiglieri;
	private final Mazzo<CartaPolitica> mazzoCartePolitica;
	/**
	 * @param mappa
	 * @param regioni
	 * @param planciaRe
	 * @param pedinaRe
	 * @param consiglieri
	 * @param mazzoCartePolitica
	 */
	public Tabellone(Map mappa, ArrayList<Regione> regioni, PlanciaRe planciaRe, Re pedinaRe,
			ArrayList<Consigliere> consiglieri, Mazzo<CartaPolitica> mazzoCartePolitica) {
		this.mappa = mappa;
		this.regioni = regioni;
		this.planciaRe = planciaRe;
		this.pedinaRe = pedinaRe;
		this.consiglieri = consiglieri;
		this.mazzoCartePolitica = mazzoCartePolitica;
	}
	/**
	 * @return the mappa
	 */
	public Map getMappa() {
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

	

}
