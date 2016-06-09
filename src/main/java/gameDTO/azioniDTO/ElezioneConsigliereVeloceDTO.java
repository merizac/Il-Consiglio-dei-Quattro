package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.gameDTO.BalconeDTO;
import gameDTO.gameDTO.ConsigliereDTO;

public class ElezioneConsigliereVeloceDTO implements AzioneDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4829189371404681263L;
	private BalconeDTO balcone;
	private ConsigliereDTO consigliere;
	
	/**
	 * @return the balcone
	 */
	public BalconeDTO getBalcone() {
		return balcone;
	}
	/**
	 * @param regione the regione to set
	 */
	public void setRegione(BalconeDTO balcone) {
		this.balcone = balcone;
	}
	/**
	 * @return the consigliere
	 */
	public ConsigliereDTO getConsigliere() {
		return consigliere;
	}
	/**
	 * @param consigliere the consigliere to set
	 */
	public void setConsigliere(ConsigliereDTO consigliere) {
		this.consigliere = consigliere;
	}
	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Eleggere un consigliere  [V3]";
	}
	/**
	 * @param balcone the balcone to set
	 */
	public void setBalcone(BalconeDTO balcone) {
		this.balcone = balcone;
	}
	
}
