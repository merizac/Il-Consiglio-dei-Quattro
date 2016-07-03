package common.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import server.model.bonus.Bonus;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;

public class RegioneDTO implements Serializable {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 8912658420067177656L;
	/**
	 * name of region
	 */
	private String nome;
	/**
	 * discovery permit tile in each region
	 */
	private List<TesseraPermessoDTO> tesserePermessoScoperte;
	/**
	 * bonus of region
	 */
	private Bonus bonusRegione;
	/**
	 * balcony of region
	 */
	private BalconeDTO balcone;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the tesserePermessoScoperte
	 */
	public List<TesseraPermessoDTO> getTesserePermessoScoperte() {
		return tesserePermessoScoperte;
	}

	/**
	 * @param tesserePermessoScoperte
	 *            the tesserePermessoScoperte to set
	 */
	public void setTesserePermessoScoperte(List<TesseraPermessoDTO> tesserePermessoScoperte) {
		this.tesserePermessoScoperte = tesserePermessoScoperte;
	}

	/**
	 * @return the bonusRegione
	 */
	public Bonus getBonusRegione() {
		return bonusRegione;
	}

	/**
	 * @param bonusRegione
	 *            the bonusRegione to set
	 */
	public void setBonusRegione(Bonus bonusRegione) {
		this.bonusRegione = bonusRegione;
	}

	/**
	 * @return the balcone
	 */

	public BalconeDTO getBalcone() {
		return balcone;
	}

	/**
	 * @param balcone
	 *            the balcone to set
	 */

	public void setBalcone(BalconeDTO balcone) {
		this.balcone = balcone;
	}

	/**
	 * @param regone
	 *            the regione to inizialize in DTO mode
	 */
	public void inizializza(Regione regione) {
		this.nome = regione.getNome();
		this.balcone = new BalconeDTO();
		this.balcone.inizializza(regione.getBalcone());
		this.tesserePermessoScoperte = new ArrayList<>();
		for (TesseraPermesso t : regione.getTesserePermessoScoperte()) {
			TesseraPermessoDTO tp = new TesseraPermessoDTO();
			tp.inizializza(t);
			this.tesserePermessoScoperte.add(tp);
		}
		if (!regione.isBonusAssegnato()) {
			this.setBonusRegione(regione.getBonusRegione());
		}
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return nome;
	}

}
