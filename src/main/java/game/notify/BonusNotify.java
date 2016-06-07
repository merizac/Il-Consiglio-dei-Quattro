package game.notify;

import java.util.ArrayList;
import java.util.List;

import game.Giocatore;
import game.azioni.Azione;
import gameDTO.azioniDTO.AzioneDTO;
import view.clientNotify.BonusClientNotify;
import view.clientNotify.ClientNotify;

public class BonusNotify implements Notify {


	private List<Azione> bonus;
	private List<Giocatore> giocatori;

	/**
	 * @param azioni
	 * @param giocatori
	 */
	public BonusNotify(List<Azione> list, List<Giocatore> giocatori) {
		if (list == null)
			throw new NullPointerException("La lista di bonus non può essere null");
		if (giocatori == null)
			throw new NullPointerException("La lista di giocatori non può essere null");

		this.bonus = list;
		this.giocatori = giocatori;
	}

	@Override
	public boolean daInviare(Giocatore giocatore) {
		if (giocatore == null)
			throw new NullPointerException("Il giocatore non può essere null");
		else
			return giocatori.contains(giocatore);
	}
	
	@Override
	public ClientNotify notifyToClientNotify() {
		ArrayList<AzioneDTO> bonusDTO = new ArrayList<>();
		for (Azione a: bonus){
			bonusDTO.add(a.getAzioneDTO());
		}
		
		return new BonusClientNotify(bonusDTO);

	}

}
