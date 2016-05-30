package game.macchinaStati;

import java.util.ArrayList;
import java.util.List;
import game.GameState;
import game.notify.AzioniNotify;
import utility.exception.AzioneNonEseguibile;

public class StartEnd implements Stato {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4274723843140699323L;

	public StartEnd(GameState gameState) throws AzioneNonEseguibile {
		if(gameState.getNumeroTurni()!=gameState.getGiocatori().size()){
			gameState.notifyObserver(new AzioniNotify(this.getAzioni()) );
			gameState.prossimoTurno();
		}
		else
			gameState.setStato(new StatoOffertaMarket(gameState));
	}

	@Override
	public void transizionePescaCarta(GameState gameState) {
		System.out.println("transizione");
		gameState.setStato(new Stato11(gameState));
		
	}

	@Override
	public List<String> getAzioni() {
		List<String> azioni=new ArrayList<>();
		azioni.add("Pesca[pc]");
		return azioni;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StartEnd";
	}

	

}
