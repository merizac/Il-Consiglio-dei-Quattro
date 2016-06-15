package client.grafica.gui;

import java.util.List;

import client.connessione.Connessione;
import client.grafica.Grafica;
import common.azioniDTO.AzioneDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;
import common.gameDTO.OffertaDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application implements Grafica {
	
	private Connessione connessione;
	private GameStateDTO gameStateDTO;
	private Stage finestra;

	@Override
	public void setConnessione(Connessione connessione) {
		this.connessione= connessione;
	}

	@Override
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}
	
	public void setGiocatoreDTO(GiocatoreDTO giocatoreDTO){
		if(giocatoreDTO==null)
			throw new IllegalArgumentException("Giocatore null");
		this.gameStateDTO.setGiocatoreDTO(giocatoreDTO);
	}
	
	public Connessione getConnessione (){
		return this.connessione;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.gameStateDTO=new GameStateDTO();
		this.gameStateDTO.setGiocatoreDTO(new GiocatoreDTO());
		FXMLLoader fxmloader = new FXMLLoader();
		fxmloader.setLocation(getClass().getClassLoader().getResource("client/grafica/gui/fxml/login.fxml"));
		this.finestra = primaryStage;
		
		Parent root = fxmloader.load();
		Scene theScene = new Scene(root);
		
		GUIController controller = fxmloader.getController();
		controller.setGui(this);
		
		finestra.setScene(theScene);
		finestra.show();
		
		for(int i=0; i<10000; i++){
			System.out.println(i);
		}
		
		fxmloader.setLocation(getClass().getClassLoader().getResource("client/grafica/gui/fxml/game.fxml"));
		
		root = fxmloader.load();
		theScene = new Scene(root);
		
		finestra.setScene(theScene);
		finestra.show();
		
		}

	@Override
	public void mostraAzioni(List<AzioneDTO> azioni) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraClassifica(List<GiocatoreDTO> vincenti, List<GiocatoreDTO> perdenti) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraGame(GameStateDTO gameStateDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraGiocatore(GiocatoreDTO giocatoreDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraOfferte(List<OffertaDTO> offerte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		try{
		Application.launch();}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

}
