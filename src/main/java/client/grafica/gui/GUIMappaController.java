package client.grafica.gui;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.azioniDTO.AzioneMappaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GUIMappaController {
	
	@FXML
	private Button mappa1;
	@FXML
	private Button mappa2;
	private GUI gui;
	private static final Logger log=Logger.getLogger(GUIMappaController.class.getName());
	
	/**
	 * initializes map
	 */
	public void inizializza(){
		mappa1.setUserData("mappa1");
		mappa2.setUserData("mappa2");
	}
	
	/**
	 * set gui
	 * 
	 * @param gui
	 */
	public void setGui(GUI gui){
		this.gui=gui;
	}
	
	/**
	 * handle action on map
	 * 
	 * @param event
	 */
	@FXML
	public void handleMappa(ActionEvent event){
		try {
			AzioneMappaDTO azioneMappaDTO=new AzioneMappaDTO((String) ((Button)event.getSource()).getUserData());
			gui.getConnessione().inviaAzione(azioneMappaDTO);
			mappa1.setDisable(true);
			mappa2.setDisable(true);
		} catch (RemoteException e) {
			log.log(Level.SEVERE, "Errore nell'invio dell'azione per settare la mappa", e);
		}
		
		gui.closeSceltaMappa();
	}

}
