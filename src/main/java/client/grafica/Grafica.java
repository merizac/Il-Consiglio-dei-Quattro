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
import server.view.clientNotify.ClientNotify;

public interface Grafica {

	public void setConnessione(Connessione connessione);

	public void setGameStateDTO(GameStateDTO gameStateDTO);

	public void mostraAzioni(List<AzioneDTO> azioni);

	public void mostraClassifica(List<GiocatoreDTO> vincenti, List<GiocatoreDTO> perdenti);

	public void mostraGame(GameStateDTO gameStateDTO) throws IOException;

	public void mostraGiocatore(GiocatoreDTO giocatoreDTO);

	public void mostraMessaggio(String messaggio);

	public void mostraOfferte(List<OffertaDTO> offerte);

	public void notify(ClientNotify notify);
	public void mostraAvversario(GiocatoreDTO avversario);
	public ConsigliereDTO scegliConsigliere(List<ConsigliereDTO> consiglieri);

	public BalconeDTO scegliBalcone(List<RegioneDTO> regioni, BalconeDTO balconeRe);

	public RegioneDTO scegliRegione(List<RegioneDTO> regioni);

	public TesseraPermessoDTO scegliTesseraRegione(List<TesseraPermessoDTO> tesserePermessoScoperte);

	public int scegliPrezzo();

	public CartaPoliticaDTO scegliCarta(List<CartaPoliticaDTO> cartePolitica);

	public CittàDTO scegliCittàBonus(Set<CittàBonusDTO> città, ColoreDTO coloreGiocatore, String input);

	public CittàDTO scegliCittà(Set<? extends CittàDTO> città, ColoreDTO coloreGiocatore);

	public TesseraPermessoDTO scegliTesseraGiocatore(List<TesseraPermessoDTO> list);

	public List<CartaPoliticaDTO> scegliCarte(List<CartaPoliticaDTO> carteGiocatore);

	public MarketableDTO scegliMarketable();

	public int scegliOfferta(List<OffertaDTO> offerte);

	public int scegliUsataONonUsata();

	public List<CittàBonusDTO> scegliUnaCittà();

	public List<CittàBonusDTO> scegliDueCittà();

	public void startMarket();

	public void mostraGiocatoreMarket(GiocatoreDTO giocatoreDTO);

	public void fineMarket();
}
