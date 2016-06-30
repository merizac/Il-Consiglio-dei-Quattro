package server.model.bonus;

import java.util.Arrays;

import server.model.azioni.Azione;
import server.model.azioni.azioniBonus.BonusTesseraAcquistataN;
import server.model.game.GameState;
import server.model.notify.MessageNotify;

public class BonusTesseraAcquistata extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5973987175289643213L;
	private int id= 1;

	public BonusTesseraAcquistata() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the corresponding action
	 */
	public Azione getAzioneBonus(){
		BonusTesseraAcquistataN bt = new BonusTesseraAcquistataN();
		bt.setId(id);
		return bt;
	}

	/**
	 * add the corresponding action to the list of bonus in current player
	 */
	@Override
	public void usaBonus(GameState gameState) {		
		if (gameState.getGiocatoreCorrente().getTesserePermesso().isEmpty()
				&& gameState.getGiocatoreCorrente().getTesserePermessoUsate().isEmpty())
		{
			gameState.notifyObserver(new MessageNotify("Hai vinto un bonus tessera ma non hai tessere permesso! \nNon puoi utilizzare il bonus!\n", Arrays.asList(gameState.getGiocatoreCorrente())));
		}
		gameState.getGiocatoreCorrente().getBonusNobiltà().add(this.getAzioneBonus());
	}
	
}
