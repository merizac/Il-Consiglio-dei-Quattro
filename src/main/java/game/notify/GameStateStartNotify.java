package game.notify;

import bonus.Bonus;
import game.GameState;
import game.Giocatore;
import gameDTO.gameDTO.CittàDTO;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class GameStateStartNotify implements NotifyGiocatori {

	private static final long serialVersionUID = 1799063332237801318L;
	private GameStateDTO gameStateDTO;
	private GiocatoreDTO giocatoreDTO;
	
	
	/**
	 * @param gameStateDTO
	 * @param giocatoreDTO
	 */
	public GameStateStartNotify(GameState gameState) {
		this.gameStateDTO = new GameStateDTO();
		this.gameStateDTO.inizializza(gameState);
		this.giocatoreDTO = new GiocatoreDTO();
	}

	public void setGiocatoreDTO(Giocatore giocatore){
		this.giocatoreDTO.inizializza(giocatore);
	}
	@Override
	public void update(GameStateDTO gameStateDTO){
		gameStateDTO.setCittà(this.gameStateDTO.getCittà());
		gameStateDTO.setConsiglieri(this.gameStateDTO.getConsiglieri());
		gameStateDTO.setPedinaRE(this.gameStateDTO.getPedinaRE());
		gameStateDTO.setPlanciaReDTO(this.gameStateDTO.getPlanciaReDTO());
		gameStateDTO.setRegioni(this.gameStateDTO.getRegioni());
	}

	public void update(GiocatoreDTO giocatoreDTO){
		giocatoreDTO.setAiutanti(this.giocatoreDTO.getAiutanti());
		giocatoreDTO.setCartePolitica(this.giocatoreDTO.getCartePolitica());
		giocatoreDTO.setColoreGiocatore(this.giocatoreDTO.getColoreGiocatore());
		giocatoreDTO.setEmpori(this.giocatoreDTO.getEmpori());
		giocatoreDTO.setPunteggioNobiltà(this.giocatoreDTO.getPunteggioNobiltà());
		giocatoreDTO.setPunteggioRicchezza(this.giocatoreDTO.getPunteggioRicchezza());
		giocatoreDTO.setPunteggioVittoria(this.giocatoreDTO.getPunteggioVittoria());
	}
	@Override
	public void stamp() {
		
		for(CittàDTO c: gameStateDTO.getCittà()){
		System.out.println(c);
		}
	
	for(RegioneDTO r: gameStateDTO.getRegioni()){
		System.out.println(r);
		System.out.println("Balcone" + r);
		for(ConsigliereDTO cons: r.getBalcone()){
			System.out.println(cons);
		}
		System.out.println("Tessere Permesso Scoperte nella regione "+ r);
		for (TesseraPermessoDTO t: r.getTesserePermessoScoperte()){
			System.out.println(t);
		}
		System.out.println(r.getBonusRegione());	
		
	}
	
	System.out.println("Balcone Re:");
	
	for (ConsigliereDTO consRe: gameStateDTO.getPlanciaReDTO().getBalconeRe()){
		System.out.println(consRe);
	}
	System.out.println(gameStateDTO.getPedinaRE());
	
	for(Bonus b: gameStateDTO.getPlanciaReDTO().getBonusPremioRe())
		System.out.println(b);

	System.out.println(giocatoreDTO);
	}
		
}
