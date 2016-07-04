package client.grafica.gui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utility.AzioneNonEseguibile;
import utility.Utils;

public class GUIMarketController implements Controller {

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
	@FXML
	private TextArea message;

	private List<Button> azioni;
	private static final Logger log = Logger.getLogger(GUIMarketController.class.getName());

	/**
	 * initializes action of market
	 */
	public void inizializza() {
		
		acquisto.setUserData(new AzioneAcquistoDTO());
		offerta.setUserData(new AzioneOffertaDTO());
		passa.setUserData(new PassaDTO());
		azioni = Arrays.asList(acquisto, offerta, passa);
		EventHandler<Event> onMouseClicked= (event) -> {
			URL audioGioco = getClass().getResource("css/MouseClick.mp3");
			Media media = new Media(audioGioco.toString());
			MediaPlayer song = new MediaPlayer(media);
			song.setVolume(1);
			song.play();
		};
		acquisto.setOnMouseClicked(onMouseClicked);
		offerta.setOnMouseClicked(onMouseClicked);
		passa.setOnMouseClicked(onMouseClicked);
		
	}

	/**
	 * 
	 * @return offerte
	 */
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

	/**
	 * handle action of market
	 * 
	 * @param event
	 */
	public void handleAzione(ActionEvent event) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Runnable runnable = () -> {
			for (Button b : azioni)
				b.setDisable(true);
			AzioneDTO azioneDTO = gameStateDTO.getAzioniDisponibili().stream()
					.filter(a -> a.getClass()
							.equals(((AzioneDTO) ((Button) event.getSource()).getUserData()).getClass()))
					.findAny().orElse(null);

			if (azioneDTO == null) {
				gui.azioneNonValida("L'azione non esiste!", "Ooops, riprova e inserisci un'azione valida!");
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
				gui.stopTimer();
				gui.getConnessione().inviaAzione(azioneDTO);
				for (Button b : azioni)
					b.setDisable(false);
			} catch (RemoteException e) {
				log.log(Level.SEVERE, "Errore nell'invio dell'azione", e);
			}

		};
		executor.submit(runnable);
	}

	/**
	 * handle offers action
	 * 
	 * @param event
	 */
	public void handleOfferta(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			MarketableDTO marketableDTO = (MarketableDTO) ((ImageView) event.getSource()).getUserData();
			if (marketableDTO instanceof AiutanteDTO)
				this.aiutanti.getChildren().remove(event.getSource());
			else if (marketableDTO instanceof CartaPoliticaDTO)
				this.cartePolitica.getChildren().remove(event.getSource());
			else
				this.tesserePermesso.getChildren().remove(event.getSource());
			gui.getLock().notifyAll();
		}
	}

	/**
	 * handle setting of price
	 */
	public void handlePrezzo() {
		ok.setDisable(false);
	}

	/**
	 * handle action on 'ok' button
	 */
	public void handleOk() {
		synchronized (gui.getLock()) {
			String prezzoOfferta = this.prezzo.getText();
			if (Utils.isNumeric(prezzoOfferta)) {
				gui.setParametro(prezzoOfferta);
				gui.getLock().notifyAll();
				ok.setDisable(true);
				this.prezzo.clear();
			} else {
				gui.azioneNonValida("Prezzo errato", "Inserire un valore intero");
				this.prezzo.clear();
			}
		}
	}

	/**
	 * handle acquisto action
	 * 
	 * @param event
	 */
	public void handleAcquisto(ActionEvent event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((Button) event.getSource()).getUserData());
			gui.getLock().notifyAll();
		}
	}

	/**
	 * get message
	 */
	@Override
	public TextArea getMessage() {
		return this.message;
	}

}
