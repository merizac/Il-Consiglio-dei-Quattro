package game.macchinaStati;

import java.util.Arrays;
import java.util.List;
import game.GameState;
import game.azioni.Azione;
import game.notify.BonusNotify;
import utility.exception.AzioneNonEseguibile;

public class StatoBonus implements Stato {
	private Stato stato;
	private List<Azione> bonus;
	
	/**
	 * @param stato
	 */
	public StatoBonus(GameState gameState, Stato stato) {
		System.out.println("[SERVER] "+this);
		this.stato = stato;
		this.bonus=gameState.getGiocatoreCorrente().getBonusNobiltà();
		gameState.notifyObserver(new BonusNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StatoBonus";
	}

}
