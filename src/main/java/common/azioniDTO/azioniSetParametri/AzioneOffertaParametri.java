package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.AzioneOffertaDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.MarketableDTO;

public class AzioneOffertaParametri implements SetterParametri {

	private AzioneOffertaDTO azioneOffertaDTO;

	public AzioneOffertaParametri(AzioneOffertaDTO azioneOffertaDTO) {
		this.azioneOffertaDTO = azioneOffertaDTO;
	}

	/**
	 * this method set parameters for the action AzioneOfferta of the market in
	 * particular set the offer with object that player want to send and the
	 * price.
	 */
	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		grafica.mostraMessaggio("Seleziona un oggetto che vuoi vendere\n");
		MarketableDTO marketableDTO = grafica.scegliMarketable();
		grafica.mostraMessaggio("Inserisci il prezzo\n");
		int prezzo = grafica.scegliPrezzo();

		azioneOffertaDTO.setMarketableDTO(marketableDTO);
		azioneOffertaDTO.setPrezzo(prezzo);

	}

}
