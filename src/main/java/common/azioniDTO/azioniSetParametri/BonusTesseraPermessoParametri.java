package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.BonusTesseraPermessoNDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;

public class BonusTesseraPermessoParametri implements SetterParametri {

	private BonusTesseraPermessoNDTO bonusTesseraPermessoNDTO;

	public BonusTesseraPermessoParametri(BonusTesseraPermessoNDTO bonusTesseraPermessoNDTO) {
		this.bonusTesseraPermessoNDTO=bonusTesseraPermessoNDTO;
	}

	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		RegioneDTO regioneScelta = grafica.scegliRegione(gameStateDTO.getRegioni());
		TesseraPermessoDTO tesseraScelta = grafica.scegliTesseraRegione(regioneScelta.getTesserePermessoScoperte());
		bonusTesseraPermessoNDTO.setRegione(regioneScelta);
		bonusTesseraPermessoNDTO.setTesseraScoperta(tesseraScelta);
	}

}
