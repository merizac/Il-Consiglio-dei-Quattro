package client.grafica.gui;

import java.io.IOException;
import java.rmi.RemoteException;

import client.connessione.ConnessioneRMI;
import client.connessione.ConnessioneSocket;
import common.gameDTO.GiocatoreDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	 * @param gui
	 *            the gui to set
	 */
	public void setGui(GUI gui) {
		this.gui = gui;
	}

	@FXML
	public void button(ActionEvent e) {
		if (nome.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText("Non hai inserito il tuo nome!");
			alert.setContentText("Ooops, riprova!");

			alert.showAndWait();
			return;
		}

		else if (!socket.isSelected() && !rmi.isSelected()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText("Non hai inserito la connesione!");
			alert.setContentText("Ooops, riprova!");

			alert.showAndWait();
			return;
		}

		else if (socket.isSelected() && rmi.isSelected()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText("Seleziona una sola connessione!");
			alert.setContentText("Ooops, riprova!");

			alert.showAndWait();
			return;
		}

		GiocatoreDTO giocatoreDTO = new GiocatoreDTO();
		giocatoreDTO.setNome(nome.getText());
		gui.getGameStateDTO().setGiocatoreDTO(giocatoreDTO);

		if (socket.isSelected())
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
		try {
			gui.inizializza();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
