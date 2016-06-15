package common.gameDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.azioniDTO.AzioneDTO;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.game.Regione;

public class GameStateDTO implements Serializable {

	private static final long serialVersionUID = -8629825962043239713L;
	private Set<CittàDTO> città;
	private List<RegioneDTO> regioni;
	private ReDTO pedinaRE;
	private PlanciaReDTO planciaReDTO;
	private List<ConsigliereDTO> consiglieri;
	private List<AzioneDTO> azioniDisponibili;
	private GiocatoreDTO giocatoreDTO;
	private List<OffertaDTO> offerte;

	public void inizializza(GameState gameState) {
		this.città = new HashSet<>();
		for (Città c : gameState.getCittà()) {
			if (c instanceof CittàBonus) {
				CittàBonusDTO cittàBonusDTO = new CittàBonusDTO();
				cittàBonusDTO.inizializza((CittàBonus) c);
				città.add(cittàBonusDTO);
			} else {
				CittàDTO cittàDTO = new CittàDTO();
				cittàDTO.inizializza(c);
				città.add(cittàDTO);
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
		planciaReDTO = new PlanciaReDTO();
		planciaReDTO.inizializza(gameState.getPlanciaRe());
		this.setPlanciaReDTO(planciaReDTO);
		this.consiglieri = new ArrayList<>();
		for (Consigliere c : gameState.getConsiglieri()) {
			ConsigliereDTO consigliereDTO = new ConsigliereDTO();
			consigliereDTO.inizializza(c);
			consiglieri.add(consigliereDTO);
		}
		this.offerte = new ArrayList<>();
	}

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
	public List<RegioneDTO> getRegioni() {
		return regioni;
	}

	/**
	 * @param regioni
	 *            the regioni to set
	 */
	public void setRegioni(List<RegioneDTO> regioni) {
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
	public List<ConsigliereDTO> getConsiglieri() {
		return consiglieri;
	}

	/**
	 * @param consiglieri
	 *            the consiglieri to set
	 */
	public void setConsiglieri(List<ConsigliereDTO> consiglieri) {
		this.consiglieri = consiglieri;
	}

	public void setAzioni(List<AzioneDTO> azioni) {
		this.azioniDisponibili = azioni;
	}

	/**
	 * @return the azioniDisponibili
	 */
	public List<AzioneDTO> getAzioniDisponibili() {
		return azioniDisponibili;
	}

	public GiocatoreDTO getGiocatoreDTO() {
		return giocatoreDTO;
	}

	/**
	 * @param giocatoreDTO
	 *            the giocatoreDTO to set
	 */
	public void setGiocatoreDTO(GiocatoreDTO giocatoreDTO) {
		this.giocatoreDTO = giocatoreDTO;
	}

	/**
	 * @return the offerte
	 */
	public void setOfferte(List<OffertaDTO> offerte) {
		this.offerte = offerte;
	}

	/**
	 * @return the offerte
	 */
	public List<OffertaDTO> getOfferte() {
		return offerte;
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
