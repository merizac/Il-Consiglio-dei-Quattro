package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;
import utility.AzioneNonEseguibile;

public interface SetterParametri {

	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) throws AzioneNonEseguibile;
}
