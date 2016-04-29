package game;

import java.util.ArrayList;

import bonus.Bonus;

public class Regione {

	private final String nome;
	private final Mazzo<TesseraPermesso> mazzoTesserePermesso;
	private final ArrayList<TesseraPermesso> tesserePermessoScoperte;
	private final Bonus bonusRegione;
	private final Balcone balcone;
	/**
	 * @param nome
	 * @param mazzoTesserePermesso
	 * @param tesserePermessoScoperte
	 * @param bonusRegione
	 * @param balcone
	 */
	public Regione(String nome, Mazzo<TesseraPermesso> mazzoTesserePermesso, ArrayList<TesseraPermesso> tesserePermessoScoperte,
			Bonus bonusRegione, Balcone balcone) {
		this.nome = nome;
		this.mazzoTesserePermesso = mazzoTesserePermesso;
		this.tesserePermessoScoperte = tesserePermessoScoperte;
		this.bonusRegione = bonusRegione;
		this.balcone = balcone;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @return the mazzoTesserePermesso
	 */
	public Mazzo<TesseraPermesso> getMazzoTesserePermesso() {
		return mazzoTesserePermesso;
	}
	/**
	 * @return the tesserePermessoScoperte
	 */
	public ArrayList<TesseraPermesso> getTesserePermessoScoperte() {
		return tesserePermessoScoperte;
	}
	/**
	 * @return the bonusRegione
	 */
	public Bonus getBonusRegione() {
		return bonusRegione;
	}
	/**
	 * @return the balcone
	 */
	public Balcone getBalcone() {
		return balcone;
	}
	
}
