package client.grafica.gui;

import common.gameDTO.CittàBonusDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.GameStateDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class GUIGameController {

	private GameStateDTO gameStateDTO;

	@FXML
	private Button arkon;
	@FXML
	private ImageView bonusArkon;
	@FXML
	private Button burgen;
	@FXML
	private ImageView bonusBurgen;
	@FXML
	private Button castrum;
	@FXML
	private ImageView bonusCastrum;
	@FXML
	private Button dorful;
	@FXML
	private ImageView bonusDorful;
	@FXML
	private Button esti;
	@FXML
	private ImageView bonusEsti;
	@FXML
	private Button framek;
	@FXML
	private ImageView bonusFramek;
	@FXML
	private Button graden;
	@FXML
	private ImageView bonusGraden;
	@FXML
	private Button indur;
	@FXML
	private ImageView bonusIndur;
	@FXML
	private Button juvelar;
	@FXML
	private ImageView bonusJuvelar;
	@FXML
	private Button hellar;
	@FXML
	private ImageView bonusHellar;
	@FXML
	private Button kultos;
	@FXML
	private ImageView bonusKultos;
	@FXML
	private Button lyram;
	@FXML
	private ImageView bonusLyram;
	@FXML
	private Button naris;
	@FXML
	private ImageView bonusNaris;
	@FXML
	private Button merkatim;
	@FXML
	private ImageView bonusMerkatim;
	@FXML
	private Button osium;
	@FXML
	private ImageView bonusOsium;
	
	

	/**
	 * @param gameStateDTO
	 *            the gameStateDTO to set
	 */
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}



	public void initialize() {
		for(CittàDTO c: gameStateDTO.getCittà()){
			if(c instanceof CittàBonusDTO){
				switch(c.getNome()){
				case "Arkon":
				
				}
			}
		}
		
	}

}
