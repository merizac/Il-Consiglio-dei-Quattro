package gameDTO.azioniDTO;

import game.azioni.Azione;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;

public class PescaCartaDTO implements AzioneDTO {

	private static final long serialVersionUID = 4395569912555731424L;

	@Override
	public Azione accept(AzioneVisitor azioneVisitor) {
		return azioneVisitor.visit(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "E' il tuo turno!\n"
				+ "Pesca una carta politica per iniziare il turno!  [Pesca]";
	}

}
