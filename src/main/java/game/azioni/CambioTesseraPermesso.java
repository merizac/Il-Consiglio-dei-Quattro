package game.azioni;

import game.GameState;
import game.Regione;
import game.TesseraPermesso;
import game.notify.ErrorNotify;
import game.notify.GiocatoreDTONotify;

public class CambioTesseraPermesso extends AzioneVeloce {


	private static final long serialVersionUID = 1446954873428331443L;
	private Regione regione;



	@Override
	public void eseguiAzione(GameState gameState) {
		if(gameState.getGiocatoreCorrente().getAiutanti().getAiutante()>0){
			gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(1);
			RimozioneCarte();
			SostituzioneCarte();
			setStatoTransizioneVeloce(gameState); 
			gameState.notifyObserver(new GiocatoreDTONotify(gameState.getGiocatoreCorrente()));
		}
		else{
			gameState.notifyObserver(new ErrorNotify("Errore: non hai abbastanza aiutanti"));
			return;
		}
		
		
	}
	
	/**
	 * remove cards from TesserePermesseScoperte and add to MazzoTesserePermesso
	 */
	private void RimozioneCarte(){
		
		int size=regione.getTesserePermessoScoperte().size();
		for(int i=0; i<size; i++){
			TesseraPermesso permesso=regione.getTesserePermessoScoperte().remove(0);
			regione.getMazzoTesserePermesso().aggiungiCarta(permesso);
		}
	}
	
	/**
	 * remove cards from MazzoTesserePermesso and add cards to TesserePermessoScoperte
	 */
	private void SostituzioneCarte(){
	
		for(int i=0; i<2; i++){
			TesseraPermesso tessera=regione.getMazzoTesserePermesso().pescaCarte();
			regione.getTesserePermessoScoperte().add(tessera);
		}
	
		

		
	}

	/**
	 * @param regione the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}
	
}
