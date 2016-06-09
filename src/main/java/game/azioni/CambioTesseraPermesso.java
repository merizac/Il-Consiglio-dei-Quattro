package game.azioni;

import java.util.Arrays;

import game.GameState;
import game.Regione;
import game.TesseraPermesso;
import game.notify.MessageNotify;
import game.notify.GameStateNotify;
import game.notify.GiocatoreNotify;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.CambioTesserePermessoDTO;

public class CambioTesseraPermesso extends AzioneVeloce {

	private final int ID=6;
	private Regione regione;



	@Override
	public void eseguiAzione(GameState gameState) {
		if(gameState.getGiocatoreCorrente().getAiutanti().getAiutante()>0){
			gameState.getGiocatoreCorrente().getAiutanti().togliAiutanti(1);
			RimozioneCarte();
			SostituzioneCarte();
			gameState.notifyObserver(new GameStateNotify(gameState, gameState.getGiocatori()));
			gameState.notifyObserver(new GiocatoreNotify(gameState.getGiocatoreCorrente(), Arrays.asList(gameState.getGiocatoreCorrente())));
			setStatoTransizioneVeloce(gameState); 
		}
		else{
			gameState.notifyObserver(new MessageNotify("Errore: non hai abbastanza aiutanti", Arrays.asList(gameState.getGiocatoreCorrente())));
			return;
		}
		
		
	}
	
	/**
	 * remove cards from TesserePermesseScoperte and add to MazzoTesserePermesso
	 */
	private void RimozioneCarte(){
		
		int size=regione.getTesserePermessoScoperte().size();
		for(int i=0; i<size; i++){
			TesseraPermesso permesso=regione.getTesserePermessoScoperte().remove(0);
			regione.getMazzoTesserePermesso().aggiungiCarta(permesso);
		}
	}
	
	/**
	 * remove cards from MazzoTesserePermesso and add cards to TesserePermessoScoperte
	 */
	private void SostituzioneCarte(){
	
		for(int i=0; i<2; i++){
			TesseraPermesso tessera=regione.getMazzoTesserePermesso().pescaCarte();
			regione.getTesserePermessoScoperte().add(tessera);
		}
	
		

		
	}

	/**
	 * @param regione the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}

	@Override
	public AzioneDTO getAzioneDTO() {
		return new CambioTesserePermessoDTO();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CambioTesseraPermesso other = (CambioTesseraPermesso) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
}
