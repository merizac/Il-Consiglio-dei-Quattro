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
		this.acquistoTesseraPermessoDTO=acquistoTesseraPermessoDTO;
	}

	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) throws AzioneNonEseguibile {
		
		if(gameStateDTO.getGiocatoreDTO().getCartePolitica().isEmpty())
			throw new AzioneNonEseguibile("Errore: non hai carte politica, seleziona un'altra azione");
		
		List<CartaPoliticaDTO> cartePolitica = grafica
				.scegliCarte(new ArrayList<>(gameStateDTO.getGiocatoreDTO().getCartePolitica()));
		RegioneDTO regioneScelta = grafica.scegliRegione(gameStateDTO.getRegioni());
		TesseraPermessoDTO tesseraScelta = grafica.scegliTesseraRegione(regioneScelta.getTesserePermessoScoperte());		
		acquistoTesseraPermessoDTO.setRegione(regioneScelta);
		acquistoTesseraPermessoDTO.setCarte(cartePolitica);
		acquistoTesseraPermessoDTO.setTesseraPermesso(tesseraScelta);
	}

}
