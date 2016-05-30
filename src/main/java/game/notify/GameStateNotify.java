package game.notify;

import bonus.Bonus;
import game.GameState;
import gameDTO.gameDTO.BonusDTO;
import gameDTO.gameDTO.CittàDTO;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.ReDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class GameStateNotify implements NotifyGiocatori {

	private static final long serialVersionUID = 4438277578283085786L;
	private GameStateDTO gameStateDTO;

	/**
	 * @param gameStateDTO
	 */
	public GameStateNotify(GameState gameState) {
		this.gameStateDTO = new GameStateDTO();
		this.gameStateDTO.inizializza(gameState);
	}
	
	@Override
	public void update(GameStateDTO gameStateDTO) {
		gameStateDTO.setCittà(this.gameStateDTO.getCittà());
		gameStateDTO.setConsiglieri(this.gameStateDTO.getConsiglieri());
		gameStateDTO.setPedinaRE(this.gameStateDTO.getPedinaRE());
		gameStateDTO.setPlanciaReDTO(this.gameStateDTO.getPlanciaReDTO());
		gameStateDTO.setRegioni(this.gameStateDTO.getRegioni());
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
		
		}
		
		
	
	}


