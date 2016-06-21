package client.grafica.gui;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàBonusDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

public class GUIGameController {

	private GameStateDTO gameStateDTO;
	private GUI gui;
	private Map<String, Image> mappaCarte = new HashMap<>();
	private Map<String, Image> mappaTessere = new HashMap<>();
	private Map<String, Image> mappaGettoni = new HashMap<>();
	private Map<String, Image> mappaConsiglieri = new HashMap<>();
	private Map<String, Image> mappaConsiglieriRiserva = new HashMap<>();

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

	public GUIGameController() {

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
		this.mappaTessere.put("TesseraPermesso  città:[Esti], bonus:[BonusAzionePrincipale 1, BonusMoneta]",
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
		this.mappaTessere.put("TesseraPermesso  città:[Juvelar], bonus:[BonusAzionePrincipale 1, BonusPuntiVittoria 2]",
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
		this.mappaTessere.put("TesseraPermesso  città:[Naris, Merkatim], bonus:[BonusAzionePrincipale 1]",
				new Image(getClass().getResource("css/permitTile/2.14.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Kultos], bonus:[BonusCartePolitica 4]",
				new Image(getClass().getResource("css/permitTile/2.8.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Lyram, Merkatim], bonus:[BonusCartePolitica 3]",
				new Image(getClass().getResource("css/permitTile/2.6.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Merkatim], bonus:[BonusPuntiVittoria 5, BonusPuntiNobiltà 1]",
				new Image(getClass().getResource("css/permitTile/2.11.png").toExternalForm()));
		this.mappaTessere.put(
				"TesseraPermesso  città:[Osium, Kultos], bonus:[BonusCartePolitica 2, BonusPuntiNobiltà 1]",
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

	public void mostraCartePolitica(List<CartaPoliticaDTO> carte) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for (CartaPoliticaDTO c : carte) {

					Button tp= new Button();
					tp.setPrefHeight(60);
					tp.setPrefWidth(45);
					Background sfondo= new Background(new BackgroundImage(mappaCarte.get(c.toString()), BackgroundRepeat.NO_REPEAT,
							BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(45, 60, false, false, true, true)));
					tp.backgroundProperty().set(sfondo);
					tp.setUserData(c);
					cartePolitica.getChildren().add(tp);
					tp.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							handleCartaPolitica(event);
						}
					});

				}

			}
		});
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

	public void mostraTesserePermesso(List<TesseraPermessoDTO> tessere) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for (TesseraPermessoDTO t : tessere) {
					ImageView image = new ImageView();
					image.setFitHeight(60);
					image.setPreserveRatio(true);
					image.setImage(mappaTessere.get(t.toString()));
					Button tp = new Button();
					tp.setGraphic(image);
					tp.setUserData(t);
					tesserePermesso.getChildren().add(tp);
				}
			}
		});
	}

	public void mostraRiserva(List<ConsigliereDTO> consiglieri) {

		consigliere1.setImage(mappaConsiglieriRiserva.get(consiglieri.get(0).getColoreConsigliere().toString()));
		consigliere1.setUserData(consiglieri.get(0).getColoreConsigliere());
		consigliere2.setImage(mappaConsiglieriRiserva.get(consiglieri.get(1).getColoreConsigliere().toString()));
		consigliere2.setUserData(consiglieri.get(1).getColoreConsigliere());
		consigliere3.setImage(mappaConsiglieriRiserva.get(consiglieri.get(2).getColoreConsigliere().toString()));
		consigliere3.setUserData(consiglieri.get(2).getColoreConsigliere());
		consigliere4.setImage(mappaConsiglieriRiserva.get(consiglieri.get(3).getColoreConsigliere().toString()));
		consigliere4.setUserData(consiglieri.get(3).getColoreConsigliere());
		consigliere5.setImage(mappaConsiglieriRiserva.get(consiglieri.get(4).getColoreConsigliere().toString()));
		consigliere5.setUserData(consiglieri.get(4).getColoreConsigliere());
		consigliere6.setImage(mappaConsiglieriRiserva.get(consiglieri.get(5).getColoreConsigliere().toString()));
		consigliere6.setUserData(consiglieri.get(5).getColoreConsigliere());
		consigliere7.setImage(mappaConsiglieriRiserva.get(consiglieri.get(6).getColoreConsigliere().toString()));
		consigliere7.setUserData(consiglieri.get(6).getColoreConsigliere());
		consigliere8.setImage(mappaConsiglieriRiserva.get(consiglieri.get(7).getColoreConsigliere().toString()));
		consigliere8.setUserData(consiglieri.get(7).getColoreConsigliere());
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
		System.out.println(((Button)event.getSource()).getUserData());

	}

	public void mostraAvversario(List<GiocatoreDTO> avversari) {
		Platform.runLater(new Runnable() {

			TabPane giocatori;
			Map<String, Image> mappaTessere;
			List<GiocatoreDTO> avversari;

			@Override
			public void run() {
				try {
					for (GiocatoreDTO g : avversari) {
						Tab tab = new Tab();
						VBox vbox = new VBox();
						HBox hbox = new HBox();
						hbox.setSpacing(15);
						vbox.setSpacing(5);
						tab.setText(g.getNome());
						// hbox.relocate(20, 5);
						vbox.getChildren().add(hbox);
						tab.setContent(vbox);
						giocatori.getTabs().add(tab);

						Pane pane1 = new Pane();
						ImageView puntiV = new ImageView();
						puntiV.setImage(new Image(getClass().getResource("css/Point.png").toExternalForm()));
						puntiV.setPreserveRatio(true);
						puntiV.setFitHeight(40);
						Text npuntiV = new Text();
						npuntiV.setText(Integer.toString(g.getPunteggioVittoria()));
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
						npuntiR.setText(Integer.toString(g.getPunteggioRicchezza()));
						npuntiR.relocate(10, 13);
						pane2.getChildren().add(puntiR);
						pane2.getChildren().add(npuntiR);
						hbox.getChildren().add(pane2);

						Pane pane3 = new Pane();
						ImageView aiutanti = new ImageView();
						aiutanti.setImage(new Image(getClass().getResource("css/Assistant.png").toExternalForm()));
						aiutanti.setPreserveRatio(true);
						aiutanti.setFitHeight(40);
						Text naiutanti = new Text();
						naiutanti.setText(Integer.toString(g.getAiutanti()));
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
						empori.setText(Integer.toString(g.getEmpori()));
						empori.relocate(10, 13);
						pane4.getChildren().add(emporio);
						pane4.getChildren().add(empori);
						hbox.getChildren().add(pane4);

						Pane pane5 = new Pane();
						ImageView nobilty = new ImageView();
						nobilty.setImage(new Image(getClass().getResource("css/Nobility.png").toExternalForm()));
						nobilty.setPreserveRatio(true);
						nobilty.setFitHeight(40);
						Text nobiltyPoint = new Text();
						nobiltyPoint.setText(Integer.toString(g.getPunteggioNobiltà()));
						nobiltyPoint.relocate(10, 13);
						pane5.getChildren().add(nobilty);
						pane5.getChildren().add(nobiltyPoint);
						hbox.getChildren().add(pane5);

						Pane pane6 = new Pane();
						ImageView puntiBonus = new ImageView();
						puntiBonus
								.setImage(new Image(getClass().getResource("css/BonusGiocatori.png").toExternalForm()));
						puntiBonus.setPreserveRatio(true);
						puntiBonus.setFitHeight(40);
						Text npuntiBonus = new Text();
						npuntiBonus.setText(Integer.toString(g.getPunteggioNobiltà()));
						npuntiBonus.relocate(20, 13);
						pane6.getChildren().add(puntiBonus);
						pane6.getChildren().add(npuntiBonus);
						hbox.getChildren().add(pane6);

						HBox hbox1 = new HBox();
						for (TesseraPermessoDTO t : g.getTesserePermesso()) {
							ImageView image = new ImageView();
							image.setFitHeight(60);
							image.setPreserveRatio(true);
							image.setImage(mappaTessere.get(t.toString()));
							hbox1.getChildren().add(image);
						}
						vbox.getChildren().add(hbox1);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public Runnable setAvversari(TabPane giocatori, Map<String, Image> mappaTessere,
					List<GiocatoreDTO> avversari) {
				this.giocatori = giocatori;
				this.mappaTessere = mappaTessere;
				this.avversari = avversari;
				return this;
			}
		}.setAvversari(this.giocatori, this.mappaTessere, avversari));
	}

	public void mostraConsiglieriBalcone() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for (ConsigliereDTO c : gameStateDTO.getRegioni().get(0).getBalcone().getConsiglieri()) {
					ImageView imageView = new ImageView(mappaConsiglieri.get(c.toString()));
					imageView.setFitHeight(25);
					imageView.setPreserveRatio(true);
					balconeMare.getChildren().add(imageView);
				}

				for (ConsigliereDTO c : gameStateDTO.getRegioni().get(1).getBalcone().getConsiglieri()) {
					ImageView imageView = new ImageView(mappaConsiglieri.get(c.toString()));
					imageView.setFitHeight(25);
					imageView.setPreserveRatio(true);
					balconeCollina.getChildren().add(imageView);
				}
				for (ConsigliereDTO c : gameStateDTO.getRegioni().get(2).getBalcone().getConsiglieri()) {
					ImageView imageView = new ImageView(mappaConsiglieri.get(c.toString()));
					imageView.setFitHeight(25);
					imageView.setPreserveRatio(true);
					balconeMontagna.getChildren().add(imageView);
				}
				for (ConsigliereDTO c : gameStateDTO.getPlanciaReDTO().getBalconeRe().getConsiglieri()) {
					ImageView imageView = new ImageView(mappaConsiglieri.get(c.toString()));
					imageView.setFitHeight(25);
					imageView.setPreserveRatio(true);
					balconeRe.getChildren().add(imageView);
				}
			}
		});
	}

	public TextArea getMessage() {
		return this.message;
	}

	public void handleCartaPolitica(Event event) {
		System.out.println(((Button)event.getSource()).getUserData());
	}

	@FXML
	public void handleConsigliereRiserva(ActionEvent event) {
		System.out.println(((Button) event.getSource()).getUserData());
	}

	@FXML
	public void selezionaCittà(ActionEvent event) {
		System.out.println(((Button) event.getSource()).getUserData());
	}

	public void assegnaBottoniCittà(List<CittàDTO> città) {
		arkon.setUserData(città.get(0));
		burgen.setUserData(città.get(1));
		castrum.setUserData(città.get(2));
		dorful.setUserData(città.get(3));
		esti.setUserData(città.get(4));
		framek.setUserData(città.get(5));
		graden.setUserData(città.get(6));
		hellar.setUserData(città.get(7));
		indur.setUserData(città.get(8));
		juvelar.setUserData(città.get(9));
		kultos.setUserData(città.get(10));
		lyram.setUserData(città.get(11));
		merkatim.setUserData(città.get(12));
		naris.setUserData(città.get(13));
		osium.setUserData(città.get(14));
	}
}
