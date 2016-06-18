package client.grafica.gui;

import java.io.IOException;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.view.clientNotify.ClientNotify;

public class GUI extends Application implements Grafica {

	private Connessione connessione;
	private GameStateDTO gameStateDTO;
	private GUIGameController controller;
	private Stage finestra;

	@Override
	public void setConnessione(Connessione connessione) {
		this.connessione = connessione;
	}

	@Override
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}

	public GameStateDTO getGameStateDTO() {
		return this.gameStateDTO;
	}

	public void setGiocatoreDTO(GiocatoreDTO giocatoreDTO) {
		if (giocatoreDTO == null)
			throw new IllegalArgumentException("Giocatore null");
		this.gameStateDTO.setGiocatoreDTO(giocatoreDTO);
	}

	public Connessione getConnessione() {
		return this.connessione;
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.gameStateDTO = new GameStateDTO();
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
	}

	public void inizializza() throws IOException {
		FXMLLoader fxmloader = new FXMLLoader();
		fxmloader.setLocation(getClass().getClassLoader().getResource("client/grafica/gui/fxml/gameState.fxml"));

		Parent root = fxmloader.load();
		Scene theScene = new Scene(root);

		controller = fxmloader.getController();
		controller.setGameStateDTO(this.gameStateDTO);
		controller.setGui(this);
		finestra.setScene(theScene);
		finestra.show();
	}

	@Override
	public void mostraAzioni(List<AzioneDTO> azioni) {
		
	}

	@Override
	public void mostraClassifica(List<GiocatoreDTO> vincenti, List<GiocatoreDTO> perdenti) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostraGame(GameStateDTO gameStateDTO) throws IOException {

	}

	@Override
	public void mostraGiocatore(GiocatoreDTO giocatoreDTO) {
		gameStateDTO.getGiocatoreDTO().getTesserePermesso().add(gameStateDTO.getRegioni().get(0).getTesserePermessoScoperte().get(0));
		controller.mostraTesserePermesso(giocatoreDTO.getTesserePermesso());
		controller.mostraCartePolitica(giocatoreDTO.getCartePolitica());
		controller.mostraNomeGiocatore(giocatoreDTO.getNome());
		controller.mostraPuntiGiocatore(giocatoreDTO.getPunteggioVittoria());
		controller.mostraMoneteGiocatore(giocatoreDTO.getPunteggioRicchezza());
		controller.mostraAiutantiGiocatore(giocatoreDTO.getAiutanti());
		controller.mostraEmporiGiocatore(giocatoreDTO.getEmpori());
		controller.mostraTessereBonusGiocatore(giocatoreDTO.getTessereBonus());
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		Text text=new Text();
		text.setText(messaggio);
		
	}

	@Override
	public void mostraOfferte(List<OffertaDTO> offerte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notify(ClientNotify notify) {
		notify.update(gameStateDTO);
		try {
			notify.stamp(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void mostraAvversario(GiocatoreDTO avversario) {
		// TODO Auto-generated method stub
		
	}
}
