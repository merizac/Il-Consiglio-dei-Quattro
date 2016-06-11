package client;

import common.gameDTO.GameStateDTO;

public interface Grafica {
	
	public void setConnessione(Connessione connessione);

	public void setGameStateDTO(GameStateDTO gameStateDTO);
	
	public void start();
}
