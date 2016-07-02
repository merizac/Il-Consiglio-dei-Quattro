package server.view.clientNotify;

import java.io.IOException;
import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;

public class GameStateClientNotify implements ClientNotify {

	private static final long serialVersionUID = -1954917098455633180L;
	private GameStateDTO gameStateDTO;

	/**
	 * gamestate notify
	 * 
	 * @param gameStateDTO
	 */
	public GameStateClientNotify(GameStateDTO gameStateDTO) {
		if (gameStateDTO == null)
			throw new NullPointerException("Il gamestateDTO non può essere null");
		this.gameStateDTO = gameStateDTO;
		System.out.println("bonus premio re client notify: " + this.gameStateDTO.getPlanciaReDTO().getBonusPremioRe());
	}

	/**
	 * show update in gamestate
	 */
	@Override
	public void stamp(Grafica grafica, GameStateDTO gameStateDTO) throws IOException {

		gameStateDTO.setNomeMappa(this.gameStateDTO.getNomeMappa());
		gameStateDTO.setBonusColore(this.gameStateDTO.getBonusColore());
		gameStateDTO.setCittà(this.gameStateDTO.getCittà());
		gameStateDTO.setConsiglieri(this.gameStateDTO.getConsiglieri());
		gameStateDTO.setPedinaRE(this.gameStateDTO.getPedinaRE());
		gameStateDTO.setPlanciaReDTO(this.gameStateDTO.getPlanciaReDTO());
		System.out.println("Client bonus re:" + gameStateDTO.getPlanciaReDTO().getBonusPremioRe());
		gameStateDTO.setRegioni(this.gameStateDTO.getRegioni());
		gameStateDTO.setAvversari(this.gameStateDTO.getAvversari());

		grafica.mostraGame(gameStateDTO);
	}
}
