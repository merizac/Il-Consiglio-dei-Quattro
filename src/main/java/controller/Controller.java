package controller;

import utility.Observer;
import utility.exception.AzioneNonEseguibile;
import game.GameState;
import game.azioni.Azione;

public class Controller implements Observer<Azione> {

	private GameState gameState;

	
	
	public Controller(GameState gameState)
	{
		this.gameState=gameState;
	}
	

	@Override
	public void update(Azione azione) {
		try {
			azione.eseguiAzione(gameState);
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * @return the gameState
	 */
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * @param stato the stato to set
	 */


/*public void gestisciPartita() {
		
		int indiceGiocatore = 0;
		Giocatore giocatoreCorrente= gameState.getGiocatoreCorrente();
	
		while(true){
			
			giocatoreCorrente = this.gameState.getGiocatori().get(indiceGiocatore);
			giocatoreCorrente.aggiungiCartaPolitica(this.gameState.getMazzoCartePolitica().pescaCarte());
		
			
		
			//riempiAzioniPrincipali();
			//riempiAzioniVeloci();
			
			//mostra gioco e giocatore
			
			String messaggio=view.scegliAzione();
			mostraAzione(messaggio);
			
			if (this.getGiocatoreCorrente().getAzioniPrincipali().isEmpty()){
				String messaggio1 = view.possibilitàAzioneVeloce();
				if (messaggio1.equals("Y")){
					view.scegliAzioneVeloce();
					//controllo ritorno
				}
			}
			else {
				view.scegliAzionePrincipale();
				//controllo ritorno
			}
			if (indiceGiocatore==(giocatori.size()-1)){
				indiceGiocatore=0;
			}
			else indiceGiocatore ++;	
		}
		//condizione fine partita
		
	}

	public void riempiAzioniPrincipali() {
		this.gameState.getGiocatoreCorrente().getAzioniPrincipali().add(new ElezioneConsigliere(this.gameState));
		this.gameState.getGiocatoreCorrente().getAzioniPrincipali().add(new CostruzioneAiutoRe(this));
		this.gameState.getGiocatoreCorrente().getAzioniPrincipali().add(new CostruzioneTesseraPermesso(this));	
		this.gameState.getGiocatoreCorrente().getAzioniPrincipali().add(new AcquistoTesseraPermesso(this));	
	}
	
	private void riempiAzioniVeloci(){
		this.gameState.getGiocatoreCorrente().getAzioneVeloce().add(new IngaggioAiutante(this));
		this.gameState.getGiocatoreCorrente().getAzioneVeloce().add(new CambioTesseraPermesso(this));
		this.gameState.getGiocatoreCorrente().getAzioneVeloce().add(new ElezioneConsigliereVeloce(this.gameState,));
		this.gameState.getGiocatoreCorrente().getAzioneVeloce().add(new SecondaAzionePrincipale(this.gameState));
	}
	
	public void svuotaAzioniVeloci(){
		for (int i = 0; i < this.gameState.getGiocatoreCorrente().getAzioneVeloce().size(); i++){
		this.gameState.getGiocatoreCorrente().getAzioneVeloce().remove(i);
		}
	}
	
	public void svuotaAzioniPrincipali(){
		for (int i = 0; i < this.gameState.getGiocatoreCorrente().getAzioniPrincipali().size(); i++){
		this.gameState.getGiocatoreCorrente().getAzioniPrincipali().remove(i);
		}
	}
	
	private void mostraAzione(String messaggio) {
		switch(messaggio){
			case "1":
				view.scegliAzionePrincipale();
				//controllo
				svuotaAzioniPrincipali();
				break;
			case "2":
				view.scegliAzioneVeloce();
				//controllo
				svuotaAzioniVeloci();
				break;
		}
		
	}

}*/
}
