package view.clientNotify;

import game.Giocatore;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;

public class VincitoreClientNotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -139988226344509096L;
	private GiocatoreDTO giocatoreDTO;

	/**
	 * @param giocatore
	 */
	public VincitoreClientNotify(Giocatore giocatore) {
		if(giocatore==null)
			throw new NullPointerException("Il giocatore non può essere null!");
		this.giocatoreDTO=new GiocatoreDTO();
		this.giocatoreDTO.inizializza(giocatore);
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stamp() {
		System.out.println("Complimenti "+giocatoreDTO.getNome().toUpperCase()
				+ " hai vinto!!");
		System.out.println("Hai totalizzato "+ giocatoreDTO.getPunteggioVittoria() + "punti!");
		
	}

}
