package client.grafica.gui;

import java.rmi.RemoteException;

import client.connessione.ConnessioneRMI;
import client.connessione.ConnessioneSocket;
import common.gameDTO.GiocatoreDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class GUIController {
	
	
	private GUI gui;
	@FXML
	private TextField nome;
	@FXML
	private Text errormessage;
	@FXML
	private CheckBox socket;
	@FXML
	private CheckBox rmi;
	
	/**
	 * @param gui the gui to set
	 */
	public void setGui(GUI gui) {
		this.gui = gui;
	}



	@FXML
	public void button(ActionEvent e){
		if(nome.getText().isEmpty()){
			errormessage.setText("Inserisci il tuo nome");
			return;
		}
		
		else if(!socket.isSelected() && !rmi.isSelected()){
			errormessage.setText("Seleziona la connessione");
			return;
		}
		
		else if(socket.isSelected() && rmi.isSelected()){
			errormessage.setText("Seleziona una sola connessione");
			return;
		}
		
		else{
			System.out.println("premuto bottone");

	
		GiocatoreDTO giocatoreDTO=new GiocatoreDTO();
		giocatoreDTO.setNome(nome.getText());
		gui.getGameStateDTO().setGiocatoreDTO(giocatoreDTO);
		
		if(socket.isSelected())
			gui.setConnessione(new ConnessioneSocket());
		else
			try {
				gui.setConnessione(new ConnessioneRMI());
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		
		gui.setGiocatoreDTO(giocatoreDTO);
		try {
			gui.getConnessione().setGameStateDTO(gui.getGameStateDTO());
			gui.getConnessione().setGrafica(gui);
			gui.getConnessione().start();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		}
		
	}
	
}
