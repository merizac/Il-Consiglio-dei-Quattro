package game.notify;

import gameDTO.gameDTO.GameStateDTO;

public class ErrorNotify extends NotifyGiocatoreCorrente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4112723540694819709L;
	private String error;
	
	/**
	 * @param error
	 */
	public ErrorNotify(String error) {
		super();
		this.error = error;
	}

	@Override
	public void stamp(GameStateDTO gameState) {
		System.out.println(error);
		
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		// TODO Auto-generated method stub
		
	}

}
