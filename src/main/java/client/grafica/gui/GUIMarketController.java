package client.grafica.gui;

import java.util.List;

import common.gameDTO.OffertaDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUIMarketController {
	
	@FXML
	private VBox offerte;
	@FXML
	private HBox vendita;
	
	public void stampaOfferte(List<OffertaDTO> offerte){
		
	}
	
	/**
	 * @return the offerte
	 */
	public VBox getOfferte() {
		return offerte;
	}
	/**
	 * @return the vendita
	 */
	public HBox getVendita() {
		return vendita;
	}
	
	

}
