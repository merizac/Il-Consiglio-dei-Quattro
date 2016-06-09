package gameDTO.azioniDTO;

import java.util.List;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.CittàBonusDTO;

public class BonusGettoneNDTO implements AzioneDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5454333598048906851L;
	private List<CittàBonusDTO> città;
	private int numeroGettoni;


	/**
	 * @return the città
	 */
	public List<CittàBonusDTO> getCittà() {
		return città;
	}

	/**
	 * @param città the città to set
	 */
	public void setCittà(List<CittàBonusDTO> città) {
		this.città = città;
	}
	
	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);	
	}

	/**
	 * @return the numeroGettoni
	 */
	public int getNumeroGettoni() {
		return numeroGettoni;
	}

	/**
	 * @param numeroGettoni the numeroGettoni to set
	 */
	public void setNumeroGettoni(int numeroGettoni) {
		if(numeroGettoni<=0)
			throw new IllegalArgumentException("Il numero dei gettoni deve essere un numero positivo non nullo");
		this.numeroGettoni = numeroGettoni;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BonusGettone [B3]";
	}

}
