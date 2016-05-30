package game.macchinaStati;

import java.util.ArrayList;
import java.util.List;
import game.GameState;
import game.notify.AzioniNotify;
import utility.exception.AzioneNonEseguibile;

public class Stato01 implements Stato {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1159916777727661681L;
	private List<String> azioni;

	public Stato01(GameState gameState) {
		System.out.println(this);
		riempiAzioni();
		gameState.notifyObserver(new AzioniNotify(this.getAzioni()));
		
	}

	private void riempiAzioni() {
		this.azioni = new ArrayList<String>();
		azioni.add("AZIONI VELOCI");
		azioni.add("Ingaggiare un aiutante");
		azioni.add("Cambiare le tessere permesso di costruzione");
		azioni.add("Mandare un aiutante ad eleggere un consigliere");
		azioni.add("Compiere un'azione principale");
	}

	@Override
	public void transizioneVeloce(GameState gameState) throws AzioneNonEseguibile {
		gameState.nextPlayer();
		gameState.setStato(new StartEnd(gameState));
	}

	@Override
	public void transizioneSecondaPrincipale(GameState gameState) {
		gameState.setStato(new Stato10(gameState));
		
	}
	

	@Override
	public List<String> getAzioni() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stato01";
	}


	
	

	
}
