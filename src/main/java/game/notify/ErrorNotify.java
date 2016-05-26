package game.notify;

import game.GameState;

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
	public void stamp(GameState gameState) {
		System.out.println(error);
		
	}

}
