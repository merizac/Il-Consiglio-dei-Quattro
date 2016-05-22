package game.query;

import java.util.List;

import game.azioni.Azione;
import game.notify.Notify;

public class AzioniNotify extends Notify {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9218488621539162960L;
	private List<Azione> azioniDisponibili;

	/**
	 * @param azioniDisponibili
	 */
	public AzioniNotify(List<Azione> azioniDisponibili) {
		super();
		this.azioniDisponibili = azioniDisponibili;
	}


	@Override
	public void stamp() {
		for(Azione azione: azioniDisponibili){
			System.out.println(azione);
		}
	}

}
