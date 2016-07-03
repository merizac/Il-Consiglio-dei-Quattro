package utility;

public class ParameterException extends Exception {

	private static final long serialVersionUID = 4728662193729709975L;

	/**
	 * exception for error in parameter
	 * 
	 * @param messaggio
	 */
	public ParameterException(String messaggio) {
		super(messaggio);
	}

}
