package game.macchinaStati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game.GameState;
import game.notify.AzioniNotify;

public class Stato11 implements Stato {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2095271621049102185L;
	private List<String> azioni;

	public Stato11(GameState gameState) {
		System.out.println("stato11");
		riempiAzioni();
		gameState.notifyObserver(new AzioniNotify(this.getAzioni(), Arrays.asList(gameState.getGiocatoreCorrente())));
	}
	
	@Override
	public void transizionePrincipale(GameState gameState){
		if(!gameState.isBonusAzionePrincipale()){
		gameState.setStato(new Stato01(gameState));
		}
		else
			gameState.setBonusAzionePrincipale(false);
	}
	
	@Override
	public void transizioneVeloce(GameState gameState){
		gameState.setStato(new Stato10(gameState));
	}
	
	@Override
	public List<String> getAzioni() {
		return azioni;
	}


	public void riempiAzioni(){
		this.azioni = new ArrayList<String>();
		azioni.add("AZIONI PRINCIPALI :");
		azioni.add("Eleggere un consigliere [P1]");
		azioni.add("Acquistare una tessera permesso di costruzione [P2]");
		azioni.add("Costruire un emporio usando una tessera permesso [P3]");
		azioni.add("costruire un emporio con l'aiuto del re [P4]\n");
		azioni.add("AZIONI VELOCI :");
		azioni.add("Ingaggiare un aiutante [V1]");
		azioni.add("Cambiare le tessere permesso di costruzione [V2]");
		azioni.add("Mandare un aiutante ad eleggere un consigliere [V3]");
	}

}
