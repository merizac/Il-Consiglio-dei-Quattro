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
	
	private TesseraPermesso tesseraPermesso;
	
	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		TesseraPermesso tesseraScelta;
		
		/*if(usata){
			tesseraScelta = gameState.getGiocatoreCorrente().getTesserePermessoUsate().get(indiceTesseraPermesso);	
		}
		else 
			tesseraScelta = gameState.getGiocatoreCorrente().getTesserePermesso().get(indiceTesseraPermesso);*/
	
		for(Bonus b: tesseraPermesso.getBonus()){
			b.usaBonus(gameState);
		}
	}

	/**
	 * @return the indiceTesseraPermesso
	 */
	public TesseraPermesso getTesseraPermesso() {
		return tesseraPermesso;
	}

	/**
	 * @param indiceTesseraPermesso the indiceTesseraPermesso to set
	 */
	public void setTesseraPermesso(TesseraPermesso tesseraPermesso) {
		this.tesseraPermesso = tesseraPermesso;
	}


	@Override
	public AzioneDTO getAzioneDTO() {
		return new BonusTesseraAcquistataNDTO();
	}

}
