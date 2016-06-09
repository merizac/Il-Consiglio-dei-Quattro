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
	private final int ID=14;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusTesseraAcquistataN other = (BonusTesseraAcquistataN) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

}
