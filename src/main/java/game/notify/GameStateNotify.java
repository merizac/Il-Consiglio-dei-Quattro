package game.notify;

import game.GameState;
import gameDTO.gameDTO.GameStateDTO;

public class GameStateNotify implements NotifyGiocatori {

	private static final long serialVersionUID = 4438277578283085786L;
	private GameStateDTO gameStateDTO;

	/**
	 * @param gameStateDTO
	 */
	public GameStateNotify(GameState gameState) {
		this.gameStateDTO = new GameStateDTO();
		this.gameStateDTO.inizializza(gameState);
	}
	
	@Override
	public void update(GameStateDTO gameStateDTO) {
		gameStateDTO.setCittà(this.gameStateDTO.getCittà());
		gameStateDTO.setConsiglieri(this.gameStateDTO.getConsiglieri());
		gameStateDTO.setPedinaRE(this.gameStateDTO.getPedinaRE());
		gameStateDTO.setPlanciaReDTO(this.gameStateDTO.getPlanciaReDTO());
		gameStateDTO.setRegioni(this.gameStateDTO.getRegioni());
	}

	@Override
	public void stamp() {
		System.out.println(gameStateDTO);
		
	}

}
