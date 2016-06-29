package server.view.clientNotify;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;
import server.model.game.Giocatore;

public class PerdenteClientNotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3349080249799884963L;
	private GiocatoreDTO giocatoreDTO;

	
	
	/**
	 * @param giocatoreDTO
	 */
	public PerdenteClientNotify(Giocatore giocatore) {
		if(giocatore==null)
			throw new NullPointerException("Il giocatore non può essere null!");
		this.giocatoreDTO=new GiocatoreDTO();
		this.giocatoreDTO.inizializza(giocatore);
	}


	@Override
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) {
		String messaggio=giocatoreDTO.getNome().toUpperCase()+", hai perso!!\n";
		messaggio= messaggio + "Hai totalizzato "+ giocatoreDTO.getPunteggioVittoria() + " punti!";
		grafica.mostraMessaggio(messaggio);
	}

}
