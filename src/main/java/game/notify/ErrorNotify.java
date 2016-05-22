package game.notify;

public class ErrorNotify extends Notify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4112723540694819709L;
	private String error;
	
	@Override
	public void stamp() {
		System.out.println(error);
		
	}

}
