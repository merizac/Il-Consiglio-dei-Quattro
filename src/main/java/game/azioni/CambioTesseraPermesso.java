package game.azioni;

import game.GameState;
import game.Regione;
import game.TesseraPermesso;

public class CambioTesseraPermesso extends AzioneVeloce {

	private final Regione regione;

	

	/**
	 * @param partita
	 * @param regione
	 */
	public CambioTesseraPermesso(GameState gameState, Regione regione) {
		super(gameState);
		this.regione=regione;
	}



	@Override
	public void eseguiAzione() {
		RimozioneCarte();
		SostituzioneCarte();
		setStatoTransizioneVeloce(); 
		
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



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cambio Tessera Permesso";
	}
	
	
	
}
