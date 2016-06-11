package client;

import common.gameDTO.GameStateDTO;

public class GUI implements Grafica {
	
	private Connessione connessione;
	private GameStateDTO gameStateDTO;

	@Override
	public void setConnessione(Connessione connessione) {
		this.connessione= connessione;
	}

	@Override
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	
	

}
