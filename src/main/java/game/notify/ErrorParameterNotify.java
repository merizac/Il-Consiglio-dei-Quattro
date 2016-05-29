package game.notify;

import gameDTO.gameDTO.GameStateDTO;

public class ErrorParameterNotify extends NotifyGiocatoreCorrente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3147356102007186569L;
	private String error;
	
	/**
	 * @param error
	 */
	public ErrorParameterNotify(String error) {
		this.error = error;
	}


	@Override
	public void update(GameStateDTO gameStateDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stamp(GameStateDTO gameState) {
		// TODO Auto-generated method stub
		
	}

}
