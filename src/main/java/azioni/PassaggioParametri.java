package azioni;

import java.util.ArrayList;

import game.CartaPolitica;
import game.Consigliere;
import game.Partita;
import game.Regione;

public class PassaggioParametri {
	private Partita partita;

	/**
	 * constructor
	 */
	public PassaggioParametri(Partita partita) {
		super();
		this.partita=partita;
	}
	/**
	 * 
	 * @param regione
	 * @return index of the TesseraScoperta that the player want to catch
	 */
	protected int selezionaTesseraScoperta(Regione regione) {
		int indice=partita.getView().scegliTesseraScoperta(regione);
		return indice;
	}
	
	/**
	 * 
	 * @return the object Regione that the player want
	 */
	protected Regione selezionaRegione() {
		String regione=partita.getView().scegliRegione();
		if(regione.equals("mare"))
			return partita.getTabellone().getRegioni().get(0);
		else if(regione.equals("montagna"))
			return partita.getTabellone().getRegioni().get(1);
		else
			return partita.getTabellone().getRegioni().get(2);
		
	}
	
	/**
	 * 
	 * @return the object Consigliere that the player choose
	 */
	public Consigliere selezionaConsiglieri() {
		String consigliereScelto = partita.getView().scegliConsigliere();
		Consigliere consigliere = partita.getTabellone().getConsigliere(consigliereScelto);
		if(consigliere==null)
			consigliere=this.selezionaConsiglieri();
		return consigliere;
	}
	
	/**
	 * 
	 * @return ArrayList of CartaPolitica: cards that the player choose for match the Balcony
	 */
	protected ArrayList<CartaPolitica> selezionaCarteGiocatore () {

		ArrayList<String> carteView =partita.getView().scegliCarte();
		ArrayList<CartaPolitica> cartePolitica = new ArrayList<CartaPolitica>();
		for(String carta: carteView){
			int indice = Integer.parseInt(carta);
			while(indice<1 || indice>partita.getGiocatoreCorrente().getCartePolitica().size())
			{
				indice=partita.getView().erroreArrayList(carta);
			}
			cartePolitica.add(partita.getGiocatoreCorrente().getCartePolitica().get(indice-1));
		}
		
		return cartePolitica;
	}


	
	

}
