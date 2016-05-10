package azioni;

import java.util.HashSet;

import bonus.Bonus;
import game.Città;
import game.CittàBonus;
import game.Colore;
import game.Emporio;
import game.Partita;
import game.TesseraPermesso;

public class CostruzioneTesseraPermesso extends AzionePrincipale {

	private final TesseraPermesso tesseraPermessoScoperta;
	private final Città cittàCostruzione;

	/**
	 * @param partita
	 * @param tesseraPermessoScoperta
	 * @param cittàCostruzione
	 */
	public CostruzioneTesseraPermesso(Partita partita, Città cittàCostruzione, TesseraPermesso tesseraPermessoScoperta) {
		super(partita);
		this.cittàCostruzione=cittàCostruzione;
		this.tesseraPermessoScoperta= tesseraPermessoScoperta;
	}


	/**
	 * execute the action
	 */
	@Override
	public boolean eseguiAzione() {
		/*PassaggioParametri passaggioParametri= new PassaggioParametri(partita);
		cittàCostruzione=passaggioParametri.selezionaCittà();
		tesseraPermessoScoperta=passaggioParametri.selezionaTesseraPermesso();*/
		
		if(!pagoAiutanti())
			return false;
		costruisci();
		prendiBonus();
		copriTessera();
		
		return true;
	}
	/**
	 * move the permit tile used from tesserePermesso to tesserePermessoUsate
	 */
	private void copriTessera() {
		this.partita.getGiocatoreCorrente().getTesserePermesso().remove(tesseraPermessoScoperta);
		this.partita.getGiocatoreCorrente().getTesserePermessoUsate().add(tesseraPermessoScoperta);
	}


	/**
	 * check if the player has enough aiutanti and then it subtract them from the player
	 * @return true if the player has enough aiutanti, false in the other case
	 */
	private boolean pagoAiutanti(){
		int numeroEmpori = cittàCostruzione.getEmpori().size(); 
	
		if(!cittàCostruzione.getEmpori().isEmpty()) { 
			if(this.partita.getGiocatoreCorrente().getAiutanti().togliAiutanti(numeroEmpori)){
				return true;
			}
		}
		return false;
	}
	/**
	 * build an emporio to the city selected
	 */
	private void costruisci(){
		Emporio emporio = this.partita.getGiocatoreCorrente().getEmpori().remove(0);
		this.cittàCostruzione.aggiungiEmporio(emporio);
	}
	/**
	 * give to the player the bonus of the city connected to the city where the player has built
	 */
	private void prendiBonus(){
		Colore coloreEmporio = this.partita.getGiocatoreCorrente().getColoreGiocatore();
		HashSet<CittàBonus> cittàCollegate = this.partita.getTabellone().getMappa().trovaCittà(cittàCostruzione, coloreEmporio);
		for ( CittàBonus c: cittàCollegate){
				for(Bonus b: c.getBonus()){
					b.usaBonus(partita);
		}
		}
	}
	
	
}
