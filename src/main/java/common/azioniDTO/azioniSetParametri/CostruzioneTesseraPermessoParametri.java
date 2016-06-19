package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.TesseraPermessoDTO;

public class CostruzioneTesseraPermessoParametri implements SetterParametri {

	private CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO;

	public CostruzioneTesseraPermessoParametri(CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO) {
		this.costruzioneTesseraPermessoDTO=costruzioneTesseraPermessoDTO;
	}

	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		TesseraPermessoDTO tesseraScelta = grafica.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermesso());
		CittàDTO cittàScelta = grafica.scegliCittà(tesseraScelta.getCittà(),
				gameStateDTO.getGiocatoreDTO().getColoreGiocatore());

		costruzioneTesseraPermessoDTO.setTesseraPermesso(tesseraScelta);
		costruzioneTesseraPermessoDTO.setCittà(cittàScelta);
	}

}
