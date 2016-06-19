package client.grafica.gui;

import java.awt.ScrollPane;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàDTO;
import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.CambioTesserePermessoDTO;
import common.azioniDTO.CostruzioneAiutoReDTO;
import common.azioniDTO.ElezioneConsigliereDTO;
import common.azioniDTO.IngaggioAiutanteDTO;
import common.azioniDTO.SecondaAzionePrincipaleDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import server.model.bonus.Bonus;
import server.model.game.TesseraPermesso;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class GUIGameController {

	private GameStateDTO gameStateDTO;
	private GUI gui;
	private Map<String, Image> mappaCarte=new HashMap<>();
	private Map<String, Image> mappaTessere=new HashMap<>();
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
	private Circle gettoneArkon;
	@FXML
	private Circle gettoneCastrum;
	@FXML
	private Circle gettoneBurgen;
	@FXML
	private Circle gettoneDorful;
	@FXML
	private Circle gettoneEsti;
	@FXML
	private Circle gettoneFramek;
	@FXML
	private Circle gettoneGraden;
	@FXML
	private Circle gettoneHellar;
	@FXML
	private Circle gettoneIndur;
	@FXML
	private Circle gettoneJuvelar;
	@FXML
	private Circle gettoneKultos;
	@FXML
	private Circle gettoneNaris;
	@FXML
	private Circle gettoneLyram;
	@FXML
	private Circle gettoneOsium;
	@FXML
	private Circle gettoneMerkatim;
	
	@FXML
	private HBox balconeMare;
	@FXML
	private HBox balconeCollina;
	@FXML
	private HBox balconeMontagna;
	@FXML
	private HBox balconeRe;

	
	public GUIGameController(){
		
		this.mappaCarte.put("Bianco", null);
		this.mappaCarte.put("Nero", null);
		this.mappaCarte.put("Viola", null);
		this.mappaCarte.put("Arancione", null);
		this.mappaCarte.put("Azzurro", null);
		
		this.mappaGettoni.put("BonusPuntiNobiltà 1", new Image(getClass().getResource("css/gettoni/15.png").toExternalForm()));
		this.mappaGettoni.put("BonusPuntiNobiltà 1", new Image(getClass().getResource("css/gettoni/1.png").toExternalForm()));
		this.mappaGettoni.put("BonusCartePolitica 1", new Image(getClass().getResource("css/gettoni/7.png").toExternalForm()));
		this.mappaGettoni.put("BonusPuntiVittoria 1", new Image(getClass().getResource("css/gettoni/14.png").toExternalForm()));
		this.mappaGettoni.put("BonusPuntiVittoria 2", new Image(getClass().getResource("css/gettoni/6.png").toExternalForm()));
		this.mappaGettoni.put("BonusPuntiVittoria 3", new Image(getClass().getResource("css/gettoni/3.png").toExternalForm()));
		this.mappaGettoni.put("BonusMoneta 3", new Image(getClass().getResource("css/gettoni/10.png").toExternalForm()));
		this.mappaGettoni.put("BonusMoneta 2", new Image(getClass().getResource("css/gettoni/12.png").toExternalForm()));
		this.mappaGettoni.put("BonusMoneta 1", new Image(getClass().getResource("css/gettoni/5.png").toExternalForm()));
		this.mappaGettoni.put("BonusMoneta 1 BonusAiutanti 1", new Image(getClass().getResource("css/gettoni/13.png").toExternalForm()));
		this.mappaGettoni.put("BonusAiutanti 2", new Image(getClass().getResource("css/gettoni/2.png").toExternalForm()));
		this.mappaGettoni.put("BonusAiutanti 1", new Image(getClass().getResource("css/gettoni/11.png").toExternalForm()));
		this.mappaGettoni.put("BonusAiutanti 1 BonusCartePolitica 1", new Image(getClass().getResource("css/gettoni/4.png").toExternalForm()));
		this.mappaGettoni.put("BonusCartePolitica 1 BonusPuntiVittoria 1", new Image(getClass().getResource("css/gettoni/9.png").toExternalForm()));
		this.mappaCarte.put("Bianco", new Image(getClass().getResource("css/politicCard/Bianco.png").toExternalForm()));
		this.mappaCarte.put("Nero", new Image(getClass().getResource("css/politicCard/Nero.png").toExternalForm()));
		this.mappaCarte.put("Viola", new Image(getClass().getResource("css/politicCard/Viola.png").toExternalForm()));
		this.mappaCarte.put("Rosa", new Image(getClass().getResource("css/politicCard/Rosa.png").toExternalForm()));
		this.mappaCarte.put("Arancione", new Image(getClass().getResource("css/politicCard/Arancione.png").toExternalForm()));
		this.mappaCarte.put("Azzurro", new Image(getClass().getResource("css/politicCard/Azzurro.png").toExternalForm()));
		this.mappaCarte.put("Multicolore", new Image(getClass().getResource("css/politicCard/Multicolore.png").toExternalForm()));

	//mare
		this.mappaTessere.put("TesseraPermesso  città:[Arkon, Burgen, Esti], bonus:[BonusMoneta 2]", new Image(getClass().getResource("css/permitTile/3.11.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Arkon, Burgen, Castrum], bonus:[BonusAiutanti 2]", new Image(getClass().getResource("css/permitTile/3.12.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Arkon], bonus:[BonusMoneta 3, BonusPuntiVittoria 4]", new Image(getClass().getResource("css/permitTile/3.6.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Esti], bonus:[BonusAzionePrincipale 1, BonusMoneta]", new Image(getClass().getResource("css/permitTile/3.15.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Castrum], bonus:[BonusAiutanti 2, BonusMoneta 3]", new Image(getClass().getResource("css/permitTile/3.7.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Dorful], bonus:[BonusPuntiVittoria 7]", new Image(getClass().getResource("css/permitTile/3.8.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Burgen], bonus:[BonusCartePolitica 3, BonusAiutanti 1]", new Image(getClass().getResource("css/permitTile/3.2.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Arkon, Esti], bonus:[BonusPuntiNobiltà 2]", new Image(getClass().getResource("css/permitTile/3.10.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Dorful, Esti], bonus:[BonusPuntiVittoria 2, BonusAiutanti 1]", new Image(getClass().getResource("css/permitTile/3.3.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Arkon, Burgen], bonus:[BonusMoneta 3, BonusCartePolitica 1]", new Image(getClass().getResource("css/permitTile/3.9.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Arkon, Dorful, Esti], bonus:[BonusMoneta 1, BonusPuntiVittoria 2]", new Image(getClass().getResource("css/permitTile/3.1.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Castrum, Dorful], bonus:[BonusPuntiVittoria 3, BonusAiutanti 1]", new Image(getClass().getResource("css/permitTile/3.5.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Burgen, Castrum, Dorful], bonus:[BonusAiutanti 1, BonusPuntiVittoria 1]", new Image(getClass().getResource("css/permitTile/3.13.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Castrum, Dorful, Esti], bonus:[BonusCartePolitica 1, BonusPuntiNobiltà 1]", new Image(getClass().getResource("css/permitTile/3.14.png").toExternalForm()));
	//collina
		this.mappaTessere.put("TesseraPermesso  città:[Hellar], bonus:[BonusAiutanti 4]", new Image(getClass().getResource("css/permitTile/1.6.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Framek, Juvelar], bonus:[BonusAiutanti 2, BonusMoneta 1]", new Image(getClass().getResource("css/permitTile/1.8.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Indur], bonus:[BonusCartePolitica 2, BonusAiutanti 2]", new Image(getClass().getResource("css/permitTile/1.10.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Juvelar], bonus:[BonusAzionePrincipale 1, BonusPuntiVittoria 2]", new Image(getClass().getResource("css/permitTile/1.11.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Framek, Graden], bonus:[BonusMoneta 5]", new Image(getClass().getResource("css/permitTile/1.9.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Graden, Hellar], bonus:[BonusAiutanti 3]", new Image(getClass().getResource("css/permitTile/1.15.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Indur, Juvelar], bonus:[BonusCartePolitica 2, BonusPuntiNobiltà 1]", new Image(getClass().getResource("css/permitTile/1.13.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Framek], bonus:[BonusCartePolitica 2, BonusMoneta 4]", new Image(getClass().getResource("css/permitTile/1.7.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Graden, Hellar, Indur], bonus:[BonusCartePolitica 1, BonusAiutanti 1]", new Image(getClass().getResource("css/permitTile/1.5.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Graden], bonus:[BonusAiutanti 2, BonusPuntiVittoria 2, BonusPuntiNobiltà 1]", new Image(getClass().getResource("css/permitTile/1.3.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Hellar, Indur], bonus:[BonusPuntiVittoria 5]", new Image(getClass().getResource("css/permitTile/1.2.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Framek, Indur, Juvelar], bonus:[BonusAiutanti 1, BonusPuntiNobiltà 1]", new Image(getClass().getResource("css/permitTile/1.4.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Framek, Graden, Hellar], bonus:[BonusPuntiNobiltà 1, BonusMoneta 1]", new Image(getClass().getResource("css/permitTile/1.1.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Framek, Graden, Juvelar], bonus:[BonusMoneta 2, BonusPuntiVittoria 1]", new Image(getClass().getResource("css/permitTile/1.14.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Indur, Juvelar, Hellar], bonus:[BonusCartePolitica 1]", new Image(getClass().getResource("css/permitTile/1.12.png").toExternalForm()));
	//montagna
		this.mappaTessere.put("TesseraPermesso  città:[Naris], bonus:[BonusMoneta 7]", new Image(getClass().getResource("css/permitTile/2.1.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Naris, Merkatim], bonus:[BonusAzionePrincipale 1]", new Image(getClass().getResource("css/permitTile/2.14.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Kultos], bonus:[BonusCartePolitica 4]", new Image(getClass().getResource("css/permitTile/2.8.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Lyram, Merkatim], bonus:[BonusCartePolitica 3]", new Image(getClass().getResource("css/permitTile/2.6.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Merkatim], bonus:[BonusPuntiVittoria 5, BonusPuntiNobiltà 1]", new Image(getClass().getResource("css/permitTile/2.11.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Osium, Kultos], bonus:[BonusCartePolitica 2, BonusPuntiNobiltà 1]", new Image(getClass().getResource("css/permitTile/2.3.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Kultos, Lyram], bonus:[BonusCartePolitica 1, BonusAiutanti 2]", new Image(getClass().getResource("css/permitTile/2.13.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Lyram], bonus:[BonusMoneta 1, BonusAiutanti 3]", new Image(getClass().getResource("css/permitTile/2.10.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Osium], bonus:[BonusCartePolitica 3, BonusPuntiNobiltà 1]", new Image(getClass().getResource("css/permitTile/2.9.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Kultos, Naris, Osium], bonus:[BonusCartePolitica 1, BonusPuntiVittoria 1]", new Image(getClass().getResource("css/permitTile/2.12.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Naris, Osium], bonus:[BonusPuntiVittoria 2, BonusCartePolitica 2]", new Image(getClass().getResource("css/permitTile/2.15.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Kultos, Lyram, Merkatim], bonus:[BonusAiutanti 1, BonusMoneta 1]", new Image(getClass().getResource("css/permitTile/2.2.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Kultos, Lyram, Osium], bonus:[BonusCartePolitica 1, BonusMoneta 1]", new Image(getClass().getResource("css/permitTile/2.4.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Merkatim, Naris, Osium], bonus:[BonusPuntiNobiltà 1, BonusPuntiVittoria 1]", new Image(getClass().getResource("css/permitTile/2.7.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso  città:[Lyram, Merkatim, Naris], bonus:[BonusPuntiVittoria 3]", new Image(getClass().getResource("css/permitTile/2.5.png").toExternalForm()));

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
	
	public void mostraNomeGiocatore(String nome){
		nomeGiocatore.setText(nome);
	}
	
	public void mostraPuntiGiocatore(int punti){
		puntiGiocatore.setText(Integer.toString(punti));
	}
	
	public void mostraAiutantiGiocatore(int aiutanti){
		aiutantiGiocatore.setText(Integer.toString(aiutanti));
	}
	
	public void mostraEmporiGiocatore(int empori){
		emporiGiocatore.setText(Integer.toString(empori));
	}
	
	public void mostraMoneteGiocatore(int monete){
		moneteGiocatore.setText(Integer.toString(monete));
	}
	
	public void mostraTessereBonusGiocatore(int punti){
		tesserebonusGiocatore.setText(Integer.toString(punti));
	}
	
	public void mostraCartePolitica(List<CartaPoliticaDTO> cartePolitica){
		Platform.runLater(new Runnable() {
			
			HBox carte;
			Map<String, Image> mappaCarte;
			@Override
			public void run() {
				try{
					for(CartaPoliticaDTO c:cartePolitica){
						ImageView image = new ImageView();
						image.setFitHeight(60);
						image.setPreserveRatio(true);
						image.setImage(this.mappaCarte.get(c.getColore()));
						carte.getChildren().add(image);
					}}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			public Runnable setCarte (HBox carte, Map<String, Image> mappaCarte){
				this.carte= carte;
				this.mappaCarte=mappaCarte;
				return this;
			}
		}.setCarte(this.cartePolitica,this.mappaCarte));	
	}

	public void mostraGettoni(List<CittàDTO> città){
		
	}

	public void mostraTesserePermesso(List<TesseraPermessoDTO> tesserePermesso) {
		Platform.runLater(new Runnable() {
			
			HBox tessere;
			Map<String, Image> mappaTessere;
			
			@Override
			public void run() {
				try{
					for(TesseraPermessoDTO t:tesserePermesso){
						ImageView image = new ImageView();
						image.setFitHeight(60);
						image.setPreserveRatio(true);
						image.setImage(this.mappaTessere.get(t.toString()));
						
						tessere.getChildren().add(image);
					}}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			public Runnable setTessere(HBox tessere,Map<String, Image> mappaTessere){
				this.tessere= tessere;
				this.mappaTessere=mappaTessere;
				return this;
			}
		}.setTessere(this.tesserePermesso,this.mappaTessere));	
	}
	
	public void mostraTesserePermessoRegioni(List<RegioneDTO> regioni){
		tesseraCollina1.setImage(this.mappaTessere.get(regioni.get(1).getTesserePermessoScoperte().get(0).toString()));
		tesseraCollina2.setImage(this.mappaTessere.get(regioni.get(1).getTesserePermessoScoperte().get(1).toString()));
		tesseraMare1.setImage(this.mappaTessere.get(regioni.get(0).getTesserePermessoScoperte().get(0).toString()));
		tesseraMare2.setImage(this.mappaTessere.get(regioni.get(0).getTesserePermessoScoperte().get(1).toString()));
		tesseraMontagna1.setImage(this.mappaTessere.get(regioni.get(2).getTesserePermessoScoperte().get(0).toString()));
		tesseraMontagna2.setImage(this.mappaTessere.get(regioni.get(2).getTesserePermessoScoperte().get(1).toString()));
	}
	
	public void mostraAvversario(List<GiocatoreDTO> avversari){
/*		Platform.runLater(new Runnable() {
			
			VBox giocatori;
			Map<String, Image> mappaTessere;
			List<GiocatoreDTO> avversari;
			
			@Override
			public void run() {
				try{
					for(GiocatoreDTO g:avversari){
						
					}}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			public Runnable setTessere(VBox giocatori, Map<String, Image> mappaTessere, List<GiocatoreDTO> avversari){
				this.giocatori= giocatori;
				this.mappaTessere=mappaTessere;
				this.avversari=avversari;
				return this;
			}
		}.setTessere(this.giocatori,this.mappaTessere, avversari));	
*/
	}
	
	public void mostraConsiglieri(){
		
	}

}
