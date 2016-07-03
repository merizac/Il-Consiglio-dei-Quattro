package client.grafica;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import client.connessione.Connessione;
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

public interface Grafica {

	/**
	 * set the connection
	 */
	public void setConnessione(Connessione connessione);

	/**
	 * set the gameStateDTO
	 */
	public void setGameStateDTO(GameStateDTO gameStateDTO);

	/**
	 * show the actions available
	 */
	public void mostraAzioni(List<AzioneDTO> azioni);

	/**
	 * show the placement of the players
	 */
	public void mostraClassifica(List<GiocatoreDTO> vincenti, List<GiocatoreDTO> perdenti);

	/**
	 * show the game
	 */
	public void mostraGame(GameStateDTO gameStateDTO) throws IOException;

	/**
	 * show the currant state of the player
	 */
	public void mostraGiocatore(GiocatoreDTO giocatoreDTO);

	/**
	 * show a message from the server
	 */
	public void mostraMessaggio(String messaggio);

	/**
	 * show object in sale in the market state
	 */
	public void mostraOfferte(List<OffertaDTO> offerte);

	/**
	 * show players that aren't the current player
	 */
	public void mostraAvversario(GiocatoreDTO avversario);

	/**
	 * this method let the player choose a counselor which want to add into the
	 * balcony if the player insert a counselor that not exists, he will insert
	 * again the counselor
	 * 
	 * @return ConsigliereDTO choose from the player
	 */
	public ConsigliereDTO scegliConsigliere(List<ConsigliereDTO> consiglieri);

	/**
	 * this method let the player chose the balcony where he want to elect a
	 * counselor
	 * 
	 * @param regioni
	 * @param balconeRe
	 * @param stdIn
	 * @return the balcony DTO selected
	 */
	public BalconeDTO scegliBalcone(List<RegioneDTO> regioni, BalconeDTO balconeRe);

	/**
	 * this method let the player choose the region if the player insert a
	 * region that not exists, he will insert again the region
	 * 
	 * @return RegioneDTO choose from the player
	 */
	public RegioneDTO scegliRegione(List<RegioneDTO> regioni);

	/**
	 * this method let the player to chose a permit tile of the region selected
	 * 
	 * @param tessere
	 * @param stdIn
	 * @return the permit tile DTO selected
	 */
	public TesseraPermessoDTO scegliTesseraRegione(List<TesseraPermessoDTO> tesserePermessoScoperte,
			RegioneDTO regione);

	/**
	 * this method let the player chose the price for an offer
	 * 
	 * @param stdIn
	 * @return the price
	 */
	public int scegliPrezzo();

	/**
	 * this method let the player to chose a politic card from the politic cards
	 * of his hand
	 * 
	 * @param cartePolitica
	 * @param stdIn
	 * @return the politic card DTO
	 */
	public CartaPoliticaDTO scegliCarta(List<CartaPoliticaDTO> cartePolitica);

	/**
	 * this method let the player chose a city where he want to build an
	 * emporium
	 * 
	 * @param città
	 * @param coloreGiocatore
	 * @param stdIn
	 * @return the city DTO selected
	 */
	public CittàDTO scegliCittà(Set<? extends CittàDTO> città, ColoreDTO coloreGiocatore);

	/**
	 * this method let the player to chose a permit tile from his permit tiles
	 * not yet used
	 * 
	 * @param list
	 * @param stdIn
	 * @return the permit tile DTO selected
	 */
	public TesseraPermessoDTO scegliTesseraGiocatore(List<TesseraPermessoDTO> list);

	/**
	 * this method let the player chose cards that hew want to use
	 * 
	 * @param carteGiocatore
	 * @param stdIn
	 * @return cards DTO selected
	 */
	public List<CartaPoliticaDTO> scegliCarte(List<CartaPoliticaDTO> carteGiocatore);

	/**
	 * player choose which object want to buy
	 */
	public MarketableDTO scegliMarketable();

	/**
	 * choose offer
	 */
	public int scegliOfferta(List<OffertaDTO> offerte);

	/**
	 * choose one city with bonus where the player has build. for interactive
	 * nobility bonus
	 */
	public List<CittàBonusDTO> scegliUnaCittà();

	/**
	 * choose two city with bonus where the player has build. bonus can not be
	 * the same. for interactive bonus of nobility track
	 */
	public List<CittàBonusDTO> scegliDueCittà();

	/**
	 * notify start of market state
	 */
	public void startMarket();

	/**
	 * show market player
	 * 
	 * @param giocatoreDTO
	 */
	public void mostraGiocatoreMarket(GiocatoreDTO giocatoreDTO);

	/**
	 * notify the end of market state
	 */
	public void fineMarket();

	/**
	 * choose map for start game
	 */
	public void scegliMappa();

	/**
	 * player can choose if win bonus from one of his discovery or covery permit
	 * tile. For interactive bonus in nobility track
	 */
	public TesseraPermessoDTO scegliTesseraPermessoUsataONonUsata(List<TesseraPermessoDTO> tessere,
			List<TesseraPermessoDTO> tessereUsate);

	/**
	 * show message of market
	 */
	void mostraMessaggioMarket(String messaggio);
}
