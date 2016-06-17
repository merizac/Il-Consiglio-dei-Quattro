package client.grafica.gui;

import common.gameDTO.GameStateDTO;

public class GUIGameController {

	private GameStateDTO gameStateDTO;
	private GUI gui;

	

	/**
	 * @param gameStateDTO
	 *            the gameStateDTO to set
	 */
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}



	/**
	 * @param gui the gui to set
	 */
	public void setGui(GUI gui) {
		this.gui = gui;
	}

	
}
