package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.BonusTesseraPermessoNDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;

public class BonusTesseraPermessoParametri implements SetterParametri {

	private BonusTesseraPermessoNDTO bonusTesseraPermessoNDTO;

	public BonusTesseraPermessoParametri(BonusTesseraPermessoNDTO bonusTesseraPermessoNDTO) {
		this.bonusTesseraPermessoNDTO = bonusTesseraPermessoNDTO;
	}

	/**
	 * this method set parameters for the action BonusTesseraPermesso in
	 * particular set the region and the permit tile
	 */
	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		grafica.mostraMessaggio("Hai vinto un bonus tessera permesso!\nScegli una regione\n");
		RegioneDTO regioneScelta = grafica.scegliRegione(gameStateDTO.getRegioni());
		grafica.mostraMessaggio("Scegli una tessera permesso della regione " + regioneScelta.getNome() + "\n");
		TesseraPermessoDTO tesseraScelta = grafica.scegliTesseraRegione(regioneScelta.getTesserePermessoScoperte(), regioneScelta);
		bonusTesseraPermessoNDTO.setRegione(regioneScelta);
		bonusTesseraPermessoNDTO.setTesseraScoperta(tesseraScelta);
	}

}
