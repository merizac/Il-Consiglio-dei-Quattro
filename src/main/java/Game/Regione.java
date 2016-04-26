package Game;

import java.util.ArrayList;

import Bonus.Bonus;

public class Regione {

	private String nome;
	private Mazzo mazzoTesserePermesso;
	private ArrayList<TesseraPermesso> tesserePermessoScoperte;
	private Bonus bonusRegione;
	private Balcone balcone;

	private void creaMazzoTesserePermesso() {
		throw new UnsupportedOperationException();
	}

	private void creaBonusRegione() {
		throw new UnsupportedOperationException();
	}

	private void creaBalcone() {
		throw new UnsupportedOperationException();
	}

	private void creaCittà() {
		throw new UnsupportedOperationException();
	}

	public String getNomeRegione() {
		throw new UnsupportedOperationException();
	}

	public Mazzo getMazzoTesserePermesso() {
		return this.mazzoTesserePermesso;
	}

	public ArrayList<TesseraPermesso> getTesserePermessoScoperte() {
		return this.tesserePermessoScoperte;
	}

	public Bonus getBonusRegione() {
		return this.bonusRegione;
	}
}
