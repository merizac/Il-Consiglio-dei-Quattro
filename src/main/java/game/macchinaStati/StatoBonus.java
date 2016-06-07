package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bonus.BonusNobiltà;
import game.GameState;
import game.azioni.Azione;
import game.notify.AzioniNotify;
import game.notify.BonusNotify;
import utility.exception.AzioneNonEseguibile;

public class StatoBonus implements Stato {
	private Stato stato;
	private List<Azione> bonus;
	
	/**
	 * @param stato
	 */
	public StatoBonus(GameState gameState, Stato stato) {
		this.stato = stato;
		riempiAzioni(gameState);
		gameState.notifyObserver(new BonusNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	
	}

	public void riempiAzioni(GameState gameState){
		this.bonus = new ArrayList<Azione>();
		gameState.getGiocatoreCorrente().getBonusNobiltà();
		
	}
	
	@Override
	public void transizioneBonus(GameState gameState) throws AzioneNonEseguibile{
		if(gameState.getGiocatoreCorrente().getBonusNobiltà().isEmpty()){
			stato.transizionePrincipale(gameState);
		}
		else gameState.setStato(new StatoBonus(gameState, stato));
	}


	@Override
	public List<Azione> getAzioni() {
				return bonus;
	}

}
