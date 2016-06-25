package client.grafica.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import client.connessione.Connessione;
import client.grafica.Grafica;
import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.CambioTesserePermessoDTO;
import common.azioniDTO.CostruzioneAiutoReDTO;
import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import common.azioniDTO.ElezioneConsigliereDTO;
import common.azioniDTO.ElezioneConsigliereVeloceDTO;
import common.azioniDTO.IngaggioAiutanteDTO;
import common.azioniDTO.PassaDTO;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.model.game.Aiutante;
import server.model.game.Giocatore;
import server.model.market.Offerta;
import server.view.clientNotify.ClientNotify;

public class GUI extends Application implements Grafica {

	private Connessione connessione;
	private GameStateDTO gameStateDTO;
	private GUIGameController controller;
	private GUIMarketController controllerMarket;
	private Stage finestra;
	private Object lock = new Object();
	private Object parametro;
	private boolean carteInserite = false;
	private Map<GiocatoreDTO, Tab> tabAvversari = new HashMap<>();

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
		try {
			controller.mostraTesserePermessoRegioni(gameStateDTO.getRegioni());
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller.mostraGettoni(new ArrayList<>(gameStateDTO.getCittà()));
		controller.mostraTessereBonus();
		controller.mostraConsiglieriBalcone();
		controller.mostraRiserva(gameStateDTO.getConsiglieri());
		controller.assegnaBottoniCittà(new ArrayList<>(gameStateDTO.getCittà()));
		assegnaRegione();
		assegnaAzioni();
		assegnaRe();
		controller.stampaEmporiCittà(new ArrayList<>(gameStateDTO.getCittà()));
	}

	private void assegnaRe() {
		System.out.println(controller.getRe());
	}

	private void assegnaAzioni() {
		List<Button> azioni=controller.getAzioni();
		azioni.get(0).setUserData(new ElezioneConsigliereDTO());
		azioni.get(1).setUserData(new AcquistoTesseraPermessoDTO());
		azioni.get(2).setUserData(new CostruzioneTesseraPermessoDTO());
		azioni.get(3).setUserData(new CostruzioneAiutoReDTO());
		azioni.get(4).setUserData(new IngaggioAiutanteDTO());
		azioni.get(5).setUserData(new CambioTesserePermessoDTO());
		azioni.get(6).setUserData(new ElezioneConsigliereVeloceDTO());
		azioni.get(7).setUserData(new SecondaAzionePrincipaleDTO());
		azioni.get(8).setUserData(new PassaDTO());
	}

	private void assegnaRegione() {
		List<ImageView> regioni=controller.getRegioni();
		for(int i=0; i<regioni.size(); i++){
			regioni.get(i).setUserData(gameStateDTO.getRegioni().get(i));
		}
	}

	@Override
	public void mostraGiocatore(GiocatoreDTO giocatoreDTO) {
		giocatoreDTO.getTesserePermesso().add(gameStateDTO.getRegioni().get(0).getTesserePermessoScoperte().get(0));
		stampaTesserePermesso(controller.getTesserePermessoGiocatore(), giocatoreDTO.getTesserePermesso(),
				giocatoreDTO.getTesserePermessoUsate().size(), 70);
		cartePolitica(giocatoreDTO.getCartePolitica());
		controller.mostraNomeGiocatore(giocatoreDTO.getNome());
		controller.mostraPuntiGiocatore(giocatoreDTO.getPunteggioVittoria());
		controller.mostraMoneteGiocatore(giocatoreDTO.getPunteggioRicchezza());
		controller.mostraAiutantiGiocatore(giocatoreDTO.getAiutanti());
		controller.mostraEmporiGiocatore(giocatoreDTO.getEmpori());
		controller.mostraTessereBonusGiocatore(giocatoreDTO.getTessereBonus());
		controller.mostraNobiltàGiocatore(giocatoreDTO.getPunteggioNobiltà());
	}
	
	private void cartePolitica(List<CartaPoliticaDTO> carte) {
		Platform.runLater(new Runnable() {
			Map<String, Image> mappaCarte=controller.getMappaCartePolitica();
			HBox cartePolitica=controller.getCartePoliticaGiocatore();
			
			@Override
			public void run() {
				cartePolitica.getChildren().clear();
				for (CartaPoliticaDTO c : carte) {

					ImageView image = new ImageView();
					image.setFitHeight(72);
					image.setPreserveRatio(true);
					image.setImage(mappaCarte.get(c.toString()));
					image.setDisable(true);
					image.setUserData(c);
					cartePolitica.getChildren().add(image);
					image.setOnMouseClicked(new EventHandler<Event>() {

						@Override
						public void handle(Event event) {
							controller.handleCartaPolitica(event);
						}
					});
				}

			}
		});
	}

	/**      
	 * @param tesserePermesso HBox where it stamp tessere
	 * @param tessere
	 * @param tessereUsate 0 if not stamp, else the number of tessere permesso used
	 * @param dimensione
	 */
	private void stampaTesserePermesso(HBox tesserePermesso, List<TesseraPermessoDTO> tessere, int tessereUsate,
			int dimensione) {
		Platform.runLater(new Runnable() {
			Map<String, Image> mappaTessere = controller.getMappaTesserePermesso();

			@Override
			public void run() {
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
					text.relocate(dimensione/2, dimensione/2);
					text.setStyle("-fx-font: 17.0px Algerian; -fx-fill: white;");
				}

				for (TesseraPermessoDTO t : tessere) {
					ImageView image = new ImageView();
					image.setFitHeight(dimensione);
					image.setPreserveRatio(true);
					image.setImage(mappaTessere.get(t.toString()));
					image.setOnMouseClicked(new EventHandler<Event>() {

						@Override
						public void handle(Event event) {
							controller.handleTesseraPermessoGiocatore(event);
						}
					});
					image.setDisable(true);
					image.setUserData(t);
					tesserePermesso.getChildren().add(image);
				}
			}
		});

	}
	
	public void stampaConsiglieriBalcone(){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				List<HBox> balconi=controller.getBalconi();
				Map<String, Image> mappaConsiglieri=controller.getMappaConsiglieri();
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
			}
		});
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		controller.getMessage().appendText("\n"+messaggio);
	}

	@Override
	public void mostraGiocatoreMarket(GiocatoreDTO giocatore){
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				
				HBox tesserePermesso=controllerMarket.getTesserePermesso();
				HBox aiutanti=controllerMarket.getAiutanti();
				HBox cartePolitica=controllerMarket.getCartePolitica();
				System.out.println("carte: "+cartePolitica);
				Map<String, Image> carte=controller.getMappaCartePolitica();
				Map<String, Image> tessere=controller.getMappaTesserePermesso();
				Map<String, Image> bonus=controller.getMappaBonus();
				
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
				for(int i=0; i<giocatore.getAiutanti();i++){
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
					image.setOnMouseClicked(new EventHandler<Event>() {

						@Override
						public void handle(Event event) {
							controllerMarket.handleOfferta(event);
						}
					});
					tesserePermesso.getChildren().add(image);
				}
			}
		});
	}
	
	@Override
	public void mostraOfferte(List<OffertaDTO> offerte) {
		
		Platform.runLater(new Runnable() {
			
			
			VBox vbox=controllerMarket.getOfferte();
			
			@Override
			public void run() {
				for(OffertaDTO o:offerte){
					vbox.getChildren().add(stampaOfferta(o.getGiocatoreDTO().getNome(), o.getMarketableDTO(), o.getPrezzo()));
				}
			}
		});
	}
	
	private HBox stampaOfferta(String giocatore, MarketableDTO oggetto, int prezzo){
		Map<String, Image> tesserePermesso=controller.getMappaTesserePermesso();
		Map<String, Image> aiutante=controller.getMappaBonus();
		Map<String, Image> cartePolitica=controller.getMappaCartePolitica();

		HBox hbox=new HBox();
		Text nome=new Text();
		nome.setText(giocatore);
		ImageView imageview=new ImageView();
		Image image=null;
		imageview.setFitHeight(50);
		imageview.setPreserveRatio(true);
	
		if(oggetto instanceof AiutanteDTO){
			image=aiutante.get("Aiutante");
		}
		if(oggetto instanceof TesseraPermessoDTO){
			image=tesserePermesso.get(oggetto.toString());
		}
		if(oggetto instanceof CartaPoliticaDTO){
			image=cartePolitica.get(oggetto.toString());
		}		
		
		imageview.setImage(image);
		Button soldi=new Button();
		soldi.setText(Integer.toString(prezzo));
				
		hbox.getChildren().add(nome);
		hbox.getChildren().add(imageview);
		hbox.getChildren().add(soldi);
		return hbox;
	}
	
	@Override
	public void startMarket() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				FXMLLoader fxmloader = new FXMLLoader();
				fxmloader.setLocation(getClass().getClassLoader().getResource("client/grafica/gui/fxml/market.fxml"));

				Parent root=null;
				try {
					root = fxmloader.load();
					Scene theScene = new Scene(root);
					Stage market=new Stage();
					controllerMarket = fxmloader.getController();
					controllerMarket.setGameStateDTO(gameStateDTO);
					controllerMarket.setGui(GUI.this);
					controllerMarket.inizializza();
					market.setScene(theScene);
					market.show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
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
		Platform.runLater(new Runnable() {
			Map<String, Image> mappaTessere = controller.getMappaTesserePermesso();

			@Override
			public void run() {
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
				HBox hbox=stampaPuntiAvversario(avversario);
				
				vbox.getChildren().add(hbox);
				tab.setContent(vbox);
//				tab.setStyle("-fx-background-color: red;-fx-alignment: CENTER;-fx-text-fill: white;-fx-font-size: 12px;-fx-font-weight: bold;");
								
				HBox tesserePermesso = new HBox();
				stampaTesserePermesso(tesserePermesso, avversario.getTesserePermesso(),
						avversario.getTesserePermessoUsate().size(), 50);
				vbox.getChildren().add(tesserePermesso);
			}
		});
	}
	
	private HBox stampaPuntiAvversario(GiocatoreDTO avversario){
		HBox hbox = new HBox();
		hbox.setSpacing(15);
		hbox.setPadding(new Insets(5, 0, 0, 20));	
		hbox.getChildren().add(stampaPuntoAvversario("css/Point.png", Integer.toString(avversario.getPunteggioVittoria()),10));
		hbox.getChildren().add(stampaPuntoAvversario("css/Coins.png", Integer.toString(avversario.getPunteggioRicchezza()),10));
		hbox.getChildren().add(stampaPuntoAvversario("css/Assistant.png", Integer.toString(avversario.getAiutanti()),4));
		hbox.getChildren().add(stampaPuntoAvversario("css/Emporium.png", Integer.toString(avversario.getEmpori()),10));
		hbox.getChildren().add(stampaPuntoAvversario("css/Nobility.png", Integer.toString(avversario.getPunteggioNobiltà()),10));
		hbox.getChildren().add(stampaPuntoAvversario("css/BonusGiocatori.png", Integer.toString(avversario.getTessereBonus()),10));
		return hbox;
	}
	
	private Pane stampaPuntoAvversario(String immagine, String punti, int relocateH){
		Pane pane = new Pane();
		ImageView image = new ImageView();
		image.setImage(new Image(getClass().getResource(immagine).toExternalForm()));
		image.setPreserveRatio(true);
		image.setFitHeight(40);
		Text text = new Text();
		text.setText(punti);
		text.relocate(relocateH, 13);
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
		}

		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			ConsigliereDTO consigliereDTO = (ConsigliereDTO) parametro;
			for (ImageView consigliere : riserva) {
				consigliere.setDisable(true);
				consigliere.setOpacity(1);
			}
			parametro = null;
			return consigliereDTO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public BalconeDTO scegliBalcone(List<RegioneDTO> regioni, BalconeDTO balconeRe) {
		List<HBox> balconi = controller.getBalconi();
		for (HBox balcone : balconi) {
			balcone.setDisable(false);
		}

		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			BalconeDTO balconeDTO = (BalconeDTO) parametro;
			for (HBox balcone : balconi) {
				balcone.setDisable(true);
			}
			parametro = null;
			return balconeDTO;
		}
	}

	@Override
	public RegioneDTO scegliRegione(List<RegioneDTO> regioni) {
		List<ImageView> r = controller.getRegioni();
		for (ImageView i : r) {
			i.setDisable(false);
		}

		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		RegioneDTO regioneDTO=(RegioneDTO) parametro;
		System.out.println(regioneDTO.getNome());
		List<ImageView> tessere=null;
		if("Mare".equals(regioneDTO.getNome()))
			tessere=controller.getTessereMare();
		else if("Collina".equals(regioneDTO.getNome()))
			tessere=controller.getTessereCollina();
		else
			tessere=controller.getTessereMontagna();
		
		for(ImageView i: tessere){
			i.setDisable(false);
		}
		for(ImageView i: r){
			i.setDisable(true);
		}
		parametro=null;
		return regioneDTO;
	}

	@Override
	public TesseraPermessoDTO scegliTesseraRegione(List<TesseraPermessoDTO> tesserePermessoScoperte) {
		synchronized (lock) {
			while (parametro == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		TesseraPermessoDTO tesseraPermessoDTO = (TesseraPermessoDTO) parametro;
		System.out.println(tesseraPermessoDTO);
		List<ImageView> tessere=controller.getTesserePermessoRegioni();
		for(ImageView i: tessere){
			i.setDisable(true);
		}
		parametro = null;
		return tesseraPermessoDTO;
	}

	@Override
	public int scegliPrezzo() {
		
		controllerMarket.getPrezzo().setDisable(false);
		synchronized (lock) {
			while(parametro==null){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		controllerMarket.getPrezzo().setDisable(true);
		int prezzo=Integer.parseInt((String) parametro);
		controllerMarket.getOk().setDisable(false);
		parametro=null;
		return prezzo;
	}

	@Override
	public CartaPoliticaDTO scegliCarta(List<CartaPoliticaDTO> cartePolitica) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CittàDTO scegliCittà(Set<? extends CittàDTO> città, ColoreDTO coloreGiocatore) {
		List<Button> cittàCostruzione=controller.getCittàSenzaEmporio(città);
		for(Button b: cittàCostruzione)
			b.setDisable(false);
		
		synchronized (lock) {
			while(parametro==null){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		CittàDTO cittàDTO=(CittàDTO) parametro;
		parametro=null;
		return cittàDTO;
	}

	@Override
	public TesseraPermessoDTO scegliTesseraGiocatore(List<TesseraPermessoDTO> list) {
		HBox tessere=controller.getTesserePermessoGiocatore();
		for(Node i:tessere.getChildren()){
			i.setDisable(false);
		}
		
		synchronized(lock){
			while(parametro==null){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		TesseraPermessoDTO tesseraPermessoDTO=(TesseraPermessoDTO) parametro;
		parametro=null;
		for(Node i: tessere.getChildren()){
			i.setDisable(true);
		}
		return tesseraPermessoDTO;
	}

	@Override
	public List<CartaPoliticaDTO> scegliCarte(List<CartaPoliticaDTO> carteGiocatore) {
		List<CartaPoliticaDTO> carte = new ArrayList<>();
		List<ImageView> cartePolitica = this.controller.getCartePolitica();
		for (ImageView i : cartePolitica)
			i.setDisable(false);
		while (cartePolitica.size() != 4) {
			synchronized (lock) {
				while (parametro == null) {

					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (isCarteInserite()){
					parametro = null;
					break;
				}

				carte.add((CartaPoliticaDTO) parametro);

				if (carte.size() == 1)
					controller.getConferma().setDisable(false);

				parametro = null;
			}
		}
		for(ImageView i: cartePolitica)
			i.setOpacity(1);
		controller.getConferma().setDisable(true);
		System.out.println("carte politica" + carte);
		return carte;

	}

	@Override
	public MarketableDTO scegliMarketable() {
		HBox aiutanti=controllerMarket.getAiutanti();
		for(Node i: aiutanti.getChildren()){
			i.setDisable(false);
		}
		HBox cartePolitica=controllerMarket.getCartePolitica();
		for(Node i: cartePolitica.getChildren()){
			i.setDisable(false);
		}
		
		HBox tesserePermesso=controllerMarket.getTesserePermesso();
		for(Node i: tesserePermesso.getChildren()){
			i.setDisable(false);
		}
		
		synchronized (lock) {
			while(parametro==null){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		MarketableDTO marketableDTO=(MarketableDTO) parametro;
		parametro=null;
		return marketableDTO;
		
	}

	@Override
	public int scegliOfferta(List<OffertaDTO> offerte) {
		return 0;
	}

	@Override
	public CittàDTO scegliCittàBonus(Set<CittàBonusDTO> città, ColoreDTO coloreGiocatore, String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int scegliUsataONonUsata() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CittàBonusDTO> scegliUnaCittà() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CittàBonusDTO> scegliDueCittà() {
		// TODO Auto-generated method stub
		return null;
	}


}
