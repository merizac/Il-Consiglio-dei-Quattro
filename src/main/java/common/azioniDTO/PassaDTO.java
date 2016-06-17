package common.azioniDTO;

import common.azioniDTO.azioneVisitor.AzioneVisitor;
import server.model.azioni.Azione;

public class PassaDTO implements AzioneDTO {

	private static final long serialVersionUID = -4736599386993319723L;

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Passa  [Passa]";
	}

}
