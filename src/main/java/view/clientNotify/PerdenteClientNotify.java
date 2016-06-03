package view.clientNotify;

import game.Giocatore;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;

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
	public void update(GameStateDTO gameStateDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stamp() {
		System.out.println(giocatoreDTO.getNome().toUpperCase()+", hai perso!!");
		System.out.println("Hai totalizzato "+ giocatoreDTO.getPunteggioVittoria() + " punti!");
		
	}

}
