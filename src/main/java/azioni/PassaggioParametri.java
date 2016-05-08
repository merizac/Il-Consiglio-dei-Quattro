package azioni;

import java.util.ArrayList;

import game.CartaPolitica;
import game.Città;
import game.Emporio;
import game.Partita;
import game.Regione;
import game.TesseraPermesso;

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
		String tessera=partita.getView().scegliTesseraScoperta();
		int tesseraPermessoScelta=0;
		if(!isNumeric(tessera))
			tesseraPermessoScelta=selezionaTesseraScoperta(regione);
		else if(tesseraPermessoScelta<0 || tesseraPermessoScelta>regione.getTesserePermessoScoperte().size())
			tesseraPermessoScelta=selezionaTesseraScoperta(regione); 
		else
			tesseraPermessoScelta=Integer.parseInt(tessera);
		return tesseraPermessoScelta;
	}
	
	protected Regione selezionaRegione() {
		String regione=partita.getView().scegliRegione();
		Regione regioneScelta=null;
		if(regione.equals("mare"))
			regioneScelta=partita.getTabellone().getRegioni().get(0);
		else if(regione.equals("collina"))
			regioneScelta=partita.getTabellone().getRegioni().get(1);
		else if (regione.equals("montagna"))
			regioneScelta=partita.getTabellone().getRegioni().get(2);
		else
			regioneScelta=selezionaRegione();
		return regioneScelta;
		
	}
	
	protected ArrayList<CartaPolitica> selezionaCarteGiocatore () {

		ArrayList<String> carteView =partita.getView().scegliCarte();
		ArrayList<CartaPolitica> cartePolitica = new ArrayList<CartaPolitica>();
		for(String carta: carteView){
			int indice = Integer.parseInt(carta);
			while(indice<1 || indice>partita.getGiocatoreCorrente().getCartePolitica().size())
			{
				//indice=partita.getView().erroreArrayList(carta);
			}
			cartePolitica.add(partita.getGiocatoreCorrente().getCartePolitica().get(indice-1));
		}
		
		return cartePolitica;
	}

	protected Città selezionaCittà() {
		String cittàScelta=partita.getView().scegliCittà();
		Città città=partita.getTabellone().getMappa().getCittà(cittàScelta);
		if(città==null)
			città=this.selezionaCittà();
		if(città.aggiungiEmporio(new Emporio(partita.getGiocatoreCorrente().getColoreGiocatore())))
			città=this.selezionaCittà();
		return città;
	}

	public TesseraPermesso selezionaTesseraPermesso() {
		String tessera= this.partita.getView().scegliTesseraPermesso();
		int indice=0;
		TesseraPermesso tesseraPermesso=partita.getGiocatoreCorrente().getTesserePermesso().get(indice);
		if(!isNumeric(tessera))
			tesseraPermesso=selezionaTesseraPermesso();
		if(indice<1 || indice>partita.getGiocatoreCorrente().getTesserePermesso().size())
			tesseraPermesso=selezionaTesseraPermesso();
		return tesseraPermesso;
	}

	public boolean isNumeric(String stringa){
		try{
			Integer.parseInt(stringa);
			return true;
		}
		catch(NumberFormatException e ){
			return false;
		}
	}
		


	
	

}
