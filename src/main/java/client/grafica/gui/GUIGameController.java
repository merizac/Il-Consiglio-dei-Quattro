package client.grafica.gui;

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

public class GUIGameController {

	private GameStateDTO gameStateDTO;
	private GUI gui;
	private Map<String, Image> mappaCarte=new HashMap<>();
	private Map<String, Image> mappaTessere=new HashMap<>();
	private Map<String, Image> mappaGettoni = new HashMap<>();
	
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
	

	private AzioneDTO azioneCorrente;

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

	
	public GUIGameController(){
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
		this.mappaTessere.put("TesseraPermesso [ ( Arkon Burgen Esti ), ( BonusMoneta 2 ) ]", new Image(getClass().getResource("css/permitTile/3.11.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Arkon Burgen Castrum ), ( BonusAiutanti 2 ) ]", new Image(getClass().getResource("css/permitTile/3.12.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Arkon ), ( BonusMoneta 3 BonusPuntiVittoria 4 ) ]", new Image(getClass().getResource("css/permitTile/3.6.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Esti ), ( BonusAzionePrincipale 1 BonusMoneta 2 ) ]", new Image(getClass().getResource("css/permitTile/3.15.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Castrum ), ( BonusAiutanti 2 BonusMoneta 3 ) ]", new Image(getClass().getResource("css/permitTile/3.7.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Dorful ), ( BonusPuntiVittoria 7 ) ]", new Image(getClass().getResource("css/permitTile/3.8.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Burgen ), ( BonusCartePolitica 3 BonusAiutanti 1 ) ]", new Image(getClass().getResource("css/permitTile/3.2.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Arkon Esti ), ( BonusPuntiNobiltà 2 ) ]", new Image(getClass().getResource("css/permitTile/3.10.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Dorful Esti ), ( BonusPuntiVittoria 2 BonusAiutanti 1 ) ]", new Image(getClass().getResource("css/permitTile/3.3.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Arkon Burgen ), ( BonusMoneta 3 BonusCartePolitica 1 ) ]", new Image(getClass().getResource("css/permitTile/3.9.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Arkon Dorful Esti ), ( BonusMoneta 1 BonusPuntiVittoria 2 ) ]", new Image(getClass().getResource("css/permitTile/3.1.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Castrum Dorful ), ( BonusPuntiVittoria 3 BonusAiutanti 1 ) ]", new Image(getClass().getResource("css/permitTile/3.5.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Burgen Castrum Dorful ), ( BonusAiutanti 1 BonusPuntiVittoria 1 ) ]", new Image(getClass().getResource("css/permitTile/3.13.png").toExternalForm()));
		this.mappaTessere.put("TesseraPermesso [ ( Castrum Dorful Esti ), ( BonusCartePolitica 1 BonusPuntiNobiltà 1 ) ]", new Image(getClass().getResource("css/permitTile/3.14.png").toExternalForm()));

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

	@FXML
	public void handleAction(ActionEvent action) {
		if (azioneCorrente != null) {
			// do something
		}
		if (action.getSource().equals(elezioneConsigliere))
			azioneCorrente = new ElezioneConsigliereDTO();
		else if (action.getSource().equals(acquistoTesseraPermesso))
			azioneCorrente = new AcquistoTesseraPermessoDTO();
		else if (action.getSource().equals(costruzioneAiutoRe))
			azioneCorrente = new CostruzioneAiutoReDTO();
		else if (action.getSource().equals(costruzioneTesseraPermesso))
			azioneCorrente = new CostruzioneAiutoReDTO();
		else if (action.getSource().equals(cambioTesseraPermesso))
			azioneCorrente = new CambioTesserePermessoDTO();
		else if(action.getSource().equals(elezioneConsigliereVeloce))
			azioneCorrente = new ElezioneConsigliereDTO();
		else if(action.getSource().equals(ingaggioAiutante))
			azioneCorrente=new IngaggioAiutanteDTO();
		else if(action.getSource().equals(secondaAzionePrincipale))
			azioneCorrente=new SecondaAzionePrincipaleDTO();
		
	}
}
