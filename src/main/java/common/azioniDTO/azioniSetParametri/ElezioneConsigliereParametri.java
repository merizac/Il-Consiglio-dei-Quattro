package common.azioniDTO.azioniSetParametri;

import client.grafica.Grafica;
import common.azioniDTO.ElezioneConsigliereDTO;
import common.gameDTO.BalconeDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.GameStateDTO;

public class ElezioneConsigliereParametri implements SetterParametri {
	
	private ElezioneConsigliereDTO azione;
	
	public ElezioneConsigliereParametri(ElezioneConsigliereDTO azione) {
		this.azione=azione;
	}
	
	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO){
		grafica.mostraMessaggio("Seleziona il consigliere dalla riserva\n");
		ConsigliereDTO consigliereScelto = grafica.scegliConsigliere(gameStateDTO.getConsiglieri());
		grafica.mostraMessaggio("Seleziona il balcone\n");
		BalconeDTO balconeScelto = grafica.scegliBalcone(gameStateDTO.getRegioni(),
				gameStateDTO.getPlanciaReDTO().getBalconeRe());
		
		azione.setConsigliereDTO(consigliereScelto);
		azione.setBalconeDTO(balconeScelto);
	}

}
