package client.grafica.gui;

import java.util.List;

import common.gameDTO.GameStateDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class GUIGameController {
	
	private GameStateDTO gameStateDTO;

	/**
	 * @param gameStateDTO the gameStateDTO to set
	 */
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}

}
