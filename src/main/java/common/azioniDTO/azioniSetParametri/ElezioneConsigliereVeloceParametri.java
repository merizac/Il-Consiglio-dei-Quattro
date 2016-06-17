package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.ElezioneConsigliereVeloceDTO;
import common.gameDTO.BalconeDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.GameStateDTO;

public class ElezioneConsigliereVeloceParametri implements SetterParametri {

	private ElezioneConsigliereVeloceDTO elezioneConsigliereVeloceDTO;

	public ElezioneConsigliereVeloceParametri(ElezioneConsigliereVeloceDTO elezioneConsigliereVeloceDTO) {
		this.elezioneConsigliereVeloceDTO=elezioneConsigliereVeloceDTO;
	}

	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		ConsigliereDTO consigliereScelto = grafica.scegliConsigliere(gameStateDTO.getConsiglieri());
		BalconeDTO balconeScelto = grafica.scegliBalcone(gameStateDTO.getRegioni(),
				gameStateDTO.getPlanciaReDTO().getBalconeRe());
		elezioneConsigliereVeloceDTO.setConsigliere(consigliereScelto);
		elezioneConsigliereVeloceDTO.setBalcone(balconeScelto);
	}

}
