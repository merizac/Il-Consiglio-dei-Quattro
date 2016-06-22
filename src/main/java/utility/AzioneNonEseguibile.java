package utility;

public class AzioneNonEseguibile extends Exception {

	private static final long serialVersionUID = 8877087062308881572L;
	
	public AzioneNonEseguibile(String message){
		super(message);
	}

}
