package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.GameState;
import game.notify.AzioniNotify;
import utility.exception.AzioneNonEseguibile;

public class StatoBonus implements Stato {
	private Stato stato;
	private List<String> azioni;
	
	/**
	 * @param stato
	 */
	public StatoBonus(GameState gameState, Stato stato) {
		this.stato = stato;
		riempiAzioni();
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	
	}

	public void riempiAzioni(){
		this.azioni = new ArrayList<String>();
		azioni.add("Hai vinto un Bonus nel Percorso Nobiltà! \nDigita [B] per usare il Bonus:");
		
	}
	
	@Override
	public void transizioneBonus(GameState gameState) throws AzioneNonEseguibile{
		if(gameState.getGiocatoreCorrente().getBonusNobiltà().isEmpty()){
			stato.transizionePrincipale(gameState);
		}
		else gameState.setStato(new StatoBonus(gameState, stato));
	}


	@Override
	public List<String> getAzioni() {
				return azioni;
	}

}
