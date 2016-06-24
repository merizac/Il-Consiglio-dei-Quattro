package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.CambioTesserePermessoDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.RegioneDTO;

public class CambioTesseraPermessoParametri implements SetterParametri {

	private CambioTesserePermessoDTO cambioTesserePermessoDTO;

	public CambioTesseraPermessoParametri(CambioTesserePermessoDTO cambioTesserePermessoDTO) {
		this.cambioTesserePermessoDTO=cambioTesserePermessoDTO;
	}

	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		grafica.mostraMessaggio("Seleziona la regione");
		RegioneDTO regioneScelta = grafica.scegliRegione(gameStateDTO.getRegioni());
		cambioTesserePermessoDTO.setRegione(regioneScelta);
	}

}
