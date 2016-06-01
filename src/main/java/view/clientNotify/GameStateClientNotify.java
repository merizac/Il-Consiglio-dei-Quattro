package view.clientNotify;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import bonus.Bonus;
import gameDTO.gameDTO.CittàDTO;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class GameStateClientNotify implements ClientNotify {

	private static final long serialVersionUID = -1954917098455633180L;
	private GameStateDTO gameStateDTO;

	/**
	 * @param gameStateDTO
	 */
	public GameStateClientNotify(GameStateDTO gameStateDTO) {
		if (gameStateDTO == null)
			throw new NullPointerException("Il gamestateDTO non può essere null");
		this.gameStateDTO = gameStateDTO;
	}

	@Override
	public void update(GameStateDTO gameStateDTO) {
		
		gameStateDTO.setAzioni(new ArrayList<>());
		gameStateDTO.setCittà(this.gameStateDTO.getCittà());
		gameStateDTO.setConsiglieri(this.gameStateDTO.getConsiglieri());
		gameStateDTO.setPedinaRE(this.gameStateDTO.getPedinaRE());
		gameStateDTO.setPlanciaReDTO(this.gameStateDTO.getPlanciaReDTO());
		gameStateDTO.setRegioni(this.gameStateDTO.getRegioni());
	}

	@Override
	public void stamp() {
		System.out.println("\nCittà\n");
		for (CittàDTO c : gameStateDTO.getCittà()) {
			String città="";
			città=città+c.getNome()+" ["+c.getColoreDTO()+" Empori "+c.getEmpori()+" ]";
			System.out.println(città);
		}
		System.out.println("\nRegioni\n");
		for (RegioneDTO r : gameStateDTO.getRegioni()) {
			System.out.println(r);
			String balcone="Balcone [ ";
			for (ConsigliereDTO cons : r.getBalcone()) {
				balcone=balcone+cons+" ";
			}
			balcone=balcone+"]";
			System.out.println(balcone);
			System.out.println("Tessere Permesso Scoperte nella regione " + r.getNome());
			for (TesseraPermessoDTO t : r.getTesserePermessoScoperte()) {
				String cittàTessera = " Città [ ";
				String bonusTessera = ", Bonus [ ";
				for (CittàDTO ci : t.getCittà()) {
					cittàTessera = cittàTessera + ci.getNome() + " ";
				}
				cittàTessera=cittàTessera+"] ";
				for (Bonus bonus : t.getBonus()) {
					bonusTessera = bonusTessera + bonus + " ";
				}
				bonusTessera=bonusTessera+"]";
				System.out.println("Tessera ["+cittàTessera+bonusTessera+" ]");
				
			}
			System.out.println("BonusRegione ["+r.getBonusRegione()+" ]\n");

		}
		String balconeRe="Balcone Re [ ";
		for (ConsigliereDTO consRe : gameStateDTO.getPlanciaReDTO().getBalconeRe()) {
			balconeRe=balconeRe+consRe+" ";
		}
		balconeRe=balconeRe+"]";
		System.out.println(balconeRe+"\n");
		System.out.println(gameStateDTO.getPedinaRE()+"\n");
		
		String bonusRe="BonusRe\n";

		for (Bonus b : gameStateDTO.getPlanciaReDTO().getBonusPremioRe()){
			bonusRe=bonusRe+b+"\n";
		}
		System.out.println(bonusRe);

	}
}
