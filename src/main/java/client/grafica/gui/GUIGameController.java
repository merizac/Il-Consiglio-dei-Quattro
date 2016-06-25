package client.grafica.gui;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.AzioneParametri;
import common.azioniDTO.CambioTesserePermessoDTO;
import common.azioniDTO.ChatDTO;
import common.azioniDTO.CostruzioneAiutoReDTO;
import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import common.azioniDTO.ElezioneConsigliereDTO;
import common.azioniDTO.ElezioneConsigliereVeloceDTO;
import common.azioniDTO.IngaggioAiutanteDTO;
import common.azioniDTO.PassaDTO;
import common.azioniDTO.PescaCartaDTO;
import common.azioniDTO.SecondaAzionePrincipaleDTO;
import common.gameDTO.CittàBonusDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import server.model.game.Emporio;
import utility.AzioneNonEseguibile;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class GUIGameController {

	private GameStateDTO gameStateDTO;
	private GUI gui;
	private Map<String, Image> mappaCarte = new HashMap<>();
	private Map<String, Image> mappaTessere = new HashMap<>();
	private Map<String, Image> mappaGettoni = new HashMap<>();
	private Map<String, Image> mappaConsiglieri = new HashMap<>();
	private Map<String, Image> mappaConsiglieriRiserva = new HashMap<>();
	private Map<String, Image> mappaBonus=new HashMap<>();
	private Map<String, Image> mappaEmpori=new HashMap<>();
	
	@FXML
	private HBox tesserePermesso;
	@FXML
	private HBox cartePolitica;
	@FXML
	private Text nomeGiocatore;
	@FXML
	private Text puntiGiocatore;
	@FXML
	private Text aiutantiGiocatore;
	@FXML
	private Text moneteGiocatore;
	@FXML
	private Text emporiGiocatore;
	@FXML
	private Text tesserebonusGiocatore;
	@FXML
	private Text nobiltàGiocatore;
	@FXML
	private ImageView tesseraMare1;
	@FXML
	private ImageView tesseraMare2;
	@FXML
	private ImageView tesseraCollina1;
	@FXML
	private ImageView tesseraCollina2;
	@FXML
	private ImageView tesseraMontagna1;
	@FXML
	private ImageView tesseraMontagna2;
	@FXML
	private TabPane giocatori;

	@FXML
	private ImageView consigliere1;
	@FXML
	private ImageView consigliere2;
	@FXML
	private ImageView consigliere3;
	@FXML
	private ImageView consigliere4;
	@FXML
	private ImageView consigliere5;
	@FXML
	private ImageView consigliere6;
	@FXML
	private ImageView consigliere7;
	@FXML
	private ImageView consigliere8;

	@FXML
	private Button conferma;
	@FXML
	private Button passa;
	@FXML
	private Button pescaCarta;
	@FXML
	private Button elezioneConsigliere;
	@FXML
	private Button acquistoTesseraPermesso;
	@FXML
	private Button costruzioneAiutoRe;
	@FXML
	private Button costruzioneTesseraPermesso;
	@FXML
	private Button cambioTesseraPermesso;
	@FXML
	private Button elezioneConsigliereVeloce;
	@FXML
	private Button ingaggioAiutante;
	@FXML
	private Button secondaAzionePrincipale;

	@FXML
	private ImageView regioneMare;
	@FXML
	private ImageView regioneCollina;
	@FXML
	private ImageView regioneMontagna;

	@FXML
	private HBox balconeMare;
	@FXML
	private HBox balconeCollina;
	@FXML
	private HBox balconeMontagna;
	@FXML
	private HBox balconeRe;

	@FXML
	private ImageView gettoneArkon;
	@FXML
	private ImageView gettoneBurgen;
	@FXML
	private ImageView gettoneCastrum;
	@FXML
	private ImageView gettoneDorful;
	@FXML
	private ImageView gettoneEsti;
	@FXML
	private ImageView gettoneFramek;
	@FXML
	private ImageView gettoneIndur;
	@FXML
	private ImageView gettoneGraden;
	@FXML
	private ImageView gettoneJuvelar;
	@FXML
	private ImageView gettoneHellar;
	@FXML
	private ImageView gettoneKultos;
	@FXML
	private ImageView gettoneNaris;
	@FXML
	private ImageView gettoneLyram;
	@FXML
	private ImageView gettoneOsium;
	@FXML
	private ImageView gettoneMerkatim;
	@FXML
	private HBox emporiArkon;
	@FXML
	private HBox emporiBurgen;
	@FXML
	private HBox emporiCastrum;
	@FXML
	private HBox emporiDorful;
	@FXML
	private HBox emporiEsti;
	@FXML
	private HBox emporiFramek;
	@FXML
	private HBox emporiIndur;
	@FXML
	private HBox emporiGraden;
	@FXML
	private HBox emporiJuvelar;
	@FXML
	private HBox emporiHellar;
	@FXML
	private HBox emporiKultos;
	@FXML
	private HBox emporiNaris;
	@FXML
	private HBox emporiLyram;
	@FXML
	private HBox emporiOsium;
	@FXML
	private HBox emporiMerkatim;
	@FXML
	private Button quit;
	@FXML
	private Button arkon;
	@FXML
	private Button burgen;
	@FXML
	private Button castrum;
	@FXML
	private Button dorful;
	@FXML
	private Button esti;
	@FXML
	private Button framek;
	@FXML
	private Button indur;
	@FXML
	private Button graden;
	@FXML
	private Button juvelar;
	@FXML
	private Button hellar;
	@FXML
	private Button kultos;
	@FXML
	private Button naris;
	@FXML
	private Button lyram;
	@FXML
	private Button osium;
	@FXML
	private Button merkatim;
	@FXML
	private List<Button> città = new ArrayList<>();

	@FXML
	private ImageView mare;
	@FXML
	private ImageView collina;
	@FXML
	private ImageView montagna;
	@FXML
	private ImageView oro;
	@FXML
	private ImageView argento;
	@FXML
	private ImageView bronzo;
	@FXML
	private ImageView ferro;
	@FXML
	private ImageView king;

	@FXML
	private TextArea message;
	@FXML
	private TextField chat;

	public GUIGameController() {
		this.mappaEmpori.put("0", 
				new Image(getClass().getResource("css/empori/1.png").toExternalForm()));
		this.mappaEmpori.put("1", 
				new Image(getClass().getResource("css/empori/2.png").toExternalForm()));
		this.mappaEmpori.put("2", 
				new Image(getClass().getResource("css/empori/3.png").toExternalForm()));
		this.mappaEmpori.put("3", 
				new Image(getClass().getResource("css/empori/4.png").toExternalForm()));
		
		this.mappaBonus.put("Aiutante", 
				new Image(getClass().getResource("css/Assistant.png").toExternalForm()));
		
		this.mappaConsiglieri.put("Arancione",
				new Image(getClass().getResource("css/consiglieri/Arancione.png").toExternalForm()));
		this.mappaConsiglieri.put("Azzurro",
				new Image(getClass().getResource("css/consiglieri/Azzurro.png").toExternalForm()));
		this.mappaConsiglieri.put("Bianco",
				new Image(getClass().getResource("css/consiglieri/Bianco.png").toExternalForm()));
		this.mappaConsiglieri.put("Nero",
				new Image(getClass().getResource("css/consiglieri/Nero.png").toExternalForm()));
		this.mappaConsiglieri.put("Rosa",
				new Image(getClass().getResource("css/consiglieri/Rosa.png").toExternalForm()));
		this.mappaConsiglieri.put("Viola",
				new Image(getClass().getResource("css/consiglieri/Viola.png").toExternalForm()));

		this.mappaConsiglieriRiserva.put("Arancione",
				new Image(getClass().getResource("css/consiglieriRiserva/Arancione.png").toExternalForm()));
		this.mappaConsiglieriRiserva.put("Azzurro",
				new Image(getClass().getResource("css/consiglieriRiserva/Azzurro.png").toExternalForm()));
		this.mappaConsiglieriRiserva.put("Bianco",
				new Image(getClass().getResource("css/consiglieriRiserva/Bianco.png").toExternalForm()));
		this.mappaConsiglieriRiserva.put("Nero",
				new Image(getClass().getResource("css/consiglieriRiserva/Nero.png").toExternalForm()));
		this.mappaConsiglieriRiserva.put("Rosa",
				new Image(getClass().getResource("css/consiglieriRiserva/Rosa.png").toExternalForm()));
		this.mappaConsiglieriRiserva.put("Viola",
				new Image(getClass().getResource("css/consiglieriRiserva/Viola.png").toExternalForm()));

		this.mappaGettoni.put("[BonusPuntiNobiltà 1]",
				new Image(getClass().getResource("css/gettoni/15.png").toExternalForm()));
		this.mappaGettoni.put("[BonusPuntiNobiltà 1]",
				new Image(getClass().getResource("css/gettoni/1.png").toExternalForm()));
		this.mappaGettoni.put("[BonusCartePolitica 1]",
				new Image(getClass().getResource("css/gettoni/7.png").toExternalForm()));
		this.mappaGettoni.put("[BonusPuntiVittoria 1]",
				new Image(getClass().getResource("css/gettoni/14.png").toExternalForm()));
		this.mappaGettoni.put("[BonusPuntiVittoria 2]",
				new Image(getClass().getResource("css/gettoni/6.png").toExternalForm()));
		this.mappaGettoni.put("[BonusPuntiVittoria 3]",
				new Image(getClass().getResource("css/gettoni/3.png").toExternalForm()));
		this.mappaGettoni.put("[BonusMoneta 3]",
				new Image(getClass().getResource("css/gettoni/10.png").toExternalForm()));
		this.mappaGettoni.put("[BonusMoneta 2]",
				new Image(getClass().getResource("css/gettoni/12.png").toExternalForm()));
		this.mappaGettoni.put("[BonusMoneta 1]",
				new Image(getClass().getResource("css/gettoni/5.png").toExternalForm()));
		this.mappaGettoni.put("[BonusMoneta 1, BonusAiutanti 1]",
				new Image(getClass().getResource("css/gettoni/13.png").toExternalForm()));
		this.mappaGettoni.put("[BonusAiutanti 2]",
				new Image(getClass().getResource("css/gettoni/2.png").toExternalForm()));
		this.mappaGettoni.put("[BonusAiutanti 1]",
				new Image(getClass().getResource("css/gettoni/11.png").toExternalForm()));
		this.mappaGettoni.put("[BonusAiutanti 1, BonusCartePolitica 1]",
				new Image(getClass().getResource("css/gettoni/4.png").toExternalForm()));
		this.mappaGettoni.put("[BonusCartePolitica 1, BonusPuntiVittoria 1]",
				new Image(getClass().getResource("css/gettoni/9.png").toExternalForm()));

		this.mappaCarte.put("Bianco", new Image(getClass().getResource("css/politicCard/Bianco.png").toExternalForm()));
		this.mappaCarte.put("Nero", new Image(getClass().getResource("css/politicCard/Nero.png").toExternalForm()));
		this.mappaCarte.put("Viola", new Image(getClass().getResource("css/politicCard/Viola.png").toExternalForm()));
		this.mappaCarte.put("Rosa", new Image(getClass().getResource("css/politicCard/Rosa.png").toExternalForm()));
		this.mappaCarte.put("Arancione",
				new Image(getClass().getResource("css/politicCard/Arancione.png").toExternalForm()));
		this.mappaCarte.put("Azzurro",
				new Image(getClass().getResource("css/politicCard/Azzurro.png").toExternalForm()));
		this.mappaCarte.put("Multicolore",
				new Image(getClass().getResource("css/politicCard/Multicolore.png").toExternalForm()));

		// mare
		this.mappaTessere.put("TesseraPermesso  città:[Arkon, Burgen, Esti], bonus:[BonusMoneta 2]",
				new Image(getClass().getResource("css/permitTile/3.11.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Arkon, Burgen, Castrum], bonus:[BonusAiutanti 2]",
				new Image(getClass().getResource("css/permitTile/3.12.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Arkon], bonus:[BonusMoneta 3, BonusPuntiVittoria 4]",
				new Image(getClass().getResource("css/permitTile/3.6.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Esti], bonus:[BonusAzionePrincipale, BonusMoneta 2]",
				new Image(getClass().getResource("css/permitTile/3.15.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Castrum], bonus:[BonusAiutanti 2, BonusMoneta 3]",
				new Image(getClass().getResource("css/permitTile/3.7.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Dorful], bonus:[BonusPuntiVittoria 7]",
				new Image(getClass().getResource("css/permitTile/3.8.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Burgen], bonus:[BonusCartePolitica 3, BonusAiutanti 1]",
				new Image(getClass().getResource("css/permitTile/3.2.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Arkon, Esti], bonus:[BonusPuntiNobiltà 2]",
				new Image(getClass().getResource("css/permitTile/3.10.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Dorful, Esti], bonus:[BonusPuntiVittoria 2, BonusAiutanti 1]",
				new Image(getClass().getResource("css/permitTile/3.3.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Arkon, Burgen], bonus:[BonusMoneta 3, BonusCartePolitica 1]",
				new Image(getClass().getResource("css/permitTile/3.9.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Arkon, Dorful, Esti], bonus:[BonusMoneta 1, BonusPuntiVittoria 2]",
				new Image(getClass().getResource("css/permitTile/3.1.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Castrum, Dorful], bonus:[BonusPuntiVittoria 3, BonusAiutanti 1]",
				new Image(getClass().getResource("css/permitTile/3.5.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Burgen, Castrum, Dorful], bonus:[BonusAiutanti 1, BonusPuntiVittoria 1]",
				new Image(getClass().getResource("css/permitTile/3.13.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Castrum, Dorful, Esti], bonus:[BonusCartePolitica 1, BonusPuntiNobiltà 1]",
				new Image(getClass().getResource("css/permitTile/3.14.png").toExternalForm()));
		// collina
		this.mappaTessere.put("TesseraPermesso  città:[Hellar], bonus:[BonusAiutanti 4]",
				new Image(getClass().getResource("css/permitTile/1.6.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Framek, Juvelar], bonus:[BonusAiutanti 2, BonusMoneta 1]",
				new Image(getClass().getResource("css/permitTile/1.8.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Indur], bonus:[BonusCartePolitica 2, BonusAiutanti 2]",
				new Image(getClass().getResource("css/permitTile/1.10.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Juvelar], bonus:[BonusAzionePrincipale, BonusPuntiVittoria 2]",
				new Image(getClass().getResource("css/permitTile/1.11.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Framek, Graden], bonus:[BonusMoneta 5]",
				new Image(getClass().getResource("css/permitTile/1.9.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Graden, Hellar], bonus:[BonusAiutanti 3]",
				new Image(getClass().getResource("css/permitTile/1.15.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Indur, Juvelar], bonus:[BonusCartePolitica 2, BonusPuntiNobiltà 1]",
				new Image(getClass().getResource("css/permitTile/1.13.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Framek], bonus:[BonusCartePolitica 2, BonusMoneta 4]",
				new Image(getClass().getResource("css/permitTile/1.7.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Graden, Hellar, Indur], bonus:[BonusCartePolitica 1, BonusAiutanti 1]",
				new Image(getClass().getResource("css/permitTile/1.5.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Graden], bonus:[BonusAiutanti 2, BonusPuntiVittoria 2, BonusPuntiNobiltà 1]",
				new Image(getClass().getResource("css/permitTile/1.3.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Hellar, Indur], bonus:[BonusPuntiVittoria 5]",
				new Image(getClass().getResource("css/permitTile/1.2.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Framek, Indur, Juvelar], bonus:[BonusAiutanti 1, BonusPuntiNobiltà 1]",
				new Image(getClass().getResource("css/permitTile/1.4.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Framek, Graden, Hellar], bonus:[BonusPuntiNobiltà 1, BonusMoneta 1]",
				new Image(getClass().getResource("css/permitTile/1.1.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Framek, Graden, Juvelar], bonus:[BonusMoneta 2, BonusPuntiVittoria 1]",
				new Image(getClass().getResource("css/permitTile/1.14.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Indur, Juvelar, Hellar], bonus:[BonusCartePolitica 1]",
				new Image(getClass().getResource("css/permitTile/1.12.png").toExternalForm()));
		// montagna
		this.mappaTessere.put("TesseraPermesso  città:[Naris], bonus:[BonusMoneta 7]",
				new Image(getClass().getResource("css/permitTile/2.1.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Naris, Merkatim], bonus:[BonusAzionePrincipale]",
				new Image(getClass().getResource("css/permitTile/2.14.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Kultos], bonus:[BonusCartePolitica 4]",
				new Image(getClass().getResource("css/permitTile/2.8.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Lyram, Merkatim], bonus:[BonusCartePolitica 3]",
				new Image(getClass().getResource("css/permitTile/2.6.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Merkatim], bonus:[BonusPuntiVittoria 5, BonusPuntiNobiltà 1]",
				new Image(getClass().getResource("css/permitTile/2.11.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Kultos, Osium], bonus:[BonusCartePolitica 2, BonusPuntiNobiltà 1]",
				new Image(getClass().getResource("css/permitTile/2.3.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Kultos, Lyram], bonus:[BonusCartePolitica 1, BonusAiutanti 2]",
				new Image(getClass().getResource("css/permitTile/2.13.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Lyram], bonus:[BonusMoneta 1, BonusAiutanti 3]",
				new Image(getClass().getResource("css/permitTile/2.10.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Osium], bonus:[BonusCartePolitica 3, BonusPuntiNobiltà 1]",
				new Image(getClass().getResource("css/permitTile/2.9.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Kultos, Naris, Osium], bonus:[BonusCartePolitica 1, BonusPuntiVittoria 1]",
				new Image(getClass().getResource("css/permitTile/2.12.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Naris, Osium], bonus:[BonusPuntiVittoria 2, BonusCartePolitica 2]",
				new Image(getClass().getResource("css/permitTile/2.15.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Kultos, Lyram, Merkatim], bonus:[BonusAiutanti 1, BonusMoneta 1]",
				new Image(getClass().getResource("css/permitTile/2.2.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Kultos, Lyram, Osium], bonus:[BonusCartePolitica 1, BonusMoneta 1]",
				new Image(getClass().getResource("css/permitTile/2.4.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Merkatim, Naris, Osium], bonus:[BonusPuntiNobiltà 1, BonusPuntiVittoria 1]",
				new Image(getClass().getResource("css/permitTile/2.7.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Lyram, Merkatim, Naris], bonus:[BonusPuntiVittoria 3]",
				new Image(getClass().getResource("css/permitTile/2.5.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso",
				new Image(getClass().getResource("css/permitTile/PermitWhite.png").toExternalForm()));
	}

	/**
	 * @param gameStateDTO
	 *            the gameStateDTO to set
	 */
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}

	/**
	 * @param gui
	 *            the gui to set
	 */
	public void setGui(GUI gui) {
		this.gui = gui;
	}

	@FXML
	public void handleAzione(ActionEvent event) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new Runnable() {

			@Override
			public void run() {
				try {
					for (Button b : getAzioni())
						b.setDisable(true);
					System.out.println("azioni client: " + ((AzioneDTO) ((Button) event.getSource()).getUserData()));
					AzioneDTO azioneDTO = gameStateDTO.getAzioniDisponibili().stream()
							.filter(a -> a.getClass()
									.equals(((AzioneDTO) ((Button) event.getSource()).getUserData()).getClass()))
							.findAny().orElse(null);

					if (azioneDTO == null) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Errore");
						alert.setHeaderText("L'azione non esiste!");
						alert.setContentText("Ooops, riprova e inserisci un'azione valida!");

						alert.showAndWait();
						//gui.mostraMessaggio("L'azione non esiste \nInserire un'azione valida");
						for (Button b : getAzioni())
							b.setDisable(false);
						return;
					} else if (azioneDTO instanceof AzioneParametri) {
						try {
							((AzioneParametri) azioneDTO).parametri().setParametri(gui, gameStateDTO);
						} catch (AzioneNonEseguibile e) {
							gui.mostraMessaggio(e.getMessage());
							return;
						}
					}
					try {
						gui.getConnessione().inviaAzione(azioneDTO);
						for (Button b : getAzioni())
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

	@FXML
	public void elezioneConsigliere(ActionEvent event) {
		ElezioneConsigliereDTO elezione = new ElezioneConsigliereDTO();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new Runnable() {

			@Override
			public void run() {
				elezione.parametri().setParametri(gui, gameStateDTO);
				try {
					gui.getConnessione().inviaAzione(elezione);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@FXML
	public void pescaCarta(Event event) {
		PescaCartaDTO pesca = new PescaCartaDTO();
		try {
			gui.getConnessione().inviaAzione(pesca);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void acquistoTesseraPermesso(ActionEvent event) {
		AcquistoTesseraPermessoDTO acquistoTesseraPermessoDTO = new AcquistoTesseraPermessoDTO();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new Runnable() {

			@Override
			public void run() {
				try {
					acquistoTesseraPermessoDTO.parametri().setParametri(gui, gameStateDTO);
				} catch (AzioneNonEseguibile e1) {
					gui.mostraMessaggio(e1.getMessage());
					return;
				}
				try {
					gui.getConnessione().inviaAzione(acquistoTesseraPermessoDTO);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@FXML
	public void costruzioneTesseraPermesso(ActionEvent event) {
		CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO = new CostruzioneTesseraPermessoDTO();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new Runnable() {

			@Override
			public void run() {
				try {
					costruzioneTesseraPermessoDTO.parametri().setParametri(gui, gameStateDTO);
				} catch (AzioneNonEseguibile e1) {
					gui.mostraMessaggio(e1.getMessage());
					return;
				}
				try {
					gui.getConnessione().inviaAzione(costruzioneTesseraPermessoDTO);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@FXML
	public void costruzioneAiutoRe(ActionEvent event) {
		CostruzioneAiutoReDTO costruzioneAiutoReDTO = new CostruzioneAiutoReDTO();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new Runnable() {

			@Override
			public void run() {
				try {
					costruzioneAiutoReDTO.parametri().setParametri(gui, gameStateDTO);
				} catch (AzioneNonEseguibile e1) {
					gui.mostraMessaggio(e1.getMessage());
					return;
				}
				try {
					gui.getConnessione().inviaAzione(costruzioneAiutoReDTO);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@FXML
	public void cambioTesseraPermesso(ActionEvent event) {
		CambioTesserePermessoDTO cambioTesserePermessoDTO = new CambioTesserePermessoDTO();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new Runnable() {

			@Override
			public void run() {
				cambioTesserePermessoDTO.parametri().setParametri(gui, gameStateDTO);
				try {
					gui.getConnessione().inviaAzione(cambioTesserePermessoDTO);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	@FXML
	public void elezioneConsigliereVeloce(ActionEvent event) {
		ElezioneConsigliereVeloceDTO elezione = new ElezioneConsigliereVeloceDTO();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(new Runnable() {

			@Override
			public void run() {
				elezione.parametri().setParametri(gui, gameStateDTO);
				try {
					gui.getConnessione().inviaAzione(elezione);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@FXML
	public void ingaggioAiutante(ActionEvent event) {
		IngaggioAiutanteDTO ingaggioAiutanteDTO = new IngaggioAiutanteDTO();
		try {
			gui.getConnessione().inviaAzione(ingaggioAiutanteDTO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void secondaAzionePrincipale(ActionEvent event) {
		SecondaAzionePrincipaleDTO secondaAzionePrincipaleDTO = new SecondaAzionePrincipaleDTO();
		try {
			gui.getConnessione().inviaAzione(secondaAzionePrincipaleDTO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void passa(ActionEvent event) {
		PassaDTO passaDTO = new PassaDTO();
		try {
			gui.getConnessione().inviaAzione(passaDTO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mostraNomeGiocatore(String nome) {
		nomeGiocatore.setText(nome);
	}

	public void mostraPuntiGiocatore(int punti) {
		puntiGiocatore.setText(Integer.toString(punti));
	}

	public void mostraAiutantiGiocatore(int aiutanti) {
		aiutantiGiocatore.setText(Integer.toString(aiutanti));
	}

	public void mostraEmporiGiocatore(int empori) {
		emporiGiocatore.setText(Integer.toString(empori));
	}

	public void mostraMoneteGiocatore(int monete) {
		moneteGiocatore.setText(Integer.toString(monete));
	}

	public void mostraTessereBonusGiocatore(int punti) {
		tesserebonusGiocatore.setText(Integer.toString(punti));
	}

	public void mostraNobiltàGiocatore(int punti) {
		nobiltàGiocatore.setText(Integer.toString(punti));
	}

	public void mostraTessereBonus() {
		mare.setImage(new Image(getClass().getResource("css/tessereBonus/mare.png").toExternalForm()));
		collina.setImage(new Image(getClass().getResource("css/tessereBonus/collina.png").toExternalForm()));
		montagna.setImage(new Image(getClass().getResource("css/tessereBonus/montagna.png").toExternalForm()));
		oro.setImage(new Image(getClass().getResource("css/tessereBonus/oro.png").toExternalForm()));
		argento.setImage(new Image(getClass().getResource("css/tessereBonus/argento.png").toExternalForm()));
		bronzo.setImage(new Image(getClass().getResource("css/tessereBonus/bronzo.png").toExternalForm()));
		ferro.setImage(new Image(getClass().getResource("css/tessereBonus/ferro.png").toExternalForm()));
		king.setImage(new Image(getClass().getResource("css/tessereBonus/king_1.png").toExternalForm()));
	}

	public void mostraGettoni(List<CittàDTO> città) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				if (città.get(0) instanceof CittàBonusDTO) {
					gettoneArkon.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(0)).getBonus().toString()));
				}
				if (città.get(1) instanceof CittàBonusDTO) {
					gettoneBurgen.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(1)).getBonus().toString()));
				}
				if (città.get(2) instanceof CittàBonusDTO) {
					gettoneCastrum.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(2)).getBonus().toString()));
				}
				if (città.get(3) instanceof CittàBonusDTO) {
					gettoneDorful.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(3)).getBonus().toString()));
				}
				if (città.get(4) instanceof CittàBonusDTO) {
					gettoneEsti.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(4)).getBonus().toString()));
				}
				if (città.get(5) instanceof CittàBonusDTO) {
					gettoneFramek.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(5)).getBonus().toString()));
				}
				if (città.get(6) instanceof CittàBonusDTO) {
					gettoneGraden.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(6)).getBonus().toString()));
				}
				if (città.get(7) instanceof CittàBonusDTO) {
					gettoneHellar.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(7)).getBonus().toString()));
				}
				if (città.get(8) instanceof CittàBonusDTO) {
					gettoneIndur.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(8)).getBonus().toString()));
				}
				if (città.get(9) instanceof CittàBonusDTO) {
					gettoneJuvelar.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(9)).getBonus().toString()));
				}
				if (città.get(10) instanceof CittàBonusDTO) {
					gettoneKultos.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(10)).getBonus().toString()));
				}
				if (città.get(11) instanceof CittàBonusDTO) {
					gettoneLyram.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(11)).getBonus().toString()));
				}
				if (città.get(12) instanceof CittàBonusDTO) {
					gettoneMerkatim.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(12)).getBonus().toString()));
				}
				if (città.get(13) instanceof CittàBonusDTO) {
					gettoneNaris.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(13)).getBonus().toString()));
				}
				if (città.get(14) instanceof CittàBonusDTO) {
					gettoneOsium.setImage(mappaGettoni.get(((CittàBonusDTO) città.get(14)).getBonus().toString()));
				}

			}
		});
	}

	public void mostraTesserePermessoUsate(List<TesseraPermessoDTO> tessere) {
		// stessa cosa delle tessere permesso
	}
	
	public void stampaEmporiCittà(List<CittàDTO> città) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				List<HBox> hbox=Arrays.asList(emporiArkon,emporiBurgen,emporiCastrum,emporiDorful,emporiEsti,emporiFramek,emporiGraden,emporiHellar,emporiIndur,emporiJuvelar,emporiKultos,emporiLyram,emporiMerkatim,emporiNaris,emporiOsium);

				for(int i=0;i<città.size()-1;i++){
					for(String emporio: città.get(i).getEmpori()){	
						ImageView imageView=new ImageView();
						imageView.setImage(mappaEmpori.get(emporio));
						hbox.get(i).getChildren().add(imageView);
					}
				}
			}
		});
	}

	public void mostraRiserva(List<ConsigliereDTO> consiglieri) {

		consigliere1.setImage(mappaConsiglieriRiserva.get(consiglieri.get(0).getColoreConsigliere().toString()));
		consigliere1.setUserData(consiglieri.get(0));
		consigliere1.setDisable(true);
		consigliere2.setImage(mappaConsiglieriRiserva.get(consiglieri.get(1).getColoreConsigliere().toString()));
		consigliere2.setUserData(consiglieri.get(1));
		consigliere2.setDisable(true);
		consigliere3.setImage(mappaConsiglieriRiserva.get(consiglieri.get(2).getColoreConsigliere().toString()));
		consigliere3.setUserData(consiglieri.get(2));
		consigliere3.setDisable(true);
		consigliere4.setImage(mappaConsiglieriRiserva.get(consiglieri.get(3).getColoreConsigliere().toString()));
		consigliere4.setUserData(consiglieri.get(3));
		consigliere4.setDisable(true);
		consigliere5.setImage(mappaConsiglieriRiserva.get(consiglieri.get(4).getColoreConsigliere().toString()));
		consigliere5.setUserData(consiglieri.get(4));
		consigliere5.setDisable(true);
		consigliere6.setImage(mappaConsiglieriRiserva.get(consiglieri.get(5).getColoreConsigliere().toString()));
		consigliere6.setUserData(consiglieri.get(5));
		consigliere6.setDisable(true);
		consigliere7.setImage(mappaConsiglieriRiserva.get(consiglieri.get(6).getColoreConsigliere().toString()));
		consigliere7.setUserData(consiglieri.get(6));
		consigliere7.setDisable(true);
		consigliere8.setImage(mappaConsiglieriRiserva.get(consiglieri.get(7).getColoreConsigliere().toString()));
		consigliere8.setUserData(consiglieri.get(7));
		consigliere8.setDisable(true);
	}

	public void mostraTesserePermessoRegioni(List<RegioneDTO> regioni) {

		tesseraCollina1.setImage(this.mappaTessere.get(regioni.get(1).getTesserePermessoScoperte().get(0).toString()));
		tesseraCollina1.setUserData(this.mappaTessere.get(regioni.get(1).getTesserePermessoScoperte().get(0)));

		tesseraCollina2.setImage(this.mappaTessere.get(regioni.get(1).getTesserePermessoScoperte().get(1).toString()));
		tesseraCollina2.setUserData(regioni.get(1).getTesserePermessoScoperte().get(1));

		tesseraMare1.setImage(this.mappaTessere.get(regioni.get(0).getTesserePermessoScoperte().get(0).toString()));
		tesseraMare1.setUserData(regioni.get(0).getTesserePermessoScoperte().get(0));

		tesseraMare2.setImage(this.mappaTessere.get(regioni.get(0).getTesserePermessoScoperte().get(1).toString()));
		tesseraMare2.setUserData(regioni.get(0).getTesserePermessoScoperte().get(1));

		tesseraMontagna1.setImage(this.mappaTessere.get(regioni.get(2).getTesserePermessoScoperte().get(0).toString()));
		tesseraMontagna1.setUserData(regioni.get(2).getTesserePermessoScoperte().get(0));

		tesseraMontagna2.setImage(this.mappaTessere.get(regioni.get(2).getTesserePermessoScoperte().get(1).toString()));
		tesseraMontagna2.setUserData(regioni.get(2).getTesserePermessoScoperte().get(1));
	}

	@FXML
	public void handleTesseraPermessoRegione(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			gui.getLock().notifyAll();
		}

	}

	public void mostraConsiglieriBalcone() {
		gui.stampaConsiglieriBalcone();
	}

	public TextArea getMessage() {
		return this.message;
	}

	public void handleBalcone(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((HBox) event.getSource()).getUserData());
			gui.getLock().notify();
		}
	}

	@FXML
	public void handleCartaPolitica(Event event) {
		synchronized (gui.getLock()) {
			((ImageView) event.getSource()).setDisable(true);
			((ImageView) event.getSource()).setOpacity(0.5);
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			gui.getLock().notify();
		}
	}

	@FXML
	public void handleConferma(ActionEvent event) {
		synchronized (gui.getLock()) {
			gui.setCarteInserite(true);
			gui.setParametro(new Object());
			gui.getLock().notify();
		}
	}

	@FXML
	public void handleConsigliereRiserva(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			((ImageView) event.getSource()).setOpacity(0.5);
			gui.getLock().notifyAll();
		}
	}

	@FXML
	public void handleRegione(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			gui.getLock().notifyAll();
		}
	}

	@FXML
	public void handleChat(KeyEvent action) {
		if (action.getCode() == KeyCode.ENTER) {
			ChatDTO chat = new ChatDTO();
			if (!this.chat.getText().isEmpty()) {
				
				chat.setMessaggio(gameStateDTO.getGiocatoreDTO().getNome() + ": " + this.chat.getText());
				this.chat.clear();
				try {
					gui.getConnessione().inviaAzione(chat);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	public void handleTesseraPermessoGiocatore(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			gui.getLock().notifyAll();
		}
	}

	@FXML
	public void handleCittà(ActionEvent event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((Button) event.getSource()).getUserData());
			gui.getLock().notifyAll();
		}
	}

	public void assegnaBottoniCittà(List<CittàDTO> città) {
		arkon.setUserData(città.get(0));
		arkon.setDisable(true);
		this.città.add(arkon);
		burgen.setUserData(città.get(1));
		burgen.setDisable(true);
		this.città.add(burgen);
		castrum.setUserData(città.get(2));
		castrum.setDisable(true);
		this.città.add(castrum);
		dorful.setUserData(città.get(3));
		dorful.setDisable(true);
		this.città.add(dorful);
		esti.setUserData(città.get(4));
		esti.setDisable(true);
		this.città.add(esti);
		framek.setUserData(città.get(5));
		framek.setDisable(true);
		this.città.add(framek);
		graden.setUserData(città.get(6));
		graden.setDisable(true);
		this.città.add(graden);
		hellar.setUserData(città.get(7));
		hellar.setDisable(true);
		this.città.add(hellar);
		indur.setUserData(città.get(8));
		indur.setDisable(true);
		this.città.add(indur);
		juvelar.setUserData(città.get(9));
		juvelar.setDisable(true);
		this.città.add(juvelar);
		kultos.setUserData(città.get(10));
		kultos.setDisable(true);
		this.città.add(kultos);
		lyram.setUserData(città.get(11));
		lyram.setDisable(true);
		this.città.add(lyram);
		merkatim.setUserData(città.get(12));
		merkatim.setDisable(true);
		this.città.add(merkatim);
		naris.setUserData(città.get(13));
		naris.setDisable(true);
		this.città.add(naris);
		osium.setUserData(città.get(14));
		osium.setDisable(true);
		this.città.add(osium);
	}
	
	public TabPane getAvversari() {
		return this.giocatori;
	}

	public HBox getTesserePermessoGiocatore() {
		return this.tesserePermesso;
	}

	public HBox getCartePoliticaGiocatore() {
		return this.cartePolitica;
	}

	public Map<String, Image> getMappaTesserePermesso() {
		return this.mappaTessere;
	}

	public Map<String, Image> getMappaCartePolitica() {
		return this.mappaCarte;
	}
	
	public Map<String, Image> getMappaBonus() {
		return this.mappaBonus;
	}

	public List<ImageView> getConsiglieri() {

		return Arrays.asList(consigliere1, consigliere2, consigliere3, consigliere4, consigliere5, consigliere6,
				consigliere7, consigliere7);
	}

	public List<HBox> getBalconi() {
		return Arrays.asList(balconeMare, balconeCollina, balconeMontagna, balconeRe);
	}

	public List<ImageView> getCartePolitica() {
		List<ImageView> cartePolitica = new ArrayList<>();
		for (Node i : this.cartePolitica.getChildren()) {
			cartePolitica.add((ImageView) i);
		}
		return cartePolitica;
	}

	public Button getConferma() {
		return this.conferma;
	}

	public List<ImageView> getRegioni() {
		return Arrays.asList(regioneMare, regioneCollina, regioneMontagna);
	}

	public List<ImageView> getTessereMare() {
		return Arrays.asList(tesseraMare1, tesseraMare2);
	}

	public List<ImageView> getTessereCollina() {
		return Arrays.asList(tesseraCollina1, tesseraCollina2);
	}

	public List<ImageView> getTessereMontagna() {
		return Arrays.asList(tesseraMontagna1, tesseraMontagna2);
	}

	public List<ImageView> getTesserePermessoRegioni() {
		List<ImageView> tessere = new ArrayList<>();
		tessere.addAll(this.getTessereCollina());
		tessere.addAll(this.getTessereMare());
		tessere.addAll(this.getTessereMontagna());
		return tessere;
	}

	public List<Button> getCittàSenzaEmporio(Set<? extends CittàDTO> città) {
		List<Button> cittàCostruzione = new ArrayList<>();
		System.out.println(città.getClass());
		for (Button b : this.città) {
			System.out.println("città tessera " + b.getUserData() + città.contains(b.getUserData()));
			System.out.println("città " + città + "\nb.getuserdata: " + b.getUserData());
			if (!((CittàDTO) b.getUserData()).getEmpori().contains(gameStateDTO.getGiocatoreDTO().getColoreGiocatore())
					&& città.contains((CittàDTO) b.getUserData())) {
				cittàCostruzione.add(b);
			}
		}
		System.out.println("Città costruzioni: " + cittàCostruzione);
		return cittàCostruzione;
	}

	public List<Button> getAzioni() {
		return Arrays.asList(elezioneConsigliere, acquistoTesseraPermesso, costruzioneTesseraPermesso,
				costruzioneAiutoRe, ingaggioAiutante, cambioTesseraPermesso, elezioneConsigliereVeloce,
				secondaAzionePrincipale, passa);

	}

	public Button getRe() {
		for (Button b : città)
			if (b.getUserData().equals(gameStateDTO.getPedinaRE()))
				return b;
		return null;
	}

	/**
	 * @return the mappaConsiglieri
	 */
	public Map<String, Image> getMappaConsiglieri() {
		return mappaConsiglieri;
	}

}
