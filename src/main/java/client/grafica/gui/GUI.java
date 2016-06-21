package client.grafica.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.view.clientNotify.ClientNotify;

public class GUI extends Application implements Grafica {

	private Connessione connessione;
	private GameStateDTO gameStateDTO;
	private GUIGameController controller;
	private Stage finestra;

	@Override
	public void setConnessione(Connessione connessione) {
		this.connessione = connessione;
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
		try{
		controller.mostraTesserePermessoRegioni(gameStateDTO.getRegioni());}
		catch(Exception e){
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
		controller.mostraTesserePermesso(giocatoreDTO.getTesserePermesso());
		controller.mostraCartePolitica(giocatoreDTO.getCartePolitica());
		controller.mostraNomeGiocatore(giocatoreDTO.getNome());
		controller.mostraPuntiGiocatore(giocatoreDTO.getPunteggioVittoria());
		controller.mostraMoneteGiocatore(giocatoreDTO.getPunteggioRicchezza());
		controller.mostraAiutantiGiocatore(giocatoreDTO.getAiutanti());
		controller.mostraEmporiGiocatore(giocatoreDTO.getEmpori());
		controller.mostraTessereBonusGiocatore(giocatoreDTO.getTessereBonus());
		controller.mostraNobiltàGiocatore(giocatoreDTO.getPunteggioNobiltà());
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
		gameStateDTO.getAvversari().get(0).getTesserePermesso().add(gameStateDTO.getRegioni().get(0).getTesserePermessoScoperte().get(0));
		controller.mostraAvversario(gameStateDTO.getAvversari());
	}
	public ConsigliereDTO scegliConsigliere(List<ConsigliereDTO> consiglieri) {
		return null;
	}

	@Override
	public BalconeDTO scegliBalcone(List<RegioneDTO> regioni, BalconeDTO balconeRe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegioneDTO scegliRegione(List<RegioneDTO> regioni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TesseraPermessoDTO scegliTesseraRegione(List<TesseraPermessoDTO> tesserePermessoScoperte) {
		// TODO Auto-generated method stub
		return null;
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
	public CittàDTO scegliCittàBonus(Set<CittàBonusDTO> città, ColoreDTO coloreGiocatore) {
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
		// TODO Auto-generated method stub
		return null;
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
}
