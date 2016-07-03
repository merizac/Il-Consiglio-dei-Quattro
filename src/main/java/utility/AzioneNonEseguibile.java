package utility;

public class AzioneNonEseguibile extends Exception {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = 8877087062308881572L;
	
	/**
	 * not available action
	 * 
	 * @param message
	 */
	public AzioneNonEseguibile(String message){
		super(message);
	}

}
