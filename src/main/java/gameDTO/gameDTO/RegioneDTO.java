package gameDTO.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;

import game.Consigliere;
import game.Regione;
import game.TesseraPermesso;

public class RegioneDTO implements Serializable {
	
	private static final long serialVersionUID = 8912658420067177656L;
	private String nome;
	private ArrayList<TesseraPermessoDTO> tesserePermessoScoperte;
	private BonusDTO bonusRegione;
	private ArrayList<ConsigliereDTO> balcone;
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
	public ArrayList<ConsigliereDTO> getBalcone() {
		return balcone;
	}
	/**
	 * @param balcone the balcone to set
	 */
	public void setBalcone(ArrayList<ConsigliereDTO> balcone) {
		this.balcone = balcone;
	}
	public void inizializza(Regione regione) {
		this.nome=regione.getNome();
		this.balcone=new ArrayList<>();
		for(Consigliere c: regione.getBalcone().getConsigliere()){
			ConsigliereDTO consigliereDTO=new ConsigliereDTO();
			consigliereDTO.inizializza(c);
			this.balcone.add(consigliereDTO);
		}
		this.tesserePermessoScoperte=new ArrayList<>();
		for(TesseraPermesso t: regione.getTesserePermessoScoperte()){
			TesseraPermessoDTO tp=new TesseraPermessoDTO();
			tp.inizializza(t);
			this.tesserePermessoScoperte.add(tp);
		}
		
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
