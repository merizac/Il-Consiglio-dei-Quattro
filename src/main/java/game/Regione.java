package game;

import java.util.ArrayList;

import bonus.Bonus;

public class Regione {

	private final String nome;
	private final Mazzo<TesseraPermesso> mazzoTesserePermesso;
	private final ArrayList<TesseraPermesso> tesserePermessoScoperte;
	private final Bonus bonusRegione;
	private final Balcone balcone;
	private ArrayList<Città> cittàRegione;
	/**
	 * @param nome
	 * @param mazzoTesserePermesso
	 * @param tesserePermessoScoperte
	 * @param bonusRegione
	 * @param balcone
	 */
	public Regione(String nome, Mazzo<TesseraPermesso> mazzoTesserePermesso, Bonus bonusRegione, Balcone balcone) {
		this.nome = nome;
		this.mazzoTesserePermesso = mazzoTesserePermesso;
		this.tesserePermessoScoperte = new ArrayList<>();
		this.bonusRegione = bonusRegione;
		this.balcone = balcone;
		this.cittàRegione = new ArrayList<Città>();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Regione [nome=" + nome + "]";
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
	/**
	 * @return the cittàRegione
	 */
	public ArrayList<Città> getCittàRegione() {
		return cittàRegione;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Regione other = (Regione) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
	
}
