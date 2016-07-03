package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.ElezioneConsigliereVeloceDTO;
import common.gameDTO.BalconeDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.GameStateDTO;

public class ElezioneConsigliereVeloceParametri implements SetterParametri {

	private ElezioneConsigliereVeloceDTO elezioneConsigliereVeloceDTO;

	/**
	 * action of ElezioneConsigliereVeloce
	 * 
	 * @param elezioneConsigliereVeloceDTO
	 */
	public ElezioneConsigliereVeloceParametri(ElezioneConsigliereVeloceDTO elezioneConsigliereVeloceDTO) {
		this.elezioneConsigliereVeloceDTO = elezioneConsigliereVeloceDTO;
	}

	/**
	 * this method set parameters for the action ElezioneConsigliere in
	 * particular set the councilior to add at the balcony and the balcony
	 */
	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {

		grafica.mostraMessaggio("Seleziona il consigliere dalla riserva\n");
		ConsigliereDTO consigliereScelto = grafica.scegliConsigliere(gameStateDTO.getConsiglieri());
		grafica.mostraMessaggio("Seleziona il balcone\n");
		BalconeDTO balconeScelto = grafica.scegliBalcone(gameStateDTO.getRegioni(),
				gameStateDTO.getPlanciaReDTO().getBalconeRe());
		elezioneConsigliereVeloceDTO.setConsigliere(consigliereScelto);
		elezioneConsigliereVeloceDTO.setBalcone(balconeScelto);
	}

}
