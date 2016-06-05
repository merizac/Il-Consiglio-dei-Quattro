package client;

import gameDTO.azioniDTO.AzioneDTO;

public interface Connessione {
	
	public void start();

	public void inviaAzione(AzioneDTO action);

}
