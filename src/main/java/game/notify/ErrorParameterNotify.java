package game.notify;

import game.GameState;

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
	public void stamp(GameState gameState) {
		System.out.println(error);

	}

}
