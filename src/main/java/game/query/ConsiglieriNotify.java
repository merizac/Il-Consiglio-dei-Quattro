package game.query;

import java.util.List;

import game.Consigliere;
import game.notify.Notify;

public class ConsiglieriNotify extends Notify {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6355577701744908965L;
	private List<Consigliere> consiglieri;

	/**
	 * @param consiglieri
	 */
	public ConsiglieriNotify(List<Consigliere> consiglieri) {
		super();
		this.consiglieri = consiglieri;
	}


	@Override
	public void stamp() {
	for(Consigliere consigliere:consiglieri){
		System.out.println(consigliere);
	}
	}

}
