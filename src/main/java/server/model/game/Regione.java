package server.model.game;

import java.util.ArrayList;
import java.util.List;

import server.model.bonus.Bonus;

public class Regione  {

	private final String nome;
	private final Mazzo<TesseraPermesso> mazzoTesserePermesso;
	private final List<TesseraPermesso> tesserePermessoScoperte;
	private final Bonus bonusRegione;
	private boolean bonusAssegnato=false;
	private final Balcone balcone;
	private List<Città> cittàRegione;
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
	public List<TesseraPermesso> getTesserePermessoScoperte() {
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
	public List<Città> getCittàRegione() {
		return cittàRegione;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	/**
	 * @return the bonusAssegnato
	 */
	public boolean isBonusAssegnato() {
		return bonusAssegnato;
	}

	/**
	 * @param bonusAssegnato the bonusAssegnato to set
	 */
	public void setBonusAssegnato(boolean bonusAssegnato) {
		this.bonusAssegnato = bonusAssegnato;
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Regione " + nome + "\n Tessere Permesso [ " + tesserePermessoScoperte + " ]\nBonus Regione = "
				+ bonusRegione + "\nBalcone = [ " + balcone + " ]";
	}
	
	
	
}
