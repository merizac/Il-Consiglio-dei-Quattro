package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.TesseraPermessoDTO;
import utility.AzioneNonEseguibile;

public class CostruzioneTesseraPermessoParametri implements SetterParametri {

	private CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO;

	public CostruzioneTesseraPermessoParametri(CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO) {
		this.costruzioneTesseraPermessoDTO=costruzioneTesseraPermessoDTO;
	}

	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) throws AzioneNonEseguibile {
		
		if(gameStateDTO.getGiocatoreDTO().getTesserePermesso().isEmpty())
			throw new AzioneNonEseguibile("Errore: non hai tessere permesso, seleziona un'altra azione");
		grafica.mostraMessaggio("Seleziona una tessera permesso");
		TesseraPermessoDTO tesseraScelta = grafica.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermesso());
		grafica.mostraMessaggio("Seleziona la città in cui vuoi costruire");
		CittàDTO cittàScelta = grafica.scegliCittà(tesseraScelta.getCittà(),
				gameStateDTO.getGiocatoreDTO().getColoreGiocatore());

		costruzioneTesseraPermessoDTO.setTesseraPermesso(tesseraScelta);
		costruzioneTesseraPermessoDTO.setCittà(cittàScelta);
	}

}
