package view.clientNotify;

import java.util.List;

import game.Giocatore;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;

public class ClassificaClientNotify implements ClientNotify {

	private static final long serialVersionUID = -139988226344509096L;
	private List<GiocatoreDTO> perdenti;
	private List<GiocatoreDTO> vincenti;

	/**
	 * @param perdenti
	 * @param vincenti
	 */
	public ClassificaClientNotify(List<Giocatore> perdenti, List<Giocatore> vincenti) {
		for(Giocatore g:perdenti){
			GiocatoreDTO gDTO=new GiocatoreDTO();
			gDTO.inizializza(g);
			this.perdenti.add(gDTO);
		}
		for(Giocatore g:vincenti){
			GiocatoreDTO gDTO=new GiocatoreDTO();
			gDTO.inizializza(g);
			this.vincenti.add(gDTO);
		}
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stamp() {
		for(GiocatoreDTO g: vincenti){
			System.out.println("Giocatore :"+g.getNome().toUpperCase()+ " Punteggio "+ g.getPunteggioVittoria()+" punti");
		}
		for(GiocatoreDTO g: perdenti){
			System.out.println("Giocatore :"+g.getNome().toUpperCase()+ " Punteggio "+ g.getPunteggioVittoria()+" punti");
		}
	}

}
