package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;

import server.model.azioni.Azione;
import server.model.azioni.azioniPrincipali.AcquistoTesseraPermesso;
import server.model.azioni.azioniPrincipali.CostruzioneAiutoRe;
import server.model.azioni.azioniPrincipali.CostruzioneTesseraPermesso;
import server.model.azioni.azioniPrincipali.ElezioneConsigliere;
import server.model.azioni.azioniVeloci.CambioTesseraPermesso;
import server.model.azioni.azioniVeloci.ElezioneConsigliereVeloce;
import server.model.azioni.azioniVeloci.IngaggioAiutante;
import server.model.azioni.azioniVeloci.SecondaAzionePrincipale;
import server.model.game.GameState;
import server.model.notify.AzioniNotify;
import server.model.notify.MessageNotify;

public class Stato11 implements Stato {

	private List<Azione> azioni;

	public Stato11(GameState gameState) {
		System.out.println("[SERVER] "+this);
		this.azioni=Arrays.asList(new ElezioneConsigliere(), new AcquistoTesseraPermesso(),
				new CostruzioneTesseraPermesso(), new CostruzioneAiutoRe(), new IngaggioAiutante(), new CambioTesseraPermesso(), 
				new ElezioneConsigliereVeloce(), new SecondaAzionePrincipale());
		gameState.notifyObserver(new MessageNotify("Scegli un'azione principale o un'azione veloce\n", Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}
	
	@Override
	public void transizionePrincipale(GameState gameState){
		if(!gameState.isBonusAzionePrincipale()){
			gameState.setStato(new Stato01(gameState));
		}
		else{
			gameState.setBonusAzionePrincipale(false);
			gameState.setStato(new Stato11(gameState));
		}
	}
	
	@Override
	public void transizioneBonus(GameState gameState){
		gameState.setStato(new StatoBonus(gameState,this));
	}
	
	@Override
	public void transizioneVeloce(GameState gameState){
		gameState.setStato(new Stato10(gameState));
	}
	
	@Override
	public List<Azione> getAzioni() {
		return this.azioni;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stato11";
	}

}
