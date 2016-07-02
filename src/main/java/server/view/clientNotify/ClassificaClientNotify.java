package server.view.clientNotify;

import java.util.ArrayList;
import java.util.List;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;
import server.model.game.Giocatore;

public class ClassificaClientNotify implements ClientNotify {

	private static final long serialVersionUID = -139988226344509096L;
	private List<GiocatoreDTO> perdenti;
	private List<GiocatoreDTO> vincenti;

	/**
	 * ranking notify to all players
	 * 
	 * @param perdenti
	 * @param vincenti
	 */
	public ClassificaClientNotify(List<Giocatore> vincenti, List<Giocatore> perdenti) {
		this.vincenti = new ArrayList<>();
		this.perdenti = new ArrayList<>();
		for (Giocatore g : vincenti) {
			GiocatoreDTO gDTO = new GiocatoreDTO();
			gDTO.inizializza(g);
			this.vincenti.add(gDTO);
		}
		for (Giocatore g : perdenti) {
			GiocatoreDTO gDTO = new GiocatoreDTO();
			gDTO.inizializza(g);
			this.perdenti.add(gDTO);
		}
	}

	/**
	 * show ranking
	 */
	@Override
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) {
		grafica.mostraClassifica(vincenti, perdenti);
	}

}
