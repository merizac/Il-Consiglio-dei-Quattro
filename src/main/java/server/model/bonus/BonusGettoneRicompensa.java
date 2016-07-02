package server.model.bonus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.gameDTO.CittàBonusDTO;
import server.model.azioni.Azione;
import server.model.azioni.azioniBonus.BonusGettoneN;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Emporio;
import server.model.game.GameState;
import server.model.notify.MessageNotify;

public class BonusGettoneRicompensa extends Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7078597933216059193L;
	private int numeroGettoni;
	private ArrayList<CittàBonus> cittàGiocatore;
	private CittàBonusDTO cittàDTO;
	
	public BonusGettoneRicompensa(){
		super();
	}

	public BonusGettoneRicompensa(int quantità) {
		this.numeroGettoni=quantità;
	}

	/**
	 * 
	 * @return the corresponding action
	 */
	public Azione getAzioneBonus(){
		BonusGettoneN bonusGettone = new BonusGettoneN();
		bonusGettone.setNumeroGettoni(numeroGettoni);
		return bonusGettone;
	}
	
	/**
	 * add the corresponding action of in a list of bonus in current player
	 */
	@Override
	public void usaBonus(GameState gameState) {
		Set<CittàBonus> città=new HashSet<>();
		for (Città c : gameState.getCittà()) {
			if (c.getEmpori().contains(new Emporio(gameState.getGiocatoreCorrente().getColoreGiocatore()))
					&& (c instanceof CittàBonus)) {
				città.add((CittàBonus) c);
			}
		}
		if(!città.isEmpty())
			gameState.getGiocatoreCorrente().getBonusNobiltà().add(this.getAzioneBonus());
		else
			gameState.notifyObserver(new MessageNotify("Hai vinto un bonus gettone ricompensa, ma non lo puoi utilizzare!\n"
						, Arrays.asList(gameState.getGiocatoreCorrente())));
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
	public void setCittàGiocatore(ArrayList<CittàBonus> cittàGiocatore) {
		if(cittàGiocatore==null)
			throw new NullPointerException("La lista di città è null");
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
		if(cittàDTO==null)
			throw new NullPointerException("La cittàDTO è null");
		this.cittàDTO = cittàDTO;
	}

	/**
	 * @param numeroGettoni the numeroGettoni to set
	 */
	public void setNumeroGettoni(int numeroGettoni) {
		if(numeroGettoni>2 || numeroGettoni<0) throw new IllegalArgumentException("il bonus può avere al massimo due gettoni");
		this.numeroGettoni = numeroGettoni;
	}
}
