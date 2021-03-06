package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.BonusTesseraAcquistataNDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.TesseraPermessoDTO;

public class BonusTesseraAcquistataParametri implements SetterParametri {

	private BonusTesseraAcquistataNDTO bonusTesseraAcquistataNDTO;

	/**
	 * action of BonusTesseraAcquistata
	 * 
	 * @param bonusTesseraAcquistataNDTO
	 */
	public BonusTesseraAcquistataParametri(BonusTesseraAcquistataNDTO bonusTesseraAcquistataNDTO) {
		this.bonusTesseraAcquistataNDTO = bonusTesseraAcquistataNDTO;
	}

	/**
	 * this method set parameters for the action BonusTesseraAcquistata in
	 * particular set permit tile of the player
	 */
	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		grafica.mostraMessaggio("Hai vinto un bonus tessera permesso!\nScegli una tessera permesso\n");

		TesseraPermessoDTO tesseraPermessoDTO = grafica.scegliTesseraPermessoUsataONonUsata(
				gameStateDTO.getGiocatoreDTO().getTesserePermesso(),
				gameStateDTO.getGiocatoreDTO().getTesserePermessoUsate());
		bonusTesseraAcquistataNDTO.setTesseraPermesso(tesseraPermessoDTO);
	}

}
