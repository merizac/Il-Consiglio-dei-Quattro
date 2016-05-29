package game.notify;

import gameDTO.gameDTO.GameStateDTO;

public class GameNotify extends NotifyGiocatori {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7941386236992971357L;
	private GameStateDTO gameStateDTO;

	public GameNotify(GameStateDTO gameStateDTO) {
		this.gameStateDTO=gameStateDTO;
	}

	
	@Override
	public void update(GameStateDTO gameStateDTO){
		gameStateDTO.setCittà(this.gameStateDTO.getCittà());
		gameStateDTO.setConsiglieri(this.gameStateDTO.getConsiglieri());
		gameStateDTO.setPedinaRE(this.gameStateDTO.getPedinaRE());
		gameStateDTO.setPlanciaReDTO(this.gameStateDTO.getPlanciaReDTO());
		gameStateDTO.setRegioni(this.gameStateDTO.getRegioni());
	}


	@Override
	public void stamp(GameStateDTO gameState) {
		System.out.println(gameState);
		System.out.println(this.gameStateDTO);
	}

		
}
