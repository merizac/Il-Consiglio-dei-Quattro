package game;

import java.io.Serializable;
import java.util.HashSet;

public class Città implements Serializable {


	private static final long serialVersionUID = -7129037061772846218L;
	private final String nome;
	private final Regione regione;
	protected final Colore colore;
	private final transient HashSet<Emporio> empori;
	private final transient HashSet<Città> cittàCollegate;
	
	

	/**
	 * @param nome
	 * @param regione
	 * @param colore
	 * @param empori
	 * @param cittàCollegate
	 */
	public Città(String nome, Regione regione, Colore colore) {
		super();
		this.nome = nome;
		this.regione = regione;
		this.colore = colore;
		this.empori = new HashSet<Emporio>();
		this.cittàCollegate = new HashSet<Città>();
		this.regione.getCittàRegione().add(this);
	}
	

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * @return the regione
	 */
	public Regione getRegione() {
		return regione;
	}


	/**
	 * @return the colore
	 */
	public Colore getColoreCittà() {
		return colore;
	}


	/**
	 * @return the empori
	 */
	public HashSet<Emporio> getEmpori() {
		return empori;
	}


	/**
	 * @return the cittàCollegate
	 */
	public HashSet<Città> getCittàCollegate() {
		return cittàCollegate;
	}
	
	/**
	 * check if it is possible to add the emporium , and if it is possible add the emporium
	 * @param emporio
	 * @return true if it is possible, false in the other case
	 */
	public boolean aggiungiEmporio(Emporio emporio){
		if(empori.add(emporio))
			return true;
		else 
			return false;
	}
	/**
	 * check if there is a emporio of the same color of parameter colore
	 * @param colore
	 * @return true if empori contains a emporio of the same colore, false in the other case
	 */
	public boolean emporioColore(Colore colore){
		if(empori.contains(new Emporio(colore)))
			return true;
		else 
			return false;
			
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((regione == null) ? 0 : regione.hashCode());
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
		Città other = (Città) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (regione == null) {
			if (other.regione != null)
				return false;
		} else if (!regione.equals(other.regione))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Città [nome=" + nome + ", colore=" + colore + "]";
	}


	
	
	
	
	
}
