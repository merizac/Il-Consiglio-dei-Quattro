package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.CambioTesserePermessoDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.RegioneDTO;

public class CambioTesseraPermessoParametri implements SetterParametri {

	private CambioTesserePermessoDTO cambioTesserePermessoDTO;

	/**
	 * ACTION OF CAMBIOTESSERAPERMESSO
	 * 
	 * @param cambioTesserePermessoDTO
	 */
	public CambioTesseraPermessoParametri(CambioTesserePermessoDTO cambioTesserePermessoDTO) {
		this.cambioTesserePermessoDTO = cambioTesserePermessoDTO;
	}

	/**
	 * this method set parameters for the action CambioTesserePermesso in
	 * particular set the region
	 */
	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		grafica.mostraMessaggio("Seleziona la regione\n");
		RegioneDTO regioneScelta = grafica.scegliRegione(gameStateDTO.getRegioni());
		cambioTesserePermessoDTO.setRegione(regioneScelta);
	}

}
