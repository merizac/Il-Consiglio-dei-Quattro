package common.azioniDTO.azioniSetParametri;

import java.util.ArrayList;
import java.util.List;

import client.grafica.Grafica;
import common.azioniDTO.CostruzioneAiutoReDTO;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.GameStateDTO;
import utility.AzioneNonEseguibile;

public class CostruzioneAiutoReParametri implements SetterParametri {

	private CostruzioneAiutoReDTO costruzioneAiutoReDTO;

	public CostruzioneAiutoReParametri(CostruzioneAiutoReDTO costruzioneAiutoReDTO) {
		this.costruzioneAiutoReDTO=costruzioneAiutoReDTO;
	}

	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) throws AzioneNonEseguibile {
		if(gameStateDTO.getGiocatoreDTO().getCartePolitica().isEmpty())
			throw new AzioneNonEseguibile("Errore: non hai carte politica, seleziona un'altra azione");
		
		grafica.mostraMessaggio("Seleziona le carta politica dalla tua mano\n");
		List<CartaPoliticaDTO>cartePolitica = grafica
				.scegliCarte(new ArrayList<>(gameStateDTO.getGiocatoreDTO().getCartePolitica()));
		grafica.mostraMessaggio("Seleziona la città in cui vuoi costruire\n");
		CittàDTO cittàScelta = grafica.scegliCittà(gameStateDTO.getCittà(),
				gameStateDTO.getGiocatoreDTO().getColoreGiocatore());

		costruzioneAiutoReDTO.setCarteGiocatore(cartePolitica);
		costruzioneAiutoReDTO.setCittà(cittàScelta);
	}

}
