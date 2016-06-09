package game.azioni;

import java.util.UUID;

import game.GameState;
import game.Giocatore;
import game.macchinaStati.StatoAcquistoMarket;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.PassaDTO;
import utility.exception.AzioneNonEseguibile;

public class Passa extends Azione {
	
	private final int ID=9; 

	@Override
	public void eseguiAzione(GameState gameState) {
		try {
			gameState.getStato().transizionePassa(gameState);
			System.out.println("Stato :"+gameState.getStato());
		} catch (AzioneNonEseguibile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean isTurno(Giocatore giocatore, GameState gameState) {
		System.out.println("entrato");
		if (gameState.getStato() instanceof StatoAcquistoMarket)
				return giocatore.equals(((StatoAcquistoMarket) gameState.getStato()).getGiocatori().get(0));
		else
			return giocatore.equals(gameState.getGiocatoreCorrente());
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new PassaDTO();
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
		Passa other = (Passa) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	
}
