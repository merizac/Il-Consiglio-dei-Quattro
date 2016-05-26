package mapping.gameToMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;



public class GameStateDTO implements Serializable  {

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
	 * @param città the città to set
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
	 * @param regioni the regioni to set
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
	 * @param pedinaRE the pedinaRE to set
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
	 * @param planciaReDTO the planciaReDTO to set
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
	 * @param consiglieri the consiglieri to set
	 */
	public void setConsiglieri(ArrayList<ConsigliereDTO> consiglieri) {
		this.consiglieri = consiglieri;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameStateDTO [mappa=" + città + "\nregioni=" + regioni + "\npedinaRE=" + pedinaRE + "\nplanciaReDTO="
				+ planciaReDTO + "\nconsiglieri=" + consiglieri + "]";
	}
	
	

}
