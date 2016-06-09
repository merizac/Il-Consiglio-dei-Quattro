package game.azioni;

import java.util.Arrays;
import java.util.UUID;

import game.GameState;
import game.notify.GiocatoreNotify;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.PescaCartaDTO;
import utility.exception.AzioneNonEseguibile;

public class PescaCarta extends Azione {
	
	private final int ID=10;

	@Override
	public void eseguiAzione(GameState gameState) throws AzioneNonEseguibile {
		gameState.getGiocatoreCorrente().getCartePolitica().add(gameState.getMazzoCartePolitica().pescaCarte());
		System.out.println("azione pesca carta");
		System.out.println(gameState.getStato());
		gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(), 
				Arrays.asList(gameState.getGiocatoreCorrente())));
		gameState.getStato().transizionePescaCarta(gameState);
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new PescaCartaDTO();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PescaCarta other = (PescaCarta) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	

}
