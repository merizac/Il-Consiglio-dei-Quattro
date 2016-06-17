package common.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import server.model.game.Balcone;
import server.model.game.Consigliere;

public class BalconeDTO implements Serializable{

	private static final long serialVersionUID = -6523476700234911129L;
	private List<ConsigliereDTO> consiglieri;

	/**
	 * @return the consiglieri
	 */
	public List<ConsigliereDTO> getConsiglieri() {
		return consiglieri;
	}

	/**
	 * @param consiglieri the consiglieri to set
	 */
	public void setConsiglieri(List<ConsigliereDTO> consiglieri) {
		this.consiglieri = consiglieri;
	}
	
	/**
	 * map the balcone to the balconeDTO
	 * @param balcone
	 */
	public void inizializza(Balcone balcone){
		this.consiglieri=new ArrayList<>();
		for(Consigliere c: balcone.getConsigliere()){
			ConsigliereDTO consigliereDTO = new ConsigliereDTO();
			consigliereDTO.inizializza(c);
			this.getConsiglieri().add(consigliereDTO);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BalconeDTO [consiglieri=" + consiglieri + "]";
	}
}
