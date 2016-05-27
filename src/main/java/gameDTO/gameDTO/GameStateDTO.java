package gameDTO.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import game.Città;
import game.CittàBonus;
import game.Consigliere;
import game.GameState;
import game.Regione;

public class GameStateDTO implements Serializable {

	private static final long serialVersionUID = -8629825962043239713L;
	private Set<CittàDTO> città;
	private ArrayList<RegioneDTO> regioni;
	private ReDTO pedinaRE;
	private PlanciaReDTO planciaReDTO;
	private ArrayList<ConsigliereDTO> consiglieri;

	/**
	 * @return the città
	 */
	public Set<CittàDTO> getCittà() {
		return città;
	}

	/**
	 * @param città
	 *            the città to set
	 */
	public void setCittà(Set<CittàDTO> città) {
		this.città = città;
	}

	/**
	 * @return the regioni
	 */
	public ArrayList<RegioneDTO> getRegioni() {
		return regioni;
	}

	/**
	 * @param regioni
	 *            the regioni to set
	 */
	public void setRegioni(ArrayList<RegioneDTO> regioni) {
		this.regioni = regioni;
	}

	/**
	 * @return the pedinaRE
	 */
	public ReDTO getPedinaRE() {
		return pedinaRE;
	}

	/**
	 * @param pedinaRE
	 *            the pedinaRE to set
	 */
	public void setPedinaRE(ReDTO pedinaRE) {
		this.pedinaRE = pedinaRE;
	}

	/**
	 * @return the planciaReDTO
	 */
	public PlanciaReDTO getPlanciaReDTO() {
		return planciaReDTO;
	}

	/**
	 * @param planciaReDTO
	 *            the planciaReDTO to set
	 */
	public void setPlanciaReDTO(PlanciaReDTO planciaReDTO) {
		this.planciaReDTO = planciaReDTO;
	}

	/**
	 * @return the consiglieri
	 */
	public ArrayList<ConsigliereDTO> getConsiglieri() {
		return consiglieri;
	}

	/**
	 * @param consiglieri
	 *            the consiglieri to set
	 */
	public void setConsiglieri(ArrayList<ConsigliereDTO> consiglieri) {
		this.consiglieri = consiglieri;
	}

	public void inizializza(GameState gameState) {
		this.città = new HashSet<>();
		for (Città c : gameState.getCittà()) {
			if (c instanceof Città) {
				CittàDTO cittàDTO = new CittàDTO();
				cittàDTO.inizializza(c);
				città.add(cittàDTO);
			} else if (c instanceof CittàBonus) {
				CittàBonusDTO cittàBonusDTO = new CittàBonusDTO();
				cittàBonusDTO.inizializza(c);
				città.add(cittàBonusDTO);
			}
		}
		this.regioni = new ArrayList<>();
		for (Regione r : gameState.getRegioni()) {
			RegioneDTO regioneDTO = new RegioneDTO();
			regioneDTO.inizializza(r);
			regioni.add(regioneDTO);
		}
		ReDTO pedinaRe = new ReDTO();
		pedinaRe.inizializza(gameState.getPedinaRe());
		this.setPedinaRE(pedinaRe);

		PlanciaReDTO planciaReDTO = new PlanciaReDTO();
		planciaReDTO.inizializza(gameState.getPlanciaRe());
		this.setPlanciaReDTO(planciaReDTO);

		this.consiglieri = new ArrayList<>();
		for (Consigliere c : gameState.getConsiglieri()) {
			ConsigliereDTO consigliereDTO = new ConsigliereDTO();
			consigliereDTO.inizializza(c);
			consiglieri.add(consigliereDTO);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameStateDTO [mappa=" + città + "\nregioni=" + regioni + "\npedinaRE=" + pedinaRE + "\nplanciaReDTO="
				+ planciaReDTO + "\nconsiglieri=" + consiglieri + "]";
	}

}
