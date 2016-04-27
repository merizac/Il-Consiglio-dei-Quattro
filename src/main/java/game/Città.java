package game;

public class Città {

	private String nome;
	private Regione regione;
	private Color colore;
	private Emporio[] empori;
	private Città[] cittàCollegate;

	public String getNome() {
		throw new UnsupportedOperationException();
	}

	public Color getColore() {
		return this.colore;
	}

	public Regione getRegione() {
		return this.regione;
	}

	public Emporio[] getEmpori() {
		return this.empori;
	}

	public void costruisciEmporio(Emporio emporio) {
		throw new UnsupportedOperationException();
	}
}
