package game.notify;

import game.Notify;
import view.View;

public class ErrorParameterNotify implements Notify {

	private String error;
	
	/**
	 * @param error
	 */
	public ErrorParameterNotify(String error) {
		this.error = error;
	}

	@Override
	public void stamp(View view) {
		System.out.println(error);

	}

}
