package game.notify;

import game.GameState;
import game.Giocatore;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;

public class GameStateStartNotify implements NotifyGiocatori {

	private static final long serialVersionUID = 1799063332237801318L;
	private GameStateDTO gameStateDTO;
	private GiocatoreDTO giocatoreDTO;
	
	
	/**
	 * @param gameStateDTO
	 * @param giocatoreDTO
	 */
	public GameStateStartNotify(GameState gameState) {
		this.gameStateDTO = new GameStateDTO();
		this.gameStateDTO.inizializza(gameState);
		this.giocatoreDTO = new GiocatoreDTO();
	}

	public void setGiocatoreDTO(Giocatore giocatore){
		this.giocatoreDTO.inizializza(giocatore);
	}
	@Override
	public void update(GameStateDTO gameStateDTO){
		gameStateDTO.setCittà(this.gameStateDTO.getCittà());
		gameStateDTO.setConsiglieri(this.gameStateDTO.getConsiglieri());
		gameStateDTO.setPedinaRE(this.gameStateDTO.getPedinaRE());
		gameStateDTO.setPlanciaReDTO(this.gameStateDTO.getPlanciaReDTO());
		gameStateDTO.setRegioni(this.gameStateDTO.getRegioni());
	}

	public void update(GiocatoreDTO giocatoreDTO){
		giocatoreDTO.setAiutanti(this.giocatoreDTO.getAiutanti());
		giocatoreDTO.setCartePolitica(this.giocatoreDTO.getCartePolitica());
		giocatoreDTO.setColoreGiocatore(this.giocatoreDTO.getColoreGiocatore());
		giocatoreDTO.setEmpori(this.giocatoreDTO.getEmpori());
		giocatoreDTO.setPunteggioNobiltà(this.giocatoreDTO.getPunteggioNobiltà());
		giocatoreDTO.setPunteggioRicchezza(this.giocatoreDTO.getPunteggioRicchezza());
		giocatoreDTO.setPunteggioVittoria(this.giocatoreDTO.getPunteggioVittoria());
	}
	@Override
	public void stamp() {
		System.out.println(gameStateDTO);
		System.out.println(giocatoreDTO);
		
	}

}
