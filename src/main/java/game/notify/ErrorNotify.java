package game.notify;

public class ErrorNotify implements NotifyGiocatoreCorrente {

	private static final long serialVersionUID = 4112723540694819709L;
	private String error;
	
	/**
	 * @param error
	 */
	public ErrorNotify(String error) {
		this.error = error;
	}

	@Override
	public void stamp() {
		System.out.println(error);
	}


}
