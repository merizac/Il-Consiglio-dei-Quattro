package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.AzioneOffertaDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.MarketableDTO;

public class AzioneOffertaParametri implements SetterParametri {

	private AzioneOffertaDTO azioneOffertaDTO;

	public AzioneOffertaParametri(AzioneOffertaDTO azioneOffertaDTO) {
		this.azioneOffertaDTO=azioneOffertaDTO;
	}

	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		MarketableDTO marketableDTO=grafica.scegliMarketable();
		int prezzo=grafica.scegliPrezzo();
		
		azioneOffertaDTO.setMarketableDTO(marketableDTO);
		azioneOffertaDTO.setPrezzo(prezzo);

	}

}
