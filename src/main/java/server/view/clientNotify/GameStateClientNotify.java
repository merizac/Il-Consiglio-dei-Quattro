package server.view.clientNotify;

import java.io.IOException;
import java.util.ArrayList;

import client.grafica.Grafica;
import common.gameDTO.GameStateDTO;

public class GameStateClientNotify implements ClientNotify {

	private static final long serialVersionUID = -1954917098455633180L;
	private GameStateDTO gameStateDTO;

	/**
	 * @param gameStateDTO
	 */
	public GameStateClientNotify(GameStateDTO gameStateDTO) {
		if (gameStateDTO == null)
			throw new NullPointerException("Il gamestateDTO non può essere null");
		this.gameStateDTO = gameStateDTO;
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {

		gameStateDTO.setAzioni(new ArrayList<>());
		gameStateDTO.setCittà(this.gameStateDTO.getCittà());
		gameStateDTO.setConsiglieri(this.gameStateDTO.getConsiglieri());
		gameStateDTO.setPedinaRE(this.gameStateDTO.getPedinaRE());
		gameStateDTO.setPlanciaReDTO(this.gameStateDTO.getPlanciaReDTO());
		gameStateDTO.setRegioni(this.gameStateDTO.getRegioni());
	}

	@Override
	public void stamp(Grafica grafica) throws IOException {
		grafica.mostraGame(gameStateDTO);
	}
}
