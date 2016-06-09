package gameDTO.gameDTO;

import java.util.ArrayList;

import game.Balcone;
import game.Consigliere;

public class BalconeDTO {

	private ArrayList<ConsigliereDTO> consiglieri;

	/**
	 * @return the consiglieri
	 */
	public ArrayList<ConsigliereDTO> getConsiglieri() {
		return consiglieri;
	}

	/**
	 * @param consiglieri the consiglieri to set
	 */
	public void setConsiglieri(ArrayList<ConsigliereDTO> consiglieri) {
		this.consiglieri = consiglieri;
	}
	
	public void inizializza(Balcone balcone){
		for(Consigliere c: balcone.getConsigliere()){
			ConsigliereDTO consigliereDTO = new ConsigliereDTO();
			consigliereDTO.inizializza(c);
			this.getConsiglieri().add(consigliereDTO);
		}
	}
}
