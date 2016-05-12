package controller;


/*import game.Giocatore;
import game.azioni.AcquistoTesseraPermesso;
import game.azioni.CambioTesseraPermesso;
import game.azioni.CostruzioneAiutoRe;
import game.azioni.CostruzioneTesseraPermesso;
import game.azioni.ElezioneConsigliere;
import game.azioni.ElezioneConsigliereVeloce;
import game.azioni.IngaggioAiutante;
import game.azioni.SecondaAzionePrincipale;*/
import utility.Observer;
import game.GameState;
import game.azioni.Azione;
import view.View;

public class Partita implements Observer<Azione> {

	private GameState gameState;
	private Stato stato;
	private Azione azioneCorrente;
	private boolean isActionSelected;
	
	
	public Partita(GameState gameState, View view)
	{
		this.gameState=gameState;
		view.registerObserver(this);
		this.play();
		this.isActionSelected=false;
		this.stato = new StatoStartEnd();
	}
	
	private void play() {
		while (!this.isActionSelected);
		this.stato.handleAzione(this, this.azioneCorrente);
		this.isActionSelected=false;
		
	}


	@Override
	public void update(Azione azione) {
		this.azioneCorrente=azione;
		this.isActionSelected=true;
		
	}
	
	
	/**
	 * @return the tabellone
	 */
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(Stato stato) {
		this.stato = stato;
	}

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
