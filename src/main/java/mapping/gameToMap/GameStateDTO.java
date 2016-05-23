package mapping.gameToMap;

import java.io.Serializable;
import java.util.ArrayList;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import game.Colore;


public class GameStateDTO implements Serializable  {

	private static final long serialVersionUID = -8629825962043239713L;
	private UndirectedGraph<CittàDTO, DefaultEdge> mappa;
	private ArrayList<RegioneDTO> regioni;
	private ReDTO pedinaRE;
	private PlanciaReDTO planciaReDTO;
	private ArrayList<Colore> consiglieri;
	
	/**
	 * @return the mappa
	 */
	public UndirectedGraph<CittàDTO, DefaultEdge> getMappa() {
		return mappa;
	}
	/**
	 * @param mappa the mappa to set
	 */
	public void setMappa(UndirectedGraph<CittàDTO, DefaultEdge> mappa) {
		this.mappa = mappa;
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
	public ArrayList<Colore> getConsiglieri() {
		return consiglieri;
	}
	/**
	 * @param consiglieri the consiglieri to set
	 */
	public void setConsiglieri(ArrayList<Colore> consiglieri) {
		this.consiglieri = consiglieri;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameStateDTO [mappa=" + mappa + "\nregioni=" + regioni + "\npedinaRE=" + pedinaRE + "\nplanciaReDTO="
				+ planciaReDTO + "\nconsiglieri=" + consiglieri + "]";
	}
	
	

}
