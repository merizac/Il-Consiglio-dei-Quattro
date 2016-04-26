package Game;

import java.util.ArrayList;

import Bonus.*;

public class Giocatore {

	private Color coloreGiocatore;
	private ArrayList<Game.CartaPolitica> cartePolitica;
	private ArrayList<Game.TesseraPermesso> tesserePermesso;
	private ArrayList<Game.TesseraPermesso> tesserePermessoUsate;
	private ArrayList<Bonus> tessereBonus;
	private Aiutante aiutanti;
	private PunteggioVittoria punteggioVittoria;
	private PunteggioRicchezza punteggioRicchezza;
	private PunteggioNobiltà punteggioNobiltà;

	private void creaEmpori() {
		throw new UnsupportedOperationException();
	}

	public void pescaCartaPolitica(int MazzoCartaPolitica) {
		throw new UnsupportedOperationException();
	}

	public ArrayList<Game.CartaPolitica> getCartePolitica() {
		return this.cartePolitica;
	}

	public ArrayList<Game.TesseraPermesso> getTesserePermesso() {
		return this.tesserePermesso;
	}

	public ArrayList<Bonus> getTessereBonus() {
		return this.tessereBonus;
	}

	public PunteggioVittoria getPunteggioVittoria() {
		return this.punteggioVittoria;
	}

	public PunteggioRicchezza getPunteggioRIcchezza() {
		throw new UnsupportedOperationException();
	}

	public PunteggioNobiltà getPunteggioNobiltà() {
		return this.punteggioNobiltà;
	}

	public void scegliAzione() {
		throw new UnsupportedOperationException();
	}

	private void creaAzionePrincipale() {
		throw new UnsupportedOperationException();
	}

	private void creaAzioneVeloce() {
		throw new UnsupportedOperationException();
	}
}
