package common.azioniDTO.azioniSetParametri;

import java.util.List;

import client.grafica.Grafica;
import common.azioniDTO.BonusGettoneNDTO;
import common.gameDTO.CittàBonusDTO;
import common.gameDTO.GameStateDTO;

public class BonusGettoneParametri implements SetterParametri {

	private BonusGettoneNDTO bonusGettoneNDTO;

	public BonusGettoneParametri(BonusGettoneNDTO bonusGettoneNDTO) {
		this.bonusGettoneNDTO=bonusGettoneNDTO;
	}

	@Override
	public void setParametri(Grafica grafica, GameStateDTO gameStateDTO) {
		List<CittàBonusDTO> città;
		if(bonusGettoneNDTO.getNumeroGettoni()==1)
			città=grafica.scegliUnaCittà();
		else
			città=grafica.scegliDueCittà();
		
		bonusGettoneNDTO.setCittà(città);
			
	}

}
