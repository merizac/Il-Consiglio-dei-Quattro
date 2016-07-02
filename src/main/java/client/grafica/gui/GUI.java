package client.grafica.gui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.DocFlavor.URL;


import client.connessione.Connessione;
import client.grafica.Grafica;
import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.AzioneParametri;
import common.azioniDTO.BonusGettoneNDTO;
import common.azioniDTO.BonusTesseraAcquistataNDTO;
import common.azioniDTO.BonusTesseraPermessoNDTO;
import common.azioniDTO.CambioTesserePermessoDTO;
import common.azioniDTO.CostruzioneAiutoReDTO;
import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import common.azioniDTO.ElezioneConsigliereDTO;
import common.azioniDTO.ElezioneConsigliereVeloceDTO;
import common.azioniDTO.ExitDTO;
import common.azioniDTO.IngaggioAiutanteDTO;
import common.azioniDTO.PassaDTO;
import common.azioniDTO.PescaCartaDTO;
import common.azioniDTO.SecondaAzionePrincipaleDTO;
import common.gameDTO.AiutanteDTO;
import common.gameDTO.BalconeDTO;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàBonusDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.ColoreDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;
import common.gameDTO.MarketableDTO;
import common.gameDTO.OffertaDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utility.AzioneNonEseguibile;

public class GUI extends Application implements Grafica {

	private Connessione connessione;
	private GameStateDTO gameStateDTO;
	private GUIGameController controller;
	private GUIMarketController controllerMarket;
	private GUIMappaController controllerMappa;
	private Stage finestra;
	private Stage market;
	private Stage mappa;
	private Object lock = new Object();
	private Object parametro;
	private boolean carteInserite = false;
	private Map<GiocatoreDTO, Tab> tabAvversari = new HashMap<>();
	private final int timeout = 180000;
	private Timer timer;
	private TimerTask task;
	private static final Logger log = Logger.getLogger(GUI.class.getName());

	@Override
	public void setConnessione(Connessione connessione) {
		this.connessione = connessione;
	}

	/**
	 * @return the lock
	 */
	public Object getLock() {
		return lock;
	}

	/**
	 * @param lock
	 *            the lock to set
	 */
	public void setLock(Object lock) {
		this.lock = lock;
	}

	/**
	 * @return the parametro
	 */
	public Object getParametro() {
		return parametro;
	}

	/**
	 * @param parametro
	 *            the parametro to set
	 */
	public void setParametro(Object parametro) {
		this.parametro = parametro;
	}

	/**
	 * @return the carteInserite
	 */
	public boolean isCarteInserite() {
		return carteInserite;
	}

	/**
	 * @param carteInserite
	 *            the carteInserite to set
	 */
	public void setCarteInserite(boolean carteInserite) {
		this.carteInserite = carteInserite;
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
		primaryStage.setTitle("Login ");
		this.gameStateDTO = new GameStateDTO();
		this.gameStateDTO.setGiocatoreDTO(new GiocatoreDTO());
		FXMLLoader fxmloader = new FXMLLoader();
		fxmloader.setLocation(getClass().getClassLoader().getResource("client/grafica/gui/fxml/login.fxml"));
		this.finestra = primaryStage;

		Parent root = fxmloader.load();
		Scene theScene = new Scene(root);
		Image image = new Image(getClass().getResource("css/cursore.png").toExternalForm());
		theScene.setCursor(new ImageCursor(image));

		GUIController controllerLogin = fxmloader.getController();
		controllerLogin.setGui(this);

		finestra.setScene(theScene);
		finestra.show();
	}

	public void inizializza() {
		FXMLLoader fxmloader = new FXMLLoader();
		fxmloader.setLocation(getClass().getClassLoader().getResource("client/grafica/gui/fxml/gameState.fxml"));
		String audioGioco = this.getClass().getResource("css/audioGioco.mp3").toExternalForm();
		Media media = new Media(audioGioco);
		MediaPlayer song = new MediaPlayer(media);
		song.play();
		song.setVolume(0.5);
		Parent root = null;
		try {
			root = fxmloader.load();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Errore nel caricamento del file gameState.fxml", e);
		}
		Scene theScene = new Scene(root);
		Image image = new Image(getClass().getResource("css/cursore.png").toExternalForm());
		theScene.setCursor(new ImageCursor(image));
		controller = fxmloader.getController();
		controller.setGameStateDTO(this.gameStateDTO);
		controller.setGui(this);
		finestra.setScene(theScene);
		finestra.setTitle("Il Consiglio Dei Quattro");
		EventHandler<WindowEvent> onClose = (event) -> {
			try {
				connessione.inviaAzione(new ExitDTO());
			} catch (RemoteException e) {
				log.log(Level.SEVERE, "Errore nell'invio dell'azione", e);
			}
		};
		finestra.setOnCloseRequest(onClose);
		finestra.show();
	}

	@Override
	public void mostraAzioni(List<AzioneDTO> azioni) {

		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				try {
					connessione.inviaAzione(new ExitDTO());
					close();
				} catch (RemoteException e) {
					log.log(Level.SEVERE, "Errore nell'invio dell'azione", e);
				}
			}
		};

		//timer.schedule(task, timeout);

		if (azioni.get(0) instanceof BonusGettoneNDTO || azioni.get(0) instanceof BonusTesseraAcquistataNDTO
				|| azioni.get(0) instanceof BonusTesseraPermessoNDTO) {
			try {
				((AzioneParametri) azioni.get(0)).parametri().setParametri(this, gameStateDTO);
			} catch (AzioneNonEseguibile e) {
				this.mostraMessaggio(e.getMessage());
			}
			try {
				connessione.inviaAzione(azioni.get(0));
				stopTimer();
			} catch (RemoteException e) {
				log.log(Level.SEVERE, "Errore nell'invio dell'azione", e);
			}
		}
	}

	@Override
	public void mostraClassifica(List<GiocatoreDTO> vincenti, List<GiocatoreDTO> perdenti) {
		Runnable runnable = () -> {
			String messaggio = "";
			for (GiocatoreDTO g : vincenti) {
				messaggio = messaggio + g.getNome().toUpperCase() + " Punteggio " + g.getPunteggioVittoria()
						+ " punti\n";
			}
			for (GiocatoreDTO g : perdenti) {
				messaggio = messaggio + g.getNome().toUpperCase() + " Punteggio " + g.getPunteggioVittoria()
						+ " punti\n";
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Classifica");
			alert.setHeaderText(vincenti.contains(gameStateDTO.getGiocatoreDTO())
					? "Complimenti " + gameStateDTO.getGiocatoreDTO().getNome() + " hai vinto"
					: "Mi dispiace " + gameStateDTO.getGiocatoreDTO().getNome() + " hai perso");
			alert.setContentText(messaggio);
			alert.showAndWait();
			finestra.close();
		};
		Platform.runLater(runnable);

	}

	@Override
	public void mostraGame(GameStateDTO gameStateDTO) throws IOException {
		if (controllerMappa != null && mappa.isShowing()) {
			closeSceltaMappa();
		}

		controller.mostraTesserePermessoRegioni(gameStateDTO.getRegioni());

		controller.getMappaImmagine().setImage(
				new Image(getClass().getResource("css/" + gameStateDTO.getNomeMappa() + ".jpg").toExternalForm()));
		controller.mostraGettoni(new ArrayList<>(gameStateDTO.getCittà()));
		controller.mostraTessereBonus();
		controller.mostraConsiglieriBalcone();
		controller.mostraRiserva(gameStateDTO.getConsiglieri());
		controller.assegnaBottoniCittà(new ArrayList<>(gameStateDTO.getCittà()));
		assegnaRegione();
		assegnaAzioni();
		assegnaRe();
		stampaEmporiCittà(new ArrayList<>(gameStateDTO.getCittà()));
	}

	private void stampaEmporiCittà(List<CittàDTO> città) {

		Runnable runnable = () -> {
			List<HBox> hbox = controller.getListaEmporiHBox();
			Map<String, Image> mappaEmpori = controller.getMappaEmpori();

			for (int i = 0; i < città.size() - 1; i++) {
				hbox.get(i).getChildren().clear();
				for (String emporio : città.get(i).getEmpori()) {
					ImageView imageView = new ImageView();
					imageView.setImage(mappaEmpori.get(emporio));
					hbox.get(i).getChildren().add(imageView);
					imageView.setFitHeight(15);
					imageView.setPreserveRatio(true);
				}
			}
		};

		Platform.runLater(runnable);
	}

	private void assegnaRe() {
		Runnable runnable = () -> {
			Map<String, Image> immagineRe = controller.getMappaBonus();
			ImageView imageView = new ImageView();
			imageView.setImage(immagineRe.get("Re"));
			controller.getRe().getChildren().add(imageView);
			imageView.setPreserveRatio(true);
			imageView.setFitHeight(20);
			imageView.setTranslateX(60);
			imageView.setTranslateY(-15);
			imageView.setRotate(40);
		};
		Platform.runLater(runnable);

	}

	private void assegnaAzioni() {
		List<Button> azioni = controller.getAzioni();
		EventHandler<Event> onMouseClicked = (Event) -> {
			
		};
		azioni.get(0).setUserData(new ElezioneConsigliereDTO());
		azioni.get(1).setUserData(new AcquistoTesseraPermessoDTO());
		azioni.get(2).setUserData(new CostruzioneTesseraPermessoDTO());
		azioni.get(3).setUserData(new CostruzioneAiutoReDTO());
		azioni.get(4).setUserData(new IngaggioAiutanteDTO());
		azioni.get(5).setUserData(new CambioTesserePermessoDTO());
		azioni.get(6).setUserData(new ElezioneConsigliereVeloceDTO());
		azioni.get(7).setUserData(new SecondaAzionePrincipaleDTO());
		azioni.get(8).setUserData(new PassaDTO());
		azioni.get(9).setUserData(new PescaCartaDTO());
	}

	private void assegnaRegione() {
		List<ImageView> regioni = controller.getRegioni();
		for (int i = 0; i < regioni.size(); i++) {
			regioni.get(i).setUserData(gameStateDTO.getRegioni().get(i));
		}
	}

	@Override
	public void mostraGiocatore(GiocatoreDTO giocatoreDTO) {
		stampaTesserePermesso(controller.getTesserePermessoGiocatore(), giocatoreDTO.getTesserePermesso(), 0, 70, 1);
		stampaTesserePermesso(controller.getTesserePermessoGiocatoreUsate(), giocatoreDTO.getTesserePermessoUsate(), 0,
				70, 0.6);
		cartePolitica(giocatoreDTO.getCartePolitica());
		controller.mostraNomeGiocatore(giocatoreDTO.getNome());
		HBox hbox = controller.getGiocatoreGUI();
		stampaPuntiAvversario(giocatoreDTO, hbox, 50);
		hbox.setSpacing(30);
	}

	private void cartePolitica(List<CartaPoliticaDTO> carte) {
		Runnable runnable = () -> {

			Map<String, Image> mappaCarte = controller.getMappaCartePolitica();
			HBox cartePolitica = controller.getCartePoliticaGiocatore();

			cartePolitica.getChildren().clear();
			for (CartaPoliticaDTO c : carte) {

				ImageView image = new ImageView();
				image.setFitHeight(72);
				image.setPreserveRatio(true);
				image.setImage(mappaCarte.get(c.toString()));
				image.setDisable(true);
				image.setUserData(c);
				cartePolitica.getChildren().add(image);
				EventHandler<Event> onMouseClicked = (event) -> {
					controller.handleCartaPolitica(event);
				};
				image.setOnMouseClicked(onMouseClicked);
			}

		};
		Platform.runLater(runnable);
	}

	/**
	 * @param tesserePermesso
	 *            HBox where it stamp tessere
	 * @param tessere
	 * @param tessereUsate
	 *            0 if not stamp, else the number of tessere permesso used
	 * @param dimensione
	 */
	private void stampaTesserePermesso(HBox tesserePermesso, List<TesseraPermessoDTO> tessere, int tessereUsate,
			int dimensione, double opacity) {
		Runnable runnable = () -> {

			Map<String, Image> mappaTessere = controller.getMappaTesserePermesso();
			tesserePermesso.getChildren().clear();
			if (tessereUsate != 0) {
				Pane pane = new Pane();
				ImageView used = new ImageView();
				Text text = new Text(Integer.toString(tessereUsate));
				used.setFitHeight(dimensione);
				used.setPreserveRatio(true);
				used.setImage(mappaTessere.get("TesseraPermesso"));
				tesserePermesso.getChildren().add(pane);
				pane.getChildren().add(used);
				pane.getChildren().add(text);
				text.relocate(dimensione / 2, dimensione / 2);
				text.setStyle("-fx-font: 17.0px Algerian; -fx-fill: white;");
			}

			for (TesseraPermessoDTO t : tessere) {
				ImageView image = new ImageView();
				image.setFitHeight(dimensione);
				image.setPreserveRatio(true);
				image.setImage(mappaTessere.get(t.toString()));
				EventHandler<Event> onMouseClicked = (event) -> {
					controller.handleTesseraPermessoGiocatore(event);
				};
				image.setOnMouseClicked(onMouseClicked);
				image.setDisable(true);
				image.setUserData(t);
				image.setOpacity(opacity);
				tesserePermesso.getChildren().add(image);
			}

		};
		Platform.runLater(runnable);

	}

	public void stampaConsiglieriBalcone() {
		Runnable runnable = () -> {
			List<HBox> balconi = controller.getBalconi();
			Map<String, Image> mappaConsiglieri = controller.getMappaConsiglieri();
			balconi.get(0).getChildren().clear();
			for (ConsigliereDTO c : gameStateDTO.getRegioni().get(0).getBalcone().getConsiglieri()) {
				ImageView imageView = new ImageView(mappaConsiglieri.get(c.toString()));
				imageView.setFitHeight(25);
				imageView.setPreserveRatio(true);
				balconi.get(0).getChildren().add(imageView);
			}

			balconi.get(0).setUserData(gameStateDTO.getRegioni().get(0).getBalcone());
			balconi.get(0).setDisable(true);

			balconi.get(1).getChildren().clear();
			for (ConsigliereDTO c : gameStateDTO.getRegioni().get(1).getBalcone().getConsiglieri()) {
				ImageView imageView = new ImageView(mappaConsiglieri.get(c.toString()));
				imageView.setFitHeight(25);
				imageView.setPreserveRatio(true);
				balconi.get(1).getChildren().add(imageView);
			}

			balconi.get(1).setUserData(gameStateDTO.getRegioni().get(1).getBalcone());
			balconi.get(1).setDisable(true);

			balconi.get(2).getChildren().clear();
			for (ConsigliereDTO c : gameStateDTO.getRegioni().get(2).getBalcone().getConsiglieri()) {
				ImageView imageView = new ImageView(mappaConsiglieri.get(c.toString()));
				imageView.setFitHeight(25);
				imageView.setPreserveRatio(true);
				balconi.get(2).getChildren().add(imageView);
			}

			balconi.get(2).setUserData(gameStateDTO.getRegioni().get(2).getBalcone());
			balconi.get(2).setDisable(true);

			balconi.get(3).getChildren().clear();
			for (ConsigliereDTO c : gameStateDTO.getPlanciaReDTO().getBalconeRe().getConsiglieri()) {
				ImageView imageView = new ImageView(mappaConsiglieri.get(c.toString()));
				imageView.setFitHeight(25);
				imageView.setPreserveRatio(true);
				balconi.get(3).getChildren().add(imageView);
			}

			balconi.get(3).setUserData(gameStateDTO.getPlanciaReDTO().getBalconeRe());
			balconi.get(3).setDisable(true);
		};
		Platform.runLater(runnable);
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		Runnable runnable = () -> controller.getMessage().appendText(messaggio);
		Platform.runLater(runnable);
	}
	
	@Override
	public void mostraMessaggioMarket(String messaggio){
		Runnable runnable = () ->controllerMarket.getMessage().appendText(messaggio);
		Platform.runLater(runnable);
	}

	@Override
	public void mostraGiocatoreMarket(GiocatoreDTO giocatore) {
		Runnable runnable = () -> {

			HBox tesserePermesso = controllerMarket.getTesserePermesso();
			HBox aiutanti = controllerMarket.getAiutanti();
			HBox cartePolitica = controllerMarket.getCartePolitica();
			System.out.println("carte: " + cartePolitica);
			Map<String, Image> carte = controller.getMappaCartePolitica();
			Map<String, Image> tessere = controller.getMappaTesserePermesso();
			Map<String, Image> bonus = controller.getMappaBonus();

			cartePolitica.getChildren().clear();
			for (CartaPoliticaDTO c : giocatore.getCartePolitica()) {
				ImageView image = new ImageView();
				image.setFitHeight(60);
				image.setPreserveRatio(true);
				image.setImage(carte.get(c.toString()));
				image.setDisable(true);
				image.setUserData(c);
				image.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						controllerMarket.handleOfferta(event);
					}
				});
				cartePolitica.getChildren().add(image);
			}

			aiutanti.getChildren().clear();
			for (int i = 0; i < giocatore.getAiutanti(); i++) {
				ImageView image = new ImageView();
				image.setFitHeight(60);
				image.setPreserveRatio(true);
				image.setImage(bonus.get("Aiutante"));
				image.setDisable(true);
				image.setUserData(new AiutanteDTO(1));
				image.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						controllerMarket.handleOfferta(event);
					}
				});
				aiutanti.getChildren().add(image);
			}

			tesserePermesso.getChildren().clear();
			for (TesseraPermessoDTO t : giocatore.getTesserePermesso()) {
				ImageView image = new ImageView();
				image.setFitHeight(60);
				image.setPreserveRatio(true);
				image.setImage(tessere.get(t.toString()));
				image.setDisable(true);
				image.setUserData(t);
				EventHandler<Event> onMouseClicked = (event) -> {
					controllerMarket.handleOfferta(event);
				};
				image.setOnMouseClicked(onMouseClicked);
				tesserePermesso.getChildren().add(image);
			}
		};
		Platform.runLater(runnable);
	}

	@Override
	public void mostraOfferte(List<OffertaDTO> offerte) {
		Runnable runnable = () -> {
			VBox vbox = controllerMarket.getOfferte();
			vbox.getChildren().clear();
			for (OffertaDTO o : offerte) {
				Pane pane = stampaOfferta(o, o.getGiocatoreDTO().getNome(), o.getMarketableDTO(), o.getPrezzo());
				vbox.setMargin(pane, new Insets(5, 0, 0, 3));
				vbox.getChildren().add(pane);
			}
		};
		Platform.runLater(runnable);
	}

	private Pane stampaOfferta(OffertaDTO offerta, String giocatore, MarketableDTO oggetto, int prezzo) {
		Map<String, Image> tesserePermesso = controller.getMappaTesserePermesso();
		Map<String, Image> aiutante = controller.getMappaBonus();
		Map<String, Image> cartePolitica = controller.getMappaCartePolitica();

		Pane pane = new Pane();
		HBox hbox = new HBox();
		Text nome = new Text();
		nome.setText(giocatore);
		nome.setWrappingWidth(90);
		ImageView imageview = new ImageView();
		Image image = null;
		imageview.setFitHeight(50);
		imageview.setPreserveRatio(true);

		if (oggetto instanceof AiutanteDTO) {
			image = aiutante.get("Aiutante");
		}
		if (oggetto instanceof TesseraPermessoDTO) {
			image = tesserePermesso.get(oggetto.toString());
		}
		if (oggetto instanceof CartaPoliticaDTO) {
			image = cartePolitica.get(oggetto.toString());
		}

		imageview.setImage(image);
		Button soldi = new Button();
		soldi.setText(Integer.toString(prezzo));
		soldi.setUserData(offerta);
		soldi.setDisable(true);
		soldi.setPrefWidth(50);
		EventHandler<ActionEvent> onAction = (event) -> {
			controllerMarket.handleAcquisto(event);
		};
		soldi.setOnAction(onAction);
		hbox.setMargin(nome, new Insets(16, 0, 0, 0));
		hbox.setMargin(soldi, new Insets(4, 0, 0, 0));
		hbox.getChildren().add(nome);
		hbox.getChildren().add(soldi);
		hbox.getChildren().add(imageview);
		hbox.setSpacing(60);
		pane.getChildren().add(hbox);
		return pane;
	}

	@Override
	public void startMarket() {
		Runnable runnable = () -> {
			FXMLLoader fxmloader = new FXMLLoader();
			fxmloader.setLocation(getClass().getClassLoader().getResource("client/grafica/gui/fxml/market.fxml"));

			Parent root = null;
			try {
				root = fxmloader.load();
			} catch (IOException e) {
				log.log(Level.SEVERE, "Errore nel caricamento del file market.fxml", e);
			}
			Scene theScene = new Scene(root);
			market = new Stage();
			controllerMarket = fxmloader.getController();
			controllerMarket.setGameStateDTO(gameStateDTO);
			controllerMarket.setGui(GUI.this);
			controllerMarket.inizializza();
			market.setScene(theScene);
			market.show();
		};
		Platform.runLater(runnable);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void mostraAvversario(GiocatoreDTO avversario) {
		Runnable runnable = () -> {
			TabPane giocatori = controller.getAvversari();
			Tab tab = null;

			if (!tabAvversari.containsKey(avversario)) {
				tab = new Tab();
				tab.setText(avversario.getNome());
				giocatori.getTabs().add(tab);
				tabAvversari.put(avversario, tab);
			} else {
				tab = tabAvversari.get(avversario);
			}

			VBox vbox = new VBox();
			vbox.setSpacing(5);
			HBox hbox = new HBox();
			stampaPuntiAvversario(avversario, hbox, 40);

			vbox.getChildren().add(hbox);
			tab.setContent(vbox);

			HBox tesserePermesso = new HBox();
			stampaTesserePermesso(tesserePermesso, avversario.getTesserePermesso(),
					avversario.getTesserePermessoUsate().size(), 50, 1);
			vbox.getChildren().add(tesserePermesso);
		};

		Platform.runLater(runnable);
	}

	private void stampaPuntiAvversario(GiocatoreDTO avversario, HBox hbox, int heightImage) {
		Runnable runnable = () -> {
			Map<String, Image> mappaEmpori = controller.getMappaEmpori();
			Map<String, Image> mappaBonus = controller.getMappaBonus();
			hbox.getChildren().clear();
			hbox.setSpacing(15);
			hbox.setPadding(new Insets(5, 0, 0, 20));
			hbox.getChildren().add(stampaPuntoAvversario(mappaBonus.get("Punto"),
					Integer.toString(avversario.getPunteggioVittoria()), heightImage));
			hbox.getChildren().add(stampaPuntoAvversario(mappaBonus.get("Soldo"),
					Integer.toString(avversario.getPunteggioRicchezza()), heightImage));
			hbox.getChildren().add(stampaPuntoAvversario(mappaBonus.get("Aiutante"),
					Integer.toString(avversario.getAiutanti()), heightImage));
			hbox.getChildren().add(stampaPuntoAvversario(mappaEmpori.get(avversario.getColoreGiocatore().getColore()),
					Integer.toString(avversario.getEmpori()), heightImage));
			hbox.getChildren().add(stampaPuntoAvversario(mappaBonus.get("Nobiltà"),
					Integer.toString(avversario.getPunteggioNobiltà()), heightImage));
			hbox.getChildren().add(stampaPuntoAvversario(mappaBonus.get("BonusGiocatore"),
					Integer.toString(avversario.getTessereBonus()), heightImage));
		};

		Platform.runLater(runnable);
	}

	private Pane stampaPuntoAvversario(Image immagine, String punti, int heightImage) {
		Pane pane = new Pane();
		ImageView image = new ImageView();
		image.setImage(immagine);
		image.setPreserveRatio(true);
		image.setFitHeight(heightImage);
		Text text = new Text();
		text.setText(punti);
		text.relocate(image.getBoundsInParent().getWidth() / 4, heightImage / 3);
		pane.getChildren().add(image);
		pane.getChildren().add(text);
		text.setStyle("-fx-font: 17.0px Algerian; -fx-fill: white;");
		return pane;
	}

	@Override
	public ConsigliereDTO scegliConsigliere(List<ConsigliereDTO> consiglieri) {
		List<ImageView> riserva = controller.getConsiglieri();
		for (ImageView consigliere : riserva) {
			consigliere.setDisable(false);
			DropShadow ds = new DropShadow();
			ds.setColor(Color.web("#ffffff"));
			ds.setRadius(21);
			ds.setSpread(0.6);
			ds.setWidth(42.5);
			ds.setHeight(43.5);
			consigliere.setEffect(ds);
		}

		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli consigliere interrotto", e);
					Thread.currentThread().interrupt();
				}
			}
		}
		ConsigliereDTO consigliereDTO = (ConsigliereDTO) parametro;
		for (ImageView consigliere : riserva) {
			consigliere.setDisable(true);
			consigliere.setEffect(null);
			consigliere.setOpacity(1);
		}
		parametro = null;
		return consigliereDTO;

	}

	@Override
	public BalconeDTO scegliBalcone(List<RegioneDTO> regioni, BalconeDTO balconeRe) {
		List<HBox> balconi = controller.getBalconi();
		for (HBox balcone : balconi) {
			balcone.setDisable(false);
			for (ImageView i : controller.getFrecce()) {
				i.setEffect(new Glow(1));
			}
		}

		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli balcone interrotto", e);
					Thread.currentThread().interrupt();
				}
			}
			BalconeDTO balconeDTO = (BalconeDTO) parametro;
			for (HBox balcone : balconi) {
				balcone.setDisable(true);
				for (ImageView i : controller.getFrecce()) {
					i.setEffect(null);
				}
			}
			parametro = null;
			return balconeDTO;
		}
	}

	@Override
	public RegioneDTO scegliRegione(List<RegioneDTO> regioni) {
		List<ImageView> r = controller.getRegioni();
		DropShadow dp = new DropShadow();
		dp.setSpread(0.80);
		dp.setColor(Color.web("#fffefd"));
		for (ImageView i : r) {
			i.setDisable(false);
			i.setEffect(dp);
		}

		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli regione interrotto", e);
					Thread.currentThread().interrupt();
				}
			}
		}
		RegioneDTO regioneDTO = (RegioneDTO) parametro;
		List<ImageView> tessere;
		if ("Mare".equals(regioneDTO.getNome()))
			tessere = controller.getTessereMare();
		else if ("Collina".equals(regioneDTO.getNome()))
			tessere = controller.getTessereCollina();
		else
			tessere = controller.getTessereMontagna();

		for (ImageView i : tessere) {
			i.setDisable(false);
			i.setEffect(dp);
		}
		for (ImageView i : r) {
			i.setDisable(true);
			i.setEffect(null);
		}
		parametro = null;
		return regioneDTO;
	}

	@Override
	public TesseraPermessoDTO scegliTesseraRegione(List<TesseraPermessoDTO> tesserePermessoScoperte) {
		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli tessera regione interrotto", e);
				}
			}
		}
		TesseraPermessoDTO tesseraPermessoDTO = (TesseraPermessoDTO) parametro;
		List<ImageView> tessere = controller.getTesserePermessoRegioni();
		for (ImageView i : tessere) {
			i.setDisable(true);
			i.setEffect(null);
		}
		parametro = null;
		return tesseraPermessoDTO;
	}

	@Override
	public int scegliPrezzo() {

		controllerMarket.getPrezzo().setDisable(false);
		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli prezzo interrotto", e);
					Thread.currentThread().interrupt();
				}
			}
		}

		controllerMarket.getPrezzo().setDisable(true);
		int prezzo = Integer.parseInt((String) parametro);
		controllerMarket.getOk().setDisable(false);
		parametro = null;
		return prezzo;
	}

	@Override
	public CartaPoliticaDTO scegliCarta(List<CartaPoliticaDTO> cartePolitica) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CittàDTO scegliCittà(Set<? extends CittàDTO> città, ColoreDTO coloreGiocatore) {
		List<Pane> cittàCostruzione = controller.getCittàSenzaEmporio(città);
		for (Pane b : cittàCostruzione) {
			b.setDisable(false);
		}

		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli città interrotto", e);
					Thread.currentThread().interrupt();
				}
			}
		}

		CittàDTO cittàDTO = (CittàDTO) parametro;
		parametro = null;
		return cittàDTO;
	}

	@Override
	public TesseraPermessoDTO scegliTesseraGiocatore(List<TesseraPermessoDTO> list) {
		HBox tessere = controller.getTesserePermessoGiocatore();
		DropShadow ds = new DropShadow();
		ds.setColor(Color.web("#ffffff"));
		ds.setRadius(21);
		ds.setSpread(0.6);
		ds.setWidth(42.5);
		ds.setHeight(43.5);
		for (Node i : tessere.getChildren()) {
			i.setDisable(false);
			i.setEffect(ds);
		}

		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli tessera giocatore interrotto", e);
					Thread.currentThread().interrupt();
				}
			}
		}
		TesseraPermessoDTO tesseraPermessoDTO = (TesseraPermessoDTO) parametro;
		parametro = null;
		for (Node i : tessere.getChildren()) {
			i.setDisable(true);
			i.setEffect(null);
		}
		return tesseraPermessoDTO;
	}

	@Override
	public TesseraPermessoDTO scegliTesseraPermessoUsataONonUsata(List<TesseraPermessoDTO> tessere,
			List<TesseraPermessoDTO> tessereUsate) {
		HBox tessereGiocatore = controller.getTesserePermessoGiocatore();
		HBox tessereGiocatoreUsate = controller.getTesserePermessoGiocatoreUsate();
		DropShadow ds = new DropShadow();
		ds.setColor(Color.web("#ffffff"));
		ds.setRadius(21);
		ds.setSpread(0.6);
		ds.setWidth(42.5);
		ds.setHeight(43.5);
		for (Node i : tessereGiocatore.getChildren()) {
			i.setDisable(false);
			i.setEffect(ds);
		}

		for (Node i : tessereGiocatoreUsate.getChildren()) {
			i.setDisable(false);
			i.setEffect(ds);
		}

		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli tessere permesso azione bonus interrotto", e);
					Thread.currentThread().interrupt();
				}
			}
		}
		TesseraPermessoDTO tesseraPermessoDTO = (TesseraPermessoDTO) parametro;
		parametro = null;
		for (Node i : tessereGiocatore.getChildren()) {
			i.setDisable(true);
			i.setEffect(null);
		}
		for (Node i : tessereGiocatoreUsate.getChildren()) {
			i.setDisable(true);
			i.setEffect(ds);
		}
		return tesseraPermessoDTO;
	}

	@Override
	public List<CartaPoliticaDTO> scegliCarte(List<CartaPoliticaDTO> carteGiocatore) {
		List<CartaPoliticaDTO> carte = new ArrayList<>();
		List<ImageView> cartePolitica = this.controller.getCartePolitica();
		for (ImageView i : cartePolitica) {
			i.setDisable(false);
			i.setEffect(new Glow(0.6));
		}
		while (carte.size() != 4) {
			synchronized (lock) {
				while (parametro == null) {

					try {
						lock.wait();
					} catch (InterruptedException e) {
						log.log(Level.SEVERE, "Thread scegli carte interrotto", e);
						Thread.currentThread().interrupt();
					}
				}

				if (isCarteInserite()) {
					parametro = null;
					setCarteInserite(false);
					break;
				}

				carte.add((CartaPoliticaDTO) parametro);

				DropShadow ds = new DropShadow();
				ds.setColor(Color.web("#ffffff"));
				ds.setRadius(21);
				ds.setSpread(0.76);
				ds.setWidth(27.07);
				ds.setHeight(27.07);
				if (carte.size() == 1) {
					controller.getConferma().setDisable(false);
					controller.getConferma().setEffect(ds);
				}

				parametro = null;
			}
		}
		for (ImageView i : cartePolitica) {
			i.setOpacity(1);
			i.setDisable(true);
			i.setEffect(null);
		}

		controller.getConferma().setDisable(true);
		controller.getConferma().setEffect(null);
		return carte;

	}

	@Override
	public MarketableDTO scegliMarketable() {
		HBox aiutanti = controllerMarket.getAiutanti();
		DropShadow ds = new DropShadow();
		ds.setColor(Color.web("#ffffff"));
		ds.setRadius(21);
		ds.setSpread(0.6);
		ds.setWidth(42.5);
		ds.setHeight(43.5);
		for (Node i : aiutanti.getChildren()) {
			i.setDisable(false);
			i.setEffect(ds);
		}
		HBox cartePolitica = controllerMarket.getCartePolitica();
		for (Node i : cartePolitica.getChildren()) {
			i.setDisable(false);
			i.setEffect(ds);
		}

		HBox tesserePermesso = controllerMarket.getTesserePermesso();
		for (Node i : tesserePermesso.getChildren()) {
			i.setDisable(false);
			i.setEffect(ds);
		}

		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli marketable interrotto", e);
					Thread.currentThread().interrupt();
				}
			}
		}

		for (Node i : aiutanti.getChildren()) {
			i.setDisable(true);
			i.setEffect(null);
		}

		for (Node i : cartePolitica.getChildren()) {
			i.setDisable(true);
			i.setEffect(null);
		}

		for (Node i : tesserePermesso.getChildren()) {
			i.setDisable(true);
			i.setEffect(null);
		}
		MarketableDTO marketableDTO = (MarketableDTO) parametro;
		parametro = null;
		return marketableDTO;

	}

	@Override
	public int scegliOfferta(List<OffertaDTO> offerte) {
		List<Node> offerteMarket = controllerMarket.getOfferte().getChildren();
		for (Node offerta : offerteMarket) {
			for (Node node : ((HBox) ((Pane) offerta).getChildren().get(0)).getChildren()) {
				if (node instanceof Button) {
					node.setDisable(false);
				}
			}
		}

		synchronized (lock) {
			while (parametro == null)
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli offerta interrotto", e);
					Thread.currentThread().interrupt();
				}
		}
		OffertaDTO offertaDTO = (OffertaDTO) parametro;
		parametro = null;
		for (Node offerta : offerteMarket) {
			for (Node node : ((HBox) ((Pane) offerta).getChildren().get(0)).getChildren()) {
				if (node instanceof Button) {
					node.setDisable(true);
				}
			}
		}
		return offerte.indexOf(offertaDTO) + 1;
	}


	@Override
	public List<CittàBonusDTO> scegliUnaCittà() {
		this.mostraMessaggio("Scegli una città");
		List<Pane> cittàBonus = controller.getCittàBonusConEmporio();
		for (Pane città : cittàBonus) {
			città.setDisable(false);
		}
		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli una città interrotto", e);
					Thread.currentThread().interrupt();
				}
			}
		}
		for (Pane città : cittàBonus) {
			città.setDisable(true);
		}
		CittàBonusDTO cittàBonusDTO = (CittàBonusDTO) parametro;
		parametro = null;
		return Arrays.asList(cittàBonusDTO);
	}

	@Override
	public List<CittàBonusDTO> scegliDueCittà() {
		List<Pane> dueCittàBonus = controller.getCittàBonusConEmporio();
		List<CittàBonusDTO> cittàBonusDTO = this.scegliUnaCittà();
		this.mostraMessaggio("Scegli un'altra città");
		for (Pane città : dueCittàBonus) {
			if (!((CittàDTO) città.getUserData()).getNome().equals(cittàBonusDTO.get(0).getNome()))
				città.setDisable(false);
		}
		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					log.log(Level.SEVERE, "Thread scegli due città interrotto", e);
					Thread.currentThread().interrupt();
				}
			}
		}
		for (Pane città : dueCittàBonus) {
			città.setDisable(true);
		}
		CittàBonusDTO città = (CittàBonusDTO) parametro;
		cittàBonusDTO.add(città);
		parametro = null;
		return cittàBonusDTO;
	}

	@Override
	public void fineMarket() {
		Runnable runnable = () -> market.close();
		Platform.runLater(runnable);

	}

	@Override
	public void scegliMappa() {
		Runnable runnable = () -> {
			FXMLLoader fxmloader = new FXMLLoader();
			fxmloader.setLocation(getClass().getClassLoader().getResource("client/grafica/gui/fxml/scegli_mappa.fxml"));

			Parent root = null;

			try {
				root = fxmloader.load();
			} catch (IOException e) {
				log.log(Level.SEVERE, "Errore nel caricamento del file scegli_mappa.fxml", e);
			}
			mappa = new Stage();
			controllerMappa = fxmloader.getController();
			controllerMappa.setGui(GUI.this);
			controllerMappa.inizializza();
			Scene scene = new Scene(root);
			Image image = new Image(getClass().getResource("css/cursore.png").toExternalForm());
			scene.setCursor(new ImageCursor(image));
			mappa.setScene(scene);
			mappa.show();

		};

		Platform.runLater(runnable);
	}

	public void closeSceltaMappa() {
		Runnable runnable = () -> mappa.close();
		Platform.runLater(runnable);
	}

	public void stopTimer() {
		if (timer != null) {
			task.cancel();
			timer.cancel();
		}
	}

	public void azioneNonValida(String header, String content) {
		Runnable runnable = () -> {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText(header);
			alert.setContentText(content);

			alert.showAndWait();
		};

		Platform.runLater(runnable);
	}

	public void close() {
		Runnable runnable = () -> {
			finestra.close();
			};
		Platform.runLater(runnable);
	}

}
