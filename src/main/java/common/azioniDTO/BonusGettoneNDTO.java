package common.azioniDTO;

import java.util.List;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioniSetParametri.BonusGettoneParametri;
import common.gameDTO.CittàBonusDTO;
import server.model.azioni.azioniBonus.BonusGettoneN;
import utility.ParameterException;


public class BonusGettoneNDTO implements AzioneDTO, AzioneParametri {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = -5454333598048906851L;
	/**
	 * list of cities with bonus
	 */
	private List<CittàBonusDTO> città;
	/**
	 * how many bonus of cities the player can win. can be one or two
	 */
	private int numeroGettoni;

	/**
	 * @return the città
	 */
	public List<CittàBonusDTO> getCittà() {
		return città;
	}

	/**
	 * @param città
	 *            the città to set
	 */
	public void setCittà(List<CittàBonusDTO> città) {
		this.città = città;
	}

	@Override
	public BonusGettoneN accept(AzioneVisitor azioneVisitor) throws ParameterException{
		return azioneVisitor.visit(this);
	}

	/**
	 * @return the numeroGettoni
	 */
	public int getNumeroGettoni() {
		return numeroGettoni;
	}

	/**
	 * @param numeroGettoni
	 *            the numeroGettoni to set
	 */
	public void setNumeroGettoni(int numeroGettoni) {
		if (numeroGettoni <= 0)
			throw new IllegalArgumentException("Il numero dei gettoni deve essere un numero positivo non nullo");
		this.numeroGettoni = numeroGettoni;
	}

	/*
	 * to string
	  */
	@Override
	public String toString() {
			return "Bonus Gettone [B3]";
	}

	/**
	 * set parameter
	 */
	@Override
	public BonusGettoneParametri parametri() {
		return new BonusGettoneParametri(this);
	}

}
