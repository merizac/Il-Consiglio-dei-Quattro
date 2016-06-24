package client.grafica.gui;

import java.util.List;

import common.gameDTO.GameStateDTO;
import common.gameDTO.OffertaDTO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utility.Utils;

public class GUIMarketController {
	
	
	private GUI gui;
	private GameStateDTO gameStateDTO;
	@FXML
	private HBox aiutanti;
	@FXML
	private HBox cartePolitica;
	@FXML
	private HBox tesserePermesso;
	@FXML
	private VBox offerte;
	@FXML
	private Button acquisto;
	@FXML
	private Button offerta;
	@FXML
	private Button passa;
	@FXML
	private TextField prezzo;
	
	
	public void stampaOfferte(List<OffertaDTO> offerte){
		
	}
	
	public VBox getOfferte(){
		return offerte;
	}
	
	/**
	 * @return the aiutanti
	 */
	public HBox getAiutanti() {
		return aiutanti;
	}


	/**
	 * @return the cartePolitica
	 */
	public HBox getCartePolitica() {
		return cartePolitica;
	}


	/**
	 * @return the tesserePermesso
	 */
	public HBox getTesserePermesso() {
		return tesserePermesso;
	}


	/**
	 * @param gui the gui to set
	 */
	public void setGui(GUI gui) {
		this.gui = gui;
	}

	/**
	 * @return the prezzo
	 */
	public TextField getPrezzo() {
		return prezzo;
	}

	/**
	 * @param gameStateDTO the gameStateDTO to set
	 */
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}
	
	public void handleAzione(ActionEvent event){
		
	}
	
	public void handleOfferta(Event event){
		synchronized (gui.getLock()) {
			gui.setParametro(((ImageView)event.getSource()).getUserData());
			gui.getLock().notifyAll();
		}
	}
	
	public void handlePrezzo(Event event){
		synchronized (gui.getLock()) {
			String prezzo=((TextField)event.getSource()).getText();
			if(Utils.isNumeric(prezzo)){
				gui.setParametro(prezzo);
				gui.notifyAll();
			}
		}
	}
	
	

}
