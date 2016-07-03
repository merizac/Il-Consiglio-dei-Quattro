package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.AzioneAcquistoDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;

public class AzioneAcquistoParametri implements SetterParametri {

	private AzioneAcquistoDTO azioneAcquistoDTO;

	/**
	 * action AzioneAcquisto
	 * 
	 * @param azioneAcquistoDTO
	 */
	public AzioneAcquistoParametri(AzioneAcquistoDTO azioneAcquistoDTO) {
		this.azioneAcquistoDTO = azioneAcquistoDTO;
	}

	/**
	 * this method set parameters for the action AzioneAcquisto of the market.
	 * In particular set the offer that the current player want to buy
	 */
	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		grafica.mostraMessaggioMarket("Seleziona l'oggetto che vuoi acquistare\n");
		int offertaDTO = grafica.scegliOfferta(gameStateDTO.getOfferte());
		GiocatoreDTO giocatoreDTO = gameStateDTO.getGiocatoreDTO();
		azioneAcquistoDTO.setOfferta(offertaDTO);
		azioneAcquistoDTO.setGiocatoreDTO(giocatoreDTO);
	}

}
