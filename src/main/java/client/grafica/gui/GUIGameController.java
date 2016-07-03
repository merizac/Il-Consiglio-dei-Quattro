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
import java.util.logging.Level;
import java.util.logging.Logger;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.AzioneParametri;
import common.azioniDTO.ChatDTO;
import common.azioniDTO.ExitDTO;
import common.gameDTO.CittàBonusDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.ColoreBonusDTO;
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
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.text.Text;
import utility.AzioneNonEseguibile;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GUIGameController implements Controller {

	private GameStateDTO gameStateDTO;
	private GUI gui;
	private Map<String, Image> mappaCarte = new HashMap<>();
	private Map<String, Image> mappaTessere = new HashMap<>();
	private Map<String, Image> mappaGettoni = new HashMap<>();
	private Map<String, Image> mappaConsiglieri = new HashMap<>();
	private Map<String, Image> mappaConsiglieriRiserva = new HashMap<>();
	private Map<String, Image> mappaBonus = new HashMap<>();
	private Map<String, Image> mappaEmpori = new HashMap<>();
	private static final Logger log = Logger.getLogger(GUIGameController.class.getName());

	@FXML
	private ImageView mappaImmagine;
	@FXML
	private HBox giocatoreGUI;
	@FXML
	private HBox tesserePermesso;
	@FXML
	private HBox tesserePermessoUsate;
	@FXML
	private HBox cartePolitica;
	@FXML
	private Text nomeGiocatore;
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
	private ImageView frecciaMare;
	@FXML
	private ImageView frecciaCollina;
	@FXML
	private ImageView frecciaMontagna;
	@FXML
	private ImageView frecciaRe;
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
	private Pane arkon;
	@FXML
	private Pane burgen;
	@FXML
	private Pane castrum;
	@FXML
	private Pane dorful;
	@FXML
	private Pane esti;
	@FXML
	private Pane framek;
	@FXML
	private Pane indur;
	@FXML
	private Pane graden;
	@FXML
	private Pane juvelar;
	@FXML
	private Pane hellar;
	@FXML
	private Pane kultos;
	@FXML
	private Pane naris;
	@FXML
	private Pane lyram;
	@FXML
	private Pane osium;
	@FXML
	private Pane merkatim;
	@FXML
	private List<Pane> città = new ArrayList<>();

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

	/**
	 * map image with button or imageView
	 */
	public GUIGameController() {
		this.mappaEmpori.put("0", new Image(getClass().getResource("css/empori/1.png").toExternalForm()));
		this.mappaEmpori.put("1", new Image(getClass().getResource("css/empori/2.png").toExternalForm()));
		this.mappaEmpori.put("2", new Image(getClass().getResource("css/empori/3.png").toExternalForm()));
		this.mappaEmpori.put("3", new Image(getClass().getResource("css/empori/4.png").toExternalForm()));

		this.mappaBonus.put("Aiutante", new Image(getClass().getResource("css/Assistant.png").toExternalForm()));
		this.mappaBonus.put("Re", new Image(getClass().getResource("css/king.png").toExternalForm()));
		this.mappaBonus.put("Punto", new Image(getClass().getResource("css/Point.png").toExternalForm()));
		this.mappaBonus.put("Nobiltà", new Image(getClass().getResource("css/Nobility.png").toExternalForm()));
		this.mappaBonus.put("BonusGiocatore",
				new Image(getClass().getResource("css/BonusGiocatori.png").toExternalForm()));
		this.mappaBonus.put("Soldo", new Image(getClass().getResource("css/Coins.png").toExternalForm()));

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
		this.mappaTessere.put("TesseraPermesso  città:[Hellar, Indur, Juvelar], bonus:[BonusCartePolitica 1]",
				new Image(getClass().getResource("css/permitTile/1.12.png").toExternalForm()));
		// montagna
		this.mappaTessere.put("TesseraPermesso  città:[Naris], bonus:[BonusMoneta 7]",
				new Image(getClass().getResource("css/permitTile/2.1.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Merkatim, Naris], bonus:[BonusAzionePrincipale]",
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

	/**
	 * handle action that are send from the player
	 * 
	 * @param event
	 */
	@FXML
	public void handleAzione(ActionEvent event) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Runnable runnable = () -> {

			for (Button b : getAzioni())
				b.setDisable(true);
			AzioneDTO azioneDTO = gameStateDTO.getAzioniDisponibili().stream()
					.filter(a -> a.getClass()
							.equals(((AzioneDTO) ((Button) event.getSource()).getUserData()).getClass()))
					.findAny().orElse(null);

			if (azioneDTO == null) {
				gui.azioneNonValida("L'azione non esiste!", "Ooops, riprova e inserisci un'azione valida!");
				for (Button b : getAzioni()) {
					b.setDisable(false);
				}
				return;
			} else if (azioneDTO instanceof AzioneParametri) {
				try {
					((AzioneParametri) azioneDTO).parametri().setParametri(gui, gameStateDTO);
				} catch (AzioneNonEseguibile e) {
					gui.mostraMessaggio(e.getMessage());
					log.log(Level.INFO, "Azione non eseguibile", e);
					return;
				}
			}
			try {
				gui.getConnessione().inviaAzione(azioneDTO);
				gui.stopTimer();
				for (Button b : getAzioni())
					b.setDisable(false);
			} catch (RemoteException e) {
				log.log(Level.SEVERE, "Errore nell'invio dell'azione :" + azioneDTO, e);
			}

		};
		executor.submit(runnable);
	}

	/**
	 * handle exit action
	 */
	@FXML
	public void handleExit() {
		try {
			gui.getConnessione().inviaAzione(new ExitDTO());
			gui.stopTimer();
			gui.close();
		} catch (RemoteException e) {
			log.log(Level.SEVERE, "Errore nell'invio dell'azione exit", e);
		}
	}

	/**
	 * show name player
	 * 
	 * @param nome
	 */
	public void mostraNomeGiocatore(String nome) {
		nomeGiocatore.setText(nome);
	}

	/**
	 * show bonus tiles
	 */
	public void mostraTessereBonus() {
		
		mare.setVisible(false);
		collina.setVisible(false);
		montagna.setVisible(false);
		
		
		if (gameStateDTO.getRegioni().get(0).getBonusRegione() != null) {
			mare.setImage(new Image(getClass().getResource("css/tessereBonus/mare.png").toExternalForm()));
			mare.setVisible(true);
		}
		if (gameStateDTO.getRegioni().get(1).getBonusRegione() != null) {
			collina.setImage(new Image(getClass().getResource("css/tessereBonus/collina.png").toExternalForm()));
			collina.setVisible(true);
		}
		if (gameStateDTO.getRegioni().get(2).getBonusRegione() != null) {
			montagna.setImage(new Image(getClass().getResource("css/tessereBonus/montagna.png").toExternalForm()));
			montagna.setVisible(true);
		}

		oro.setVisible(false);
		argento.setVisible(false);
		bronzo.setVisible(false);
		ferro.setVisible(false);

		for (ColoreBonusDTO b : gameStateDTO.getBonusColore()) {

			if ("Giallo".equals(b.getColore())) {
				oro.setVisible(true);
				oro.setImage(new Image(getClass().getResource("css/tessereBonus/oro.png").toExternalForm()));
			} else if ("Grigio".equals(b.getColore())) {
				argento.setVisible(true);
				argento.setImage(new Image(getClass().getResource("css/tessereBonus/argento.png").toExternalForm()));
			} else if ("Bronzo".equals(b.getColore())) {
				bronzo.setVisible(true);
				bronzo.setImage(new Image(getClass().getResource("css/tessereBonus/bronzo.png").toExternalForm()));
			} else if ("Blu".equals(b.getColore())) {
				ferro.setVisible(true);
				ferro.setImage(new Image(getClass().getResource("css/tessereBonus/ferro.png").toExternalForm()));
			}
		}
		
		king.setVisible(false);
		if (!gameStateDTO.getPlanciaReDTO().getBonusPremioRe().isEmpty()) {
			String bonus = String.valueOf(6 - gameStateDTO.getPlanciaReDTO().getBonusPremioRe().size());
			king.setImage(
					new Image(getClass().getResource("css/tessereBonus/king_" + bonus + ".png").toExternalForm()));
			king.setVisible(true);
		}
	}

	/**
	 * show bonus of the cities
	 * @param città
	 */
	public void mostraGettoni(List<CittàDTO> città) {
		Runnable runnable = () -> {
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

		};

		Platform.runLater(runnable);
	}

	/**
	 * show emporium in cities
	 * 
	 * @param città
	 */
/*	public void stampaEmporiCittà(List<CittàDTO> città) {
		Runnable runnable = () -> {
			List<HBox> hbox = Arrays.asList(emporiArkon, emporiBurgen, emporiCastrum, emporiDorful, emporiEsti,
					emporiFramek, emporiGraden, emporiHellar, emporiIndur, emporiJuvelar, emporiKultos, emporiLyram,
					emporiMerkatim, emporiNaris, emporiOsium);

			for (int i = 0; i < città.size() - 1; i++) {
				for (String emporio : città.get(i).getEmpori()) {
					ImageView imageView = new ImageView();
					imageView.setImage(mappaEmpori.get(emporio));
					hbox.get(i).getChildren().add(imageView);
				}
			}
		};

		Platform.runLater(runnable);
	}*/

	/**
	 * show counselors in stock
	 * @param consiglieri
	 */
	public void mostraRiserva(List<ConsigliereDTO> consiglieri) {

		consigliere1.setImage(mappaConsiglieriRiserva.get(consiglieri.get(0).getColoreConsigliere()));
		consigliere1.setUserData(consiglieri.get(0));
		consigliere1.setDisable(true);
		consigliere2.setImage(mappaConsiglieriRiserva.get(consiglieri.get(1).getColoreConsigliere()));
		consigliere2.setUserData(consiglieri.get(1));
		consigliere2.setDisable(true);
		consigliere3.setImage(mappaConsiglieriRiserva.get(consiglieri.get(2).getColoreConsigliere()));
		consigliere3.setUserData(consiglieri.get(2));
		consigliere3.setDisable(true);
		consigliere4.setImage(mappaConsiglieriRiserva.get(consiglieri.get(3).getColoreConsigliere()));
		consigliere4.setUserData(consiglieri.get(3));
		consigliere4.setDisable(true);
		consigliere5.setImage(mappaConsiglieriRiserva.get(consiglieri.get(4).getColoreConsigliere()));
		consigliere5.setUserData(consiglieri.get(4));
		consigliere5.setDisable(true);
		consigliere6.setImage(mappaConsiglieriRiserva.get(consiglieri.get(5).getColoreConsigliere()));
		consigliere6.setUserData(consiglieri.get(5));
		consigliere6.setDisable(true);
		consigliere7.setImage(mappaConsiglieriRiserva.get(consiglieri.get(6).getColoreConsigliere()));
		consigliere7.setUserData(consiglieri.get(6));
		consigliere7.setDisable(true);
		consigliere8.setImage(mappaConsiglieriRiserva.get(consiglieri.get(7).getColoreConsigliere()));
		consigliere8.setUserData(consiglieri.get(7));
		consigliere8.setDisable(true);
	}

	/**
	 * show permit tile of region
	 * @param regioni
	 */
	public void mostraTesserePermessoRegioni(List<RegioneDTO> regioni) {

		tesseraCollina1.setImage(this.mappaTessere.get(regioni.get(1).getTesserePermessoScoperte().get(0).toString()));
		tesseraCollina1.setUserData(regioni.get(1).getTesserePermessoScoperte().get(0));

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

	/**
	 * handle action on click of permit tile
	 * @param event
	 */
	@FXML
	public void handleTesseraPermessoRegione(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			gui.getLock().notifyAll();
		}

	}

	/**
	 * show balcony councillors
	 */
	public void mostraConsiglieriBalcone() {
		gui.stampaConsiglieriBalcone();
	}

	/**
	 * get message
	 */
	@Override
	public TextArea getMessage() {
		return this.message;
	}

	/**
	 * handle action on balcony
	 * 
	 * @param event
	 */
	public void handleBalcone(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((HBox) event.getSource()).getUserData());
			gui.getLock().notify();
		}
	}

	/**
	 * handle action on politic card
	 * 
	 * @param event
	 */
	@FXML
	public void handleCartaPolitica(Event event) {
		synchronized (gui.getLock()) {
			((ImageView) event.getSource()).setDisable(true);
			((ImageView) event.getSource()).setEffect(null);
			((ImageView) event.getSource()).setOpacity(0.5);
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			gui.getLock().notify();
		}
	}

	/**
	 * handle action on conferma button
	 */
	@FXML
	public void handleConferma() {
		synchronized (gui.getLock()) {
			gui.setCarteInserite(true);
			gui.setParametro(new Object());
			gui.getLock().notify();
		}
	}
	
	/**
	 * handle audio on button of volume
	 */
	@FXML
	public void handleAudio() {
			if (gui.getSong().getStatus().equals(Status.PLAYING)){
				gui.getSong().stop();
			}
			else {
				gui.getSong().play();
			}
	}

	/**
	 * handle action on stock councillor
	 * @param event
	 */
	@FXML
	public void handleConsigliereRiserva(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			((ImageView) event.getSource()).setOpacity(0.5);
			gui.getLock().notifyAll();
		}
	}

	/**
	 * handle action on region button
	 * @param event
	 */
	@FXML
	public void handleRegione(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			gui.getLock().notifyAll();
		}
	}

	/**
	 * handle action on chat text area
	 * @param action
	 */
	@FXML
	public void handleChat(KeyEvent action) {
		if (action.getCode() == KeyCode.ENTER) {
			ChatDTO chatMessage = new ChatDTO();
			if (!this.chat.getText().isEmpty()) {

				chatMessage.setMessaggio(gameStateDTO.getGiocatoreDTO().getNome() + ": " + this.chat.getText());
				this.chat.clear();
				try {
					gui.getConnessione().inviaAzione(chatMessage);
				} catch (RemoteException e) {
					log.log(Level.SEVERE, "Errore nell'invio dell'azione chat", e);
				}
			}
		}
	}

	/**
	 * handle player permit tile
	 * 
	 * @param event
	 */
	@FXML
	public void handleTesseraPermessoGiocatore(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((ImageView) event.getSource()).getUserData());
			gui.getLock().notifyAll();
		}
	}

	/**
	 * handle action on city
	 * @param event
	 */
	@FXML
	public void handleCittà(Event event) {
		synchronized (gui.getLock()) {
			gui.setParametro(((Pane) event.getSource()).getUserData());
			gui.getLock().notifyAll();
		}
	}

	/**
	 * assigns button on each city
	 * 
	 * @param città
	 */
	public void assegnaBottoniCittà(List<CittàDTO> città) {
		this.città.clear();
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

	/**
	 * 
	 * @return giocatori
	 */
	public TabPane getAvversari() {
		return this.giocatori;
	}

	/**
	 * 
	 * @return tessere permesso
	 */
	public HBox getTesserePermessoGiocatore() {
		return this.tesserePermesso;
	}

	/**
	 * 
	 * @return carte politica
	 */
	public HBox getCartePoliticaGiocatore() {
		return this.cartePolitica;
	}

	/**
	 * 
	 * @return mappaTessere
	 */
	public Map<String, Image> getMappaTesserePermesso() {
		return this.mappaTessere;
	}

	/**
	 * 
	 * @return mappaCarte
	 */
	public Map<String, Image> getMappaCartePolitica() {
		return this.mappaCarte;
	}

	/**
	 * 
	 * @return mappaBonus
	 */
	public Map<String, Image> getMappaBonus() {
		return this.mappaBonus;
	}

	/**
	 * 
	 * @return list of imageView of councillor
	 */
	public List<ImageView> getConsiglieri() {

		return Arrays.asList(consigliere1, consigliere2, consigliere3, consigliere4, consigliere5, consigliere6,
				consigliere7, consigliere7, consigliere8);
	}

	/**
	 * 
	 * @return list of balcony
	 */
	public List<HBox> getBalconi() {
		return Arrays.asList(balconeMare, balconeCollina, balconeMontagna, balconeRe);
	}

	/**
	 * 
	 * @return arrow of balcony
	 */
	public List<ImageView> getFrecce() {
		return Arrays.asList(frecciaMare, frecciaCollina, frecciaMontagna, frecciaRe);
	}

	/**
	 * 
	 * @return cartePoliticaGiocatore
	 */
	public List<ImageView> getCartePolitica() {
		List<ImageView> cartePoliticaGiocatore = new ArrayList<>();
		for (Node i : this.cartePolitica.getChildren()) {
			cartePoliticaGiocatore.add((ImageView) i);
		}
		return cartePoliticaGiocatore;
	}

	/**
	 * 
	 * @return conferma
	 */
	public Button getConferma() {
		return this.conferma;
	}

	/**
	 * 
	 * @return list of region
	 */
	public List<ImageView> getRegioni() {
		return Arrays.asList(regioneMare, regioneCollina, regioneMontagna);
	}

	/**
	 * 
	 * @return list of sea permit tile
	 */
	public List<ImageView> getTessereMare() {
		return Arrays.asList(tesseraMare1, tesseraMare2);
	}

	/**
	 * 
	 * @return list of hill permit tile
	 */
	public List<ImageView> getTessereCollina() {
		return Arrays.asList(tesseraCollina1, tesseraCollina2);
	}

	/**
	 * 
	 * @return list of mountain permit tile
	 */
	public List<ImageView> getTessereMontagna() {
		return Arrays.asList(tesseraMontagna1, tesseraMontagna2);
	}

	/**
	 * 
	 * @return list of emporium of each city
	 */
	public List<HBox> getListaEmporiHBox() {
		return Arrays.asList(emporiArkon, emporiBurgen, emporiCastrum, emporiDorful, emporiEsti, emporiFramek,
				emporiGraden, emporiHellar, emporiIndur, emporiJuvelar, emporiKultos, emporiLyram, emporiMerkatim,
				emporiNaris, emporiOsium);
	}

	/**
	 * @return the mappaEmpori
	 */
	public Map<String, Image> getMappaEmpori() {
		return mappaEmpori;
	}

	/**
	 * 
	 * @return list of permit tile
	 */
	public List<ImageView> getTesserePermessoRegioni() {
		List<ImageView> tessere = new ArrayList<>();
		tessere.addAll(this.getTessereCollina());
		tessere.addAll(this.getTessereMare());
		tessere.addAll(this.getTessereMontagna());
		return tessere;
	}

	/**
	 * 
	 * @param città
	 * @return city without emporium
	 */
	public List<Pane> getCittàSenzaEmporio(Set<? extends CittàDTO> città) {
		List<Pane> cittàCostruzione = new ArrayList<>();
		for (Pane b : this.città) {
			if (!((CittàDTO) b.getUserData()).getEmpori()
					.contains(gameStateDTO.getGiocatoreDTO().getColoreGiocatore().getColore())
					&& città.contains((CittàDTO) b.getUserData())) {
				cittàCostruzione.add(b);
			}
		}
		return cittàCostruzione;
	}

	/**
	 * 
	 * @return list of action
	 */
	public List<Button> getAzioni() {
		return Arrays.asList(elezioneConsigliere, acquistoTesseraPermesso, costruzioneTesseraPermesso,
				costruzioneAiutoRe, ingaggioAiutante, cambioTesseraPermesso, elezioneConsigliereVeloce,
				secondaAzionePrincipale, passa, pescaCarta);

	}

	/**
	 * 
	 * @return king's city
	 */
	public Pane getRe() {
		Pane cittàRe = null;
		for (Pane b : città) {
			b.getChildren().clear();
			if (((CittàDTO) b.getUserData()).getNome().equals(gameStateDTO.getPedinaRE().getCittà().getNome())) {
				cittàRe = b;
			}
		}
		return cittàRe;
	}

	/**
	 * @return the mappaConsiglieri
	 */
	public Map<String, Image> getMappaConsiglieri() {
		return mappaConsiglieri;
	}

	/**
	 * 
	 * @return list of city where the player has emporiums
	 */
	public List<Pane> getCittàBonusConEmporio() {
		List<Pane> cittàBonusGettone = new ArrayList<>();
		for (Pane cittàBonus : città) {
			if (cittàBonus.getUserData() instanceof CittàBonusDTO && ((CittàDTO) cittàBonus.getUserData()).getEmpori()
					.contains(gameStateDTO.getGiocatoreDTO().getColoreGiocatore().getColore()))
				cittàBonusGettone.add(cittàBonus);
		}

		return cittàBonusGettone;
	}

	/**
	 * @return the giocatoreGUI
	 */
	public HBox getGiocatoreGUI() {
		return giocatoreGUI;
	}

	/**
	 * @return the mappaImmagine
	 */
	public ImageView getMappaImmagine() {
		return mappaImmagine;
	}

	/**
	 * 
	 * @return tesserePermessoUsate
	 */
	public HBox getTesserePermessoGiocatoreUsate() {
		return this.tesserePermessoUsate;
	}

}
