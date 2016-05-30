package game.notify;

import java.util.List;

public class AzioniNotify implements NotifyGiocatoreCorrente {


	private static final long serialVersionUID = -7471237200273859021L;
	private List<String> azioni;

	public AzioniNotify(List<String> azioni) {
		this.azioni=azioni;
	}

	@Override
	public void stamp() {
		for(String azione:azioni){
			System.out.println(azione);
		}
	}



}
