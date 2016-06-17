package client.grafica.gui;

import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.CambioTesserePermessoDTO;
import common.azioniDTO.CostruzioneAiutoReDTO;
import common.azioniDTO.ElezioneConsigliereDTO;
import common.azioniDTO.IngaggioAiutanteDTO;
import common.azioniDTO.SecondaAzionePrincipaleDTO;
import common.gameDTO.GameStateDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GUIGameController {

	private GameStateDTO gameStateDTO;
	private GUI gui;
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
