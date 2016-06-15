package client.grafica;

import java.util.List;

import client.connessione.Connessione;
import common.azioniDTO.AzioneDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;
import common.gameDTO.OffertaDTO;

public interface Grafica extends Runnable{
	
	public void setConnessione(Connessione connessione);

	public void setGameStateDTO(GameStateDTO gameStateDTO);

	public void mostraAzioni(List<AzioneDTO> azioni);

	public void mostraClassifica(List<GiocatoreDTO> vincenti, List<GiocatoreDTO> perdenti);

	public void mostraGame(GameStateDTO gameStateDTO);

	public void mostraGiocatore(GiocatoreDTO giocatoreDTO);

	public void mostraMessaggio(String messaggio);

	public void mostraOfferte(List<OffertaDTO> offerte);
}
