package client.grafica.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class GUIGameController {

	private GameStateDTO gameStateDTO;
	private GUI gui;
	private Map<String, Image> mappaCarte = new HashMap<>();
	private Map<String, Image> mappaTessere = new HashMap<>();
	private Map<String, Image> mappaGettoni = new HashMap<>();
	private Map<String, Image> mappaConsiglieri = new HashMap<>();

	@FXML
	private Text bianca;
	@FXML
	private Text multicolore;
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
	private VBox giocatori;
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
					
					ImageView image = new ImageView();
					image.setPreserveRatio(true);
					image.setFitHeight(60);
					image.setImage(mappaCarte.get(c.getColore()));
					Button tp= new Button();
					tp.setUserData(c);
					tp.setGraphic(image);
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
				}
			}
		});
	}

	public void mostraTesserePermessoRegioni(List<RegioneDTO> regioni) {
		tesseraCollina1.setImage(this.mappaTessere.get(regioni.get(1).getTesserePermessoScoperte().get(0).toString()));
		tesseraCollina2.setImage(this.mappaTessere.get(regioni.get(1).getTesserePermessoScoperte().get(1).toString()));
		tesseraMare1.setImage(this.mappaTessere.get(regioni.get(0).getTesserePermessoScoperte().get(0).toString()));
		tesseraMare2.setImage(this.mappaTessere.get(regioni.get(0).getTesserePermessoScoperte().get(1).toString()));
		tesseraMontagna1.setImage(this.mappaTessere.get(regioni.get(2).getTesserePermessoScoperte().get(0).toString()));
		tesseraMontagna2.setImage(this.mappaTessere.get(regioni.get(2).getTesserePermessoScoperte().get(1).toString()));
	}

	public void mostraAvversario(List<GiocatoreDTO> avversari) {
		/*
		 * Platform.runLater(new Runnable() {
		 * 
		 * VBox giocatori; Map<String, Image> mappaTessere; List<GiocatoreDTO>
		 * avversari;
		 * 
		 * @Override public void run() { try{ for(GiocatoreDTO g:avversari){
		 * 
		 * }} catch(Exception e){ e.printStackTrace(); } }
		 * 
		 * public Runnable setTessere(VBox giocatori, Map<String, Image>
		 * mappaTessere, List<GiocatoreDTO> avversari){ this.giocatori=
		 * giocatori; this.mappaTessere=mappaTessere; this.avversari=avversari;
		 * return this; } }.setTessere(this.giocatori,this.mappaTessere,
		 * avversari));
		 */
	}

	public void mostaConsiglieri() {
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
	
	public void handleCartaPolitica(ActionEvent event) {

	}

}
