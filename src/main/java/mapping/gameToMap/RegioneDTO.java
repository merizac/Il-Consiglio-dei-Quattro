package mapping.gameToMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ArrayList;

public class RegioneDTO implements Serializable {
	
	private static final long serialVersionUID = 8912658420067177656L;
	private String nome;
	private ArrayList<TesseraPermessoDTO> tesserePermessoScoperte;
	private BonusDTO bonusRegione;
	private ArrayList<Colore> balcone;
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the tesserePermessoScoperte
	 */
	public ArrayList<TesseraPermessoDTO> getTesserePermessoScoperte() {
		return tesserePermessoScoperte;
	}
	/**
	 * @param tesserePermessoScoperte the tesserePermessoScoperte to set
	 */
	public void setTesserePermessoScoperte(ArrayList<TesseraPermessoDTO> tesserePermessoScoperte) {
		this.tesserePermessoScoperte = tesserePermessoScoperte;
	}
	/**
	 * @return the bonusRegione
	 */
	public BonusDTO getBonusRegione() {
		return bonusRegione;
	}
	/**
	 * @param bonusRegione the bonusRegione to set
	 */
	public void setBonusRegione(BonusDTO bonusRegione) {
		this.bonusRegione = bonusRegione;
	}
	/**
	 * @return the balcone
	 */
	public ArrayList<Colore> getBalcone() {
		return balcone;
	}
	/**
	 * @param balcone the balcone to set
	 */
	public void setBalcone(ArrayList<Colore> balcone) {
		this.balcone = balcone;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegioneDTO [nome=" + nome + ", tesserePermessoScoperte=" + tesserePermessoScoperte + ", bonusRegione="
				+ bonusRegione + ", balcone=" + balcone + "]";
	}
	
	

}
