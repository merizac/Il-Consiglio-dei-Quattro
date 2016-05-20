package game.query;

import java.util.List;

import game.Regione;
import game.notify.Notify;

public class RegioniNotify extends Notify {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8358819021347295505L;
	private List<Regione> regioni;
	

	/**
	 * @param regioni
	 */
	public RegioniNotify(List<Regione> regioni) {
		super();
		this.regioni = regioni;
	}


	@Override
	public void stamp() {
		for(Regione regione:regioni){
			System.out.println(regione);
		}
	}

}
