package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioniSetParametri.ElezioneConsigliereVeloceParametri;
import common.gameDTO.BalconeDTO;
import common.gameDTO.ConsigliereDTO;
import server.model.azioni.Azione;
import utility.ParameterException;

public class ElezioneConsigliereVeloceDTO implements AzioneDTO, AzioneParametri {

	/**
	 * serial version for serializable object
	 */
	private static final long serialVersionUID = -4829189371404681263L;
	/**
	 * balcony
	 */
	private BalconeDTO balcone;
	/**
	 * Councillor from the stock
	 */
	private ConsigliereDTO consigliere;

	/**
	 * @return the balcone
	 */
	public BalconeDTO getBalcone() {
		return balcone;
	}

	/**
	 * @param regione
	 *            the regione to set
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
	 * @param consigliere
	 *            the consigliere to set
	 */
	public void setConsigliere(ConsigliereDTO consigliere) {
		this.consigliere = consigliere;
	}

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) throws ParameterException {
		return azioneVisitor.visit(this);
	}

	/*
	 * to string
	 */
	@Override
	public String toString() {
		return "Eleggere un consigliere  [V3]";
	}

	/**
	 * @param balcone
	 *            the balcone to set
	 */
	public void setBalcone(BalconeDTO balcone) {
		this.balcone = balcone;
	}

	/**
	 * set parameter
	 */
	@Override
	public ElezioneConsigliereVeloceParametri parametri() {
		return new ElezioneConsigliereVeloceParametri(this);
	}

}
