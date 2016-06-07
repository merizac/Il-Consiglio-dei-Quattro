package game.azioni;

import bonus.Bonus;
import game.GameState;
import game.TesseraPermesso;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.BonusTesseraAcquistataNDTO;
import utility.exception.AzioneNonEseguibile;

public class BonusTesseraAcquistataN extends Azione{
/**
 * ottieni i bonus di una tessera permesso che hai comprato in precedenza
 * anche una di quelle a faccia in giù
 */
	
	private int indiceTesseraPermesso;
	private boolean usata;
	
	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		TesseraPermesso tesseraScelta;
		
		if(usata){
			tesseraScelta = gameState.getGiocatoreCorrente().getTesserePermessoUsate().get(indiceTesseraPermesso);	
		}
		else 
			tesseraScelta = gameState.getGiocatoreCorrente().getTesserePermesso().get(indiceTesseraPermesso);
	
		for(Bonus b: tesseraScelta.getBonus()){
			b.usaBonus(gameState);
		}
	}

	/**
	 * @return the indiceTesseraPermesso
	 */
	public int getIndiceTesseraPermesso() {
		return indiceTesseraPermesso;
	}

	/**
	 * @param indiceTesseraPermesso the indiceTesseraPermesso to set
	 */
	public void setIndiceTesseraPermesso(int indiceTesseraPermesso) {
		this.indiceTesseraPermesso = indiceTesseraPermesso;
	}

	/**
	 * @return the usata
	 */
	public boolean isUsata() {
		return usata;
	}

	/**
	 * @param usata the usata to set
	 */
	public void setUsata(boolean usata) {
		this.usata = usata;
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new BonusTesseraAcquistataNDTO();
	}

}
