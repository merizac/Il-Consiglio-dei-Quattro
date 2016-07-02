package server.view.clientNotify;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;

public class GiocatoreClientNotify implements ClientNotify {

	private static final long serialVersionUID = -4806944248705839335L;
	private GiocatoreDTO giocatoreDTO;

	/**
	 * player notify
	 * 
	 * @param giocatoreDTO
	 */
	public GiocatoreClientNotify(GiocatoreDTO giocatoreDTO) {
		if (giocatoreDTO == null)
			throw new NullPointerException("Il giocatoreDTO non può essere null");
		this.giocatoreDTO = giocatoreDTO;
	}

	/**
	 * show player update
	 */
	@Override
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) {

		GiocatoreDTO giocatore = gameStateDTO.getGiocatoreDTO();
		giocatore.setTesserePermesso(this.giocatoreDTO.getTesserePermesso());
		giocatore.setTesserePermessoUsate(this.giocatoreDTO.getTesserePermessoUsate());
		giocatore.setAiutanti(this.giocatoreDTO.getAiutanti());
		giocatore.setCartePolitica(this.giocatoreDTO.getCartePolitica());
		giocatore.setColoreGiocatore(this.giocatoreDTO.getColoreGiocatore());
		giocatore.setEmpori(this.giocatoreDTO.getEmpori());
		giocatore.setPunteggioNobiltà(this.giocatoreDTO.getPunteggioNobiltà());
		giocatore.setPunteggioRicchezza(this.giocatoreDTO.getPunteggioRicchezza());
		giocatore.setPunteggioVittoria(this.giocatoreDTO.getPunteggioVittoria());
		giocatore.setTessereBonus(this.giocatoreDTO.getTessereBonus());

		grafica.mostraGiocatore(giocatoreDTO);
	}

}
