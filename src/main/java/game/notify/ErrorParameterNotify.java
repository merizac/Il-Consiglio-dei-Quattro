package game.notify;


public class ErrorParameterNotify extends Notify {

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
	public void stamp() {
		System.out.println(error);

	}

}
