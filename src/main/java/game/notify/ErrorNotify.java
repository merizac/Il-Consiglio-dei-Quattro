package game.notify;

import game.Notify;
import view.View;

public class ErrorNotify implements Notify {

	private String error;
	
	@Override
	public void stamp(View view) {
		System.out.println(error);
		
	}

}
