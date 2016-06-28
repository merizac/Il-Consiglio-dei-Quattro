package client.grafica.gui;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import common.azioniDTO.AzioneAcquistoDTO;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.AzioneOffertaDTO;
import common.azioniDTO.AzioneParametri;
import common.azioniDTO.PassaDTO;
import common.gameDTO.AiutanteDTO;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.MarketableDTO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utility.AzioneNonEseguibile;

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
	@FXML
	private Button ok;

	private List<Button> azioni;

	public void inizializza() {
		acquisto.setUserData(new AzioneAcquistoDTO());
		offerta.setUserData(new AzioneOffertaDTO());
		passa.setUserData(new PassaDTO());
		azioni = Arrays.asList(acquisto, offerta, passa);
	}

	public VBox getOfferte() {
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
	 * @param gui
	 *            the gui to set
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
	 * @param gameStateDTO
	 *            the gameStateDTO to set
	 */
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}

	/**
	 * @return the ok
	 */
	public Button getOk() {
		return ok;
	}

	public void handleAzione(ActionEvent event) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new Runnable() {

			@Override
			public void run() {
				try {
					for (Button b : azioni)
						b.setDisable(true);
					AzioneDTO azioneDTO = gameStateDTO.getAzioniDisponibili().stream()
							.filter(a -> a.getClass()
									.equals(((AzioneDTO) ((Button) event.getSource()).getUserData()).getClass()))
							.findAny().orElse(null);

					if (azioneDTO == null) {
						gui.azioneNonValida("L'azione non esiste!","Ooops, riprova e inserisci un'azione valida!");
						for (Button b : azioni)
							b.setDisable(false);
						return;
					} else if (azioneDTO instanceof AzioneParametri) {
						try {
							((AzioneParametri) azioneDTO).parametri().setParametri(gui, gameStateDTO);
						} catch (AzioneNonEseguibile e) {
							gui.azioneNonValida("Azione non eseguibile!", e.getMessage());
							return;
						}
					}
					try {
						gui.getConnessione().inviaAzione(azioneDTO);
						gui.stopTimer();
						for (Button b : azioni)
							b.setDisable(false);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	public void handleOfferta(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			MarketableDTO marketableDTO=(MarketableDTO) ((ImageView) event.getSource()).getUserData();
			if(marketableDTO instanceof AiutanteDTO)
				this.aiutanti.getChildren().remove(event.getSource());
			else if(marketableDTO instanceof CartaPoliticaDTO)
				this.cartePolitica.getChildren().remove(event.getSource());
			else
				this.tesserePermesso.getChildren().remove(event.getSource());
			//((ImageView) event.getSource()).setDisable(true);
			//((ImageView) event.getSource()).setOpacity(0.5);
			gui.getLock().notifyAll();
		}
	}

	public void handlePrezzo(Event event) {
		ok.setDisable(false);
	}

	public void handleOk(ActionEvent event) {
		synchronized (gui.getLock()) {
			String prezzo = this.prezzo.getText();
			gui.setParametro(prezzo);
			gui.getLock().notifyAll();
			ok.setDisable(true);
		}
	}
	
	public void handleAcquisto(ActionEvent event){
		synchronized(gui.getLock()){
			gui.setParametro(((Button)event.getSource()).getUserData());
			gui.getLock().notifyAll();
		}
	}

}
