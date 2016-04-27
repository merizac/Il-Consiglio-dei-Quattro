package game;

import java.util.ArrayList;

import bonus.*;

public class Giocatore {

	private Color coloreGiocatore;
	private ArrayList<game.CartaPolitica> cartePolitica;
	private ArrayList<game.TesseraPermesso> tesserePermesso;
	private ArrayList<game.TesseraPermesso> tesserePermessoUsate;
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

	public ArrayList<game.CartaPolitica> getCartePolitica() {
		return this.cartePolitica;
	}

	public ArrayList<game.TesseraPermesso> getTesserePermesso() {
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
