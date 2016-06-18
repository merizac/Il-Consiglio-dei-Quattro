package client.grafica.gui;

import java.util.List;

import common.gameDTO.CartaPoliticaDTO;
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
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class GUIGameController {

	private GameStateDTO gameStateDTO;
	private GUI gui;
	
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
			
			@Override
			public void run() {
				try{
					for(CartaPoliticaDTO c:cartePolitica){
						ImageView image = new ImageView();
						image.setFitHeight(60);
						image.setPreserveRatio(true);
						image.setImage(new Image(getClass().getResource("/client/grafica/gui/css/politicCard/"+c.getColore()+".png").toExternalForm()));
						carte.getChildren().add(image);
					}}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			public Runnable setCarte (HBox carte){
				this.carte= carte;
				return this;
			}
		}.setCarte(this.cartePolitica));	
	}


	public void mostraTesserePermesso(List<TesseraPermessoDTO> tesserePermesso) {
		Platform.runLater(new Runnable() {
			
			HBox tessere;
			
			@Override
			public void run() {
				try{
					for(int i=0; i<tesserePermesso.size(); i++){
						ImageView image = new ImageView();
						image.setFitHeight(60);
						image.setPreserveRatio(true);
						image.setImage(new Image(getClass().getResource("/client/grafica/gui/css/permitTile/PermitWhite.png").toExternalForm()));
						tessere.getChildren().add(image);
					}}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			
			public Runnable setTessere(HBox tessere){
				this.tessere= tessere;
				return this;
			}
		}.setTessere(this.tesserePermesso));	
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
