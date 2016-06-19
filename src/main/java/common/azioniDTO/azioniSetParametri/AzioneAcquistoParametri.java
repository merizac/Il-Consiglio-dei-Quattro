package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.AzioneAcquistoDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;

public class AzioneAcquistoParametri implements SetterParametri {

	private AzioneAcquistoDTO azioneAcquistoDTO;

	public AzioneAcquistoParametri(AzioneAcquistoDTO azioneAcquistoDTO) {
		this.azioneAcquistoDTO=azioneAcquistoDTO;
	}

	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		int offertaDTO=grafica.scegliOfferta(gameStateDTO.getOfferte());
		GiocatoreDTO giocatoreDTO=gameStateDTO.getGiocatoreDTO();
		azioneAcquistoDTO.setOfferta(offertaDTO);
		azioneAcquistoDTO.setGiocatoreDTO(giocatoreDTO);
	}


}
