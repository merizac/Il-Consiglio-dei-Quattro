package server.model.macchinaStati;

import java.util.Arrays;
import java.util.List;
import server.model.azioni.Azione;
import server.model.azioni.azioniBonus.PassaBonus;
import server.model.game.GameState;
import server.model.notify.BonusNotify;

public class StatoBonus implements Stato {
	private Stato stato;
	private List<Azione> azioni;
	
	/**
	 * @param stato
	 */
	public StatoBonus(GameState gameState, Stato stato) {
		System.out.println("[SERVER] "+this);
		if (stato==null)
			throw new NullPointerException("Lo stato deve essere lo stato precedente");
		this.stato = stato;
		this.azioni= gameState.getGiocatoreCorrente().getBonusNobiltà();
		this.azioni.add(new PassaBonus());
		System.out.println(this.getAzioni().toString());
		gameState.notifyObserver(new BonusNotify(gameState.getGiocatoreCorrente().getBonusNobiltà(), Arrays.asList(gameState.getGiocatoreCorrente())));

	}
	
	@Override
	public void transizioneBonus(GameState gameState){
		if(gameState.getGiocatoreCorrente().getBonusNobiltà().isEmpty()){
			stato.transizionePrincipale(gameState);
		}
		else gameState.setStato(new StatoBonus(gameState, stato));
	}

	@Override
	public void transizionePassa(GameState gameState) {
		this.transizioneBonus(gameState);
	}

	@Override
	public List<Azione> getAzioni() {
			return azioni;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stato Bonus";
	}

}
