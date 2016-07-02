package common.azioniDTO.azioniSetParametri;

import java.util.ArrayList;
import java.util.List;

import client.grafica.Grafica;
import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import utility.AzioneNonEseguibile;

public class AcquistoTesseraPermessoParametri implements SetterParametri {

	private AcquistoTesseraPermessoDTO acquistoTesseraPermessoDTO;

	public AcquistoTesseraPermessoParametri(AcquistoTesseraPermessoDTO acquistoTesseraPermessoDTO) {
		this.acquistoTesseraPermessoDTO = acquistoTesseraPermessoDTO;
	}

	/**
	 * this method set parameters for the action AcquistoTesseraPermesso. in
	 * particular set politic cards, region and permit tile of the region
	 * selected
	 */
	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) throws AzioneNonEseguibile {

		if (gameStateDTO.getGiocatoreDTO().getCartePolitica().isEmpty())
			throw new AzioneNonEseguibile("Errore: non hai carte politica, seleziona un'altra azione");

		grafica.mostraMessaggio("Seleziona le carta politica dalla tua mano\n");
		List<CartaPoliticaDTO> cartePolitica = grafica
				.scegliCarte(new ArrayList<>(gameStateDTO.getGiocatoreDTO().getCartePolitica()));
		grafica.mostraMessaggio("Seleziona la regione\n");
		RegioneDTO regioneScelta = grafica.scegliRegione(gameStateDTO.getRegioni());
		grafica.mostraMessaggio("Seleziona la tessera permesso della regione " + regioneScelta.getNome() + "\n");
		TesseraPermessoDTO tesseraScelta = grafica.scegliTesseraRegione(regioneScelta.getTesserePermessoScoperte());
		acquistoTesseraPermessoDTO.setRegione(regioneScelta);
		acquistoTesseraPermessoDTO.setCarte(cartePolitica);
		acquistoTesseraPermessoDTO.setTesseraPermesso(tesseraScelta);
	}

}
