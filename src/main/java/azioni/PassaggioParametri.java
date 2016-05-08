package azioni;

import java.util.ArrayList;

import game.CartaPolitica;
import game.Consigliere;
import game.Partita;
import game.Regione;

public class PassaggioParametri {
	private Partita partita;

	/**
	 * 
	 */
	public PassaggioParametri(Partita partita) {
		super();
		this.partita=partita;
	}
	
	protected int selezionaTesseraScoperta(Regione regione) {
		int indice=partita.getView().scegliTesseraScoperta(regione);
		return indice;
	}
	
	protected Regione selezionaRegione() {
		String regione=partita.getView().scegliRegione();
		if(regione.equals("mare"))
			return partita.getTabellone().getRegioni().get(0);
		else if(regione.equals("montagna"))
			return partita.getTabellone().getRegioni().get(1);
		else
			return partita.getTabellone().getRegioni().get(2);
		
	}
	
	public Consigliere selezionaConsiglieri() {
		String consigliereScelto = partita.getView().scegliConsigliere();
		Consigliere consigliere = partita.getTabellone().getConsigliere(consigliereScelto);
		if(consigliere==null)
			consigliere=this.selezionaConsiglieri();
		return consigliere;
	}
	
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
