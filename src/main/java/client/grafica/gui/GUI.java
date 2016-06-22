package client.grafica.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import client.connessione.Connessione;
import client.grafica.Grafica;
import common.azioniDTO.AzioneDTO;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.view.clientNotify.ClientNotify;

public class GUI extends Application implements Grafica {

	private Connessione connessione;
	private GameStateDTO gameStateDTO;
	private GUIGameController controller;
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
	}

	@Override
	public void mostraGiocatore(GiocatoreDTO giocatoreDTO) {
		giocatoreDTO.getTesserePermesso().add(gameStateDTO.getRegioni().get(0).getTesserePermessoScoperte().get(0));
		stampaTesserePermesso(controller.getTesserePermessoGiocatore(), giocatoreDTO.getTesserePermesso(),
				giocatoreDTO.getTesserePermessoUsate().size(), 70);
		controller.mostraCartePolitica(giocatoreDTO.getCartePolitica());
		controller.mostraNomeGiocatore(giocatoreDTO.getNome());
		controller.mostraPuntiGiocatore(giocatoreDTO.getPunteggioVittoria());
		controller.mostraMoneteGiocatore(giocatoreDTO.getPunteggioRicchezza());
		controller.mostraAiutantiGiocatore(giocatoreDTO.getAiutanti());
		controller.mostraEmporiGiocatore(giocatoreDTO.getEmpori());
		controller.mostraTessereBonusGiocatore(giocatoreDTO.getTessereBonus());
		controller.mostraNobiltàGiocatore(giocatoreDTO.getPunteggioNobiltà());
	}

	/**
	 * 
	 * @param tesserePermesso
	 *            HBox where it stamp tessere
	 * @param tessereUsate
	 *            0 if not stamp, else the number of tessere permesso used
	 */
	private void stampaTesserePermesso(HBox tesserePermesso, List<TesseraPermessoDTO> tessere, int tessereUsate,
			int dimensione) {
		Platform.runLater(new Runnable() {
			Map<String, Image> mappaTessere = controller.getMappaTesserePermesso();

			@Override
			public void run() {
				if (tessereUsate == 0) {
					Pane pane = new Pane();
					ImageView used = new ImageView();
					Text text = new Text(Integer.toString(tessereUsate));
					used.setFitHeight(dimensione);
					used.setPreserveRatio(true);
					used.setImage(mappaTessere.get("TesseraPermesso"));
					tesserePermesso.getChildren().add(pane);
					pane.getChildren().add(used);
					pane.getChildren().add(text);
					text.relocate(25, 25);
				}

				for (TesseraPermessoDTO t : tessere) {
					ImageView image = new ImageView();
					image.setFitHeight(dimensione);
					image.setPreserveRatio(true);
					image.setImage(mappaTessere.get(t.toString()));
					tesserePermesso.getChildren().add(image);
				}
			}
		});

	}

	@Override
	public void mostraMessaggio(String messaggio) {
		controller.getMessage().appendText(messaggio);
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
//		controller.mostraAvversario(gameStateDTO.getAvversari());
		Application.setUserAgentStylesheet(getClass().getResource("/css/gameState.css").toExternalForm());
		Platform.runLater(new Runnable() {
			Map<String, Image> mappaTessere = controller.getMappaTesserePermesso();

			@Override
			public void run() {
				TabPane giocatori = controller.getAvversari();
				Tab tab = null;

				if (!tabAvversari.containsKey(avversario)) {
					tab = new Tab();

					tab.setStyle("background");
					tab.setText(avversario.getNome());
					giocatori.getTabs().add(tab);
					tabAvversari.put(avversario, tab);
					System.out.println("tabgiocatore non esistente l'ho creato");
				} else {
					tab = tabAvversari.get(avversario);
				}

				VBox vbox = new VBox();
				HBox hbox = new HBox();
				hbox.setSpacing(15);
				vbox.setSpacing(5);
				tab.setContent(vbox);

				vbox.getChildren().add(hbox);
				giocatori.getTabs().add(tab);
				hbox.setPadding(new Insets(5, 0, 0, 20));

				Pane pane1 = new Pane();
				ImageView puntiV = new ImageView();
				puntiV.setImage(new Image(getClass().getResource("css/Point.png").toExternalForm()));
				puntiV.setPreserveRatio(true);
				puntiV.setFitHeight(40);
				Text npuntiV = new Text();
				npuntiV.setText(Integer.toString(avversario.getPunteggioVittoria()));
				npuntiV.relocate(10, 13);
				pane1.getChildren().add(puntiV);
				pane1.getChildren().add(npuntiV);
				hbox.getChildren().add(pane1);

				Pane pane2 = new Pane();
				ImageView puntiR = new ImageView();
				puntiR.setImage(new Image(getClass().getResource("css/Coins.png").toExternalForm()));
				puntiR.setPreserveRatio(true);
				puntiR.setFitHeight(40);
				Text npuntiR = new Text();
				npuntiR.setText(Integer.toString(avversario.getPunteggioRicchezza()));
				npuntiR.relocate(10, 15);
				pane2.getChildren().add(puntiR);
				pane2.getChildren().add(npuntiR);
				hbox.getChildren().add(pane2);

				Pane pane3 = new Pane();
				ImageView aiutanti = new ImageView();
				aiutanti.setImage(new Image(getClass().getResource("css/Assistant.png").toExternalForm()));
				aiutanti.setPreserveRatio(true);
				aiutanti.setFitHeight(40);
				Text naiutanti = new Text();
				naiutanti.setText(Integer.toString(avversario.getAiutanti()));
				naiutanti.relocate(5, 13);
				pane3.getChildren().add(aiutanti);
				pane3.getChildren().add(naiutanti);
				hbox.getChildren().add(pane3);

				Pane pane4 = new Pane();
				ImageView emporio = new ImageView();
				emporio.setImage(new Image(getClass().getResource("css/Emporium.png").toExternalForm()));
				emporio.setPreserveRatio(true);
				emporio.setFitHeight(40);
				Text empori = new Text();
				empori.setText(Integer.toString(avversario.getEmpori()));
				empori.relocate(10, 15);
				pane4.getChildren().add(emporio);
				pane4.getChildren().add(empori);
				hbox.getChildren().add(pane4);

				Pane pane5 = new Pane();
				ImageView nobilty = new ImageView();
				nobilty.setImage(new Image(getClass().getResource("css/Nobility.png").toExternalForm()));
				nobilty.setPreserveRatio(true);
				nobilty.setFitHeight(40);
				Text nobiltyPoint = new Text();
				nobiltyPoint.setText(Integer.toString(avversario.getPunteggioNobiltà()));
				nobiltyPoint.relocate(10, 16);
				pane5.getChildren().add(nobilty);
				pane5.getChildren().add(nobiltyPoint);
				hbox.getChildren().add(pane5);

				Pane pane6 = new Pane();
				ImageView puntiBonus = new ImageView();
				puntiBonus.setImage(new Image(getClass().getResource("css/BonusGiocatori.png").toExternalForm()));
				puntiBonus.setPreserveRatio(true);
				puntiBonus.setFitHeight(40);
				Text npuntiBonus = new Text();
				npuntiBonus.setText(Integer.toString(avversario.getPunteggioNobiltà()));
				npuntiBonus.relocate(20, 13);
				pane6.getChildren().add(puntiBonus);
				pane6.getChildren().add(npuntiBonus);
				hbox.getChildren().add(pane6);

				HBox hbox1 = new HBox();
				stampaTesserePermesso(hbox1, avversario.getTesserePermesso(),
						avversario.getTesserePermessoUsate().size(), 50);
				vbox.getChildren().add(hbox1);
			}
		});
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
		List<ImageView> tessere=null;
		switch(regioneDTO.getNome()){
		case("Mare"):
			tessere=controller.getTessereMare();
		case("Collina"):
			tessere=controller.getTessereCollina();
		case("Montagna"):
			tessere=controller.getTessereMontagna();
		}
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
		List<ImageView> tessere=controller.getTesserePermessoRegioni();
		for(ImageView i: tessere){
			i.setDisable(true);
		}
		parametro = null;
		return tesseraPermessoDTO;
	}

	@Override
	public int scegliPrezzo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CartaPoliticaDTO scegliCarta(List<CartaPoliticaDTO> cartePolitica) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CittàDTO scegliCittà(Set<? extends CittàDTO> città, ColoreDTO coloreGiocatore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TesseraPermessoDTO scegliTesseraGiocatore(List<TesseraPermessoDTO> list) {
		// TODO Auto-generated method stub
		return null;
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

				if (isCarteInserite())
					break;

				carte.add((CartaPoliticaDTO) parametro);

				if (carte.size() == 1)
					controller.getConferma().setDisable(false);

				parametro = null;
			}
		}
		System.out.println("carte politica" + carte);
		return carte;

	}

	@Override
	public MarketableDTO scegliMarketable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int scegliOfferta(List<OffertaDTO> offerte) {
		// TODO Auto-generated method stub
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
