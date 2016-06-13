package client;

import java.util.List;

import common.azioniDTO.AzioneDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;
import common.gameDTO.OffertaDTO;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application implements Grafica {
	
	private Connessione connessione;
	private GameStateDTO gameStateDTO;

	@Override
	public void setConnessione(Connessione connessione) {
		this.connessione= connessione;
	}

	@Override
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraAzioni(List<AzioneDTO> azioni) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraClassifica(List<GiocatoreDTO> vincenti, List<GiocatoreDTO> perdenti) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraGame(GameStateDTO gameStateDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraGiocatore(GiocatoreDTO giocatoreDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraOfferte(List<OffertaDTO> offerte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	

}
