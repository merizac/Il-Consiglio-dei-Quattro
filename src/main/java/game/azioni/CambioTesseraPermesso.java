package game.azioni;

import game.GameState;
import game.Regione;
import game.TesseraPermesso;

public class CambioTesseraPermesso extends AzioneVeloce {

	private Regione regione;



	@Override
	public void eseguiAzione(GameState gameState) {
		RimozioneCarte();
		SostituzioneCarte();
		setStatoTransizioneVeloce(gameState); 
		
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
	
}
