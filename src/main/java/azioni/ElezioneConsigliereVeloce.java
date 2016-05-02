package azioni;

import game.Balcone;
import game.Consigliere;
import game.Partita;

public class ElezioneConsigliereVeloce extends AzioneVeloce {
	
	private final Balcone balcone;
	private final Consigliere consigliere;

	public ElezioneConsigliereVeloce(Partita partita, Balcone balcone, Consigliere consigliere) {
		super(partita);
		this.balcone = balcone;
		this.consigliere = consigliere;
	}

	@Override
	public boolean eseguiAzione() {
		return true;
	}
}
