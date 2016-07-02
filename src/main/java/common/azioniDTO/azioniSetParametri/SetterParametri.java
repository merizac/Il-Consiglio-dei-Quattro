package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;
import utility.AzioneNonEseguibile;

public interface SetterParametri {

	/**
	 * set parameter that action needed
	 * 
	 * @param grafica
	 * @param gameStateDTO
	 * @throws AzioneNonEseguibile
	 */
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) throws AzioneNonEseguibile;
}
