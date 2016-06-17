package common.azioniDTO.azioniSetParametri;

import java.util.ArrayList;
import java.util.List;

import client.grafica.Grafica;
import common.azioniDTO.CostruzioneAiutoReDTO;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.GameStateDTO;

public class CostruzioneAiutoReParametri implements SetterParametri {

	private CostruzioneAiutoReDTO costruzioneAiutoReDTO;

	public CostruzioneAiutoReParametri(CostruzioneAiutoReDTO costruzioneAiutoReDTO) {
		this.costruzioneAiutoReDTO=costruzioneAiutoReDTO;
	}

	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		List<CartaPoliticaDTO>cartePolitica = grafica
				.scegliCarte(new ArrayList<>(gameStateDTO.getGiocatoreDTO().getCartePolitica()));
		CittàDTO cittàScelta = grafica.scegliCittà(gameStateDTO.getCittà(),
				gameStateDTO.getGiocatoreDTO().getColoreGiocatore());

		costruzioneAiutoReDTO.setCarteGiocatore(cartePolitica);
		costruzioneAiutoReDTO.setCittà(cittàScelta);
	}

}
