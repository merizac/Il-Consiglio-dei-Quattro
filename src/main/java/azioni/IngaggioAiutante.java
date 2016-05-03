package azioni;

import game.Partita;

public class IngaggioAiutante extends AzioneVeloce {

	public IngaggioAiutante(Partita partita) {
		super(partita);
		
	}

	@Override
	public boolean eseguiAzione() {
		if(partita.getGiocatoreCorrente().diminuisciRicchezza(3))
		{
			partita.getGiocatoreCorrente().getAiutanti().aggiungiAiutanti(1);
			return true;
		}
		return false;
	}
}
