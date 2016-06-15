package client.grafica.gui;

import common.gameDTO.GameStateDTO;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class GUIGameController {

	private GameStateDTO gameStateDTO;

	@FXML
	private AnchorPane arkon;
	@FXML
	private AnchorPane castrum;

	/**
	 * @param gameStateDTO
	 *            the gameStateDTO to set
	 */
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}

}
