package common.gameDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import server.model.bonus.Bonus;
import server.model.game.CittàBonus;
import server.model.game.Emporio;

public class CittàBonusDTO extends CittàDTO implements Serializable{

	private static final long serialVersionUID = 6054052021956866160L;
	private List<Bonus> bonus;
	
	/**
	 * @return the bonus
	 */
	public List<Bonus> getBonus() {
		return bonus;
	}
	/**
	 * @param bonus the bonus to set
	 */
	public void setBonus(List<Bonus> bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * map a cittàBonus into a cittàBonusDTO
	 * @param cittàBonus
	 */
	public void inizializza(CittàBonus cittàBonus){
		this.setNome(cittàBonus.getNome());
		ColoreDTO coloreDTO=new ColoreDTO();
		coloreDTO.inizializza(cittàBonus.getColoreCittà());
		this.setColoreDTO(coloreDTO);
		this.setEmpori(new HashSet<>());
		for(Emporio e: cittàBonus.getEmpori()){
			this.getEmpori().add(e.getColore().getColore());
		}

		this.setBonus(cittàBonus.getBonus());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getNome()+" Colore: "+this.getColoreDTO().getColore()
				+" Empori: "+this.getEmpori()+" Bonus : "+bonus;
	}

}
