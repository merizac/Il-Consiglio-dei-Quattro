package client.grafica.gui;

import java.rmi.RemoteException;

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
	
	public void inizializza(){
		mappa1.setUserData("mappa1");
		mappa2.setUserData("mappa2");
	}
	
	public void setGui(GUI gui){
		this.gui=gui;
	}
	
	@FXML
	public void handleMappa(ActionEvent event){
		try {
			AzioneMappaDTO azioneMappaDTO=new AzioneMappaDTO((String) ((Button)event.getSource()).getUserData());
			gui.getConnessione().inviaAzione(azioneMappaDTO);
			mappa1.setDisable(true);
			mappa2.setDisable(true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gui.closeSceltaMappa();
	}

}
