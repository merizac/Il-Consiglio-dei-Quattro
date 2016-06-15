package server.model.bonus;

import java.util.List;
import common.gameDTO.CittàBonusDTO;
import server.model.azioni.Azione;
import server.model.azioni.azioniBonus.BonusGettoneN;
import server.model.game.CittàBonus;
import server.model.game.GameState;

public class BonusGettoneRicompensa extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7078597933216059193L;
	private int numeroGettoni;
	private List<CittàBonus> cittàGiocatore;
	private CittàBonusDTO cittàDTO;
	
	public BonusGettoneRicompensa(){
		super();
	}

	public BonusGettoneRicompensa(int quantità) {
		this.numeroGettoni=quantità;
	}

	public Azione getAzioneBonus(){
		BonusGettoneN bonusGettone = new BonusGettoneN();
		bonusGettone.setNumeroGettoni(numeroGettoni);
		return bonusGettone;
	}
	
	@Override
	public void usaBonus(GameState gameState) {		
		gameState.getGiocatoreCorrente().getBonusNobiltà().add(this.getAzioneBonus());
	}	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cittàGiocatore == null) ? 0 : cittàGiocatore.hashCode());
		result = prime * result + numeroGettoni;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonusGettoneRicompensa other = (BonusGettoneRicompensa) obj;
		if (cittàGiocatore == null) {
			if (other.cittàGiocatore != null)
				return false;
		} else if (!cittàGiocatore.equals(other.cittàGiocatore))
			return false;
		if (numeroGettoni != other.numeroGettoni)
			return false;
		return true;
	}

	/**
	 * @return the numeroGettoni
	 */
	public int getNumeroGettoni() {
		return numeroGettoni;
	}

	/**
	 * @return the cittàGiocatore
	 */
	public List<CittàBonus> getCittàGiocatore() {
		return cittàGiocatore;
	}

	/**
	 * @param cittàGiocatore the cittàGiocatore to set
	 */
	public void setCittàGiocatore(List<CittàBonus> cittàGiocatore) {
		this.cittàGiocatore = cittàGiocatore;
	}

	/**
	 * @return the cittàDTO
	 */
	public CittàBonusDTO getCittàDTO() {
		return cittàDTO;
	}

	/**
	 * @param cittàDTO the cittàDTO to set
	 */
	public void setCittàDTO(CittàBonusDTO cittàDTO) {
		this.cittàDTO = cittàDTO;
	}

	/**
	 * @param numeroGettoni the numeroGettoni to set
	 */
	public void setNumeroGettoni(int numeroGettoni) {
		if(numeroGettoni>2) throw new IllegalArgumentException("il bonus può avere al massimo due gettoni");
		this.numeroGettoni = numeroGettoni;
	}
}