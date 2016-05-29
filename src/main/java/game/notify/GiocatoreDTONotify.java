package game.notify;

import java.io.Serializable;
import game.Giocatore;
import gameDTO.gameDTO.GiocatoreDTO;

public class GiocatoreDTONotify implements Serializable, Notify{

	private static final long serialVersionUID = 4372428925907670712L;
	private GiocatoreDTO giocatoreDTO;

	public GiocatoreDTONotify(Giocatore giocatore) {
		this.giocatoreDTO=new GiocatoreDTO();
		giocatoreDTO.inizializza(giocatore);
	}

	@Override
	public void stamp() {
		System.out.println(giocatoreDTO);
	}


	public void update(GiocatoreDTO giocatoreDTO) {
		giocatoreDTO.setAiutanti(this.giocatoreDTO.getAiutanti());
		giocatoreDTO.setCartePolitica(this.giocatoreDTO.getCartePolitica());
		giocatoreDTO.setColoreGiocatore(this.giocatoreDTO.getColoreGiocatore());
		giocatoreDTO.setEmpori(this.giocatoreDTO.getEmpori());
		giocatoreDTO.setPunteggioNobiltà(this.giocatoreDTO.getPunteggioNobiltà());
		giocatoreDTO.setPunteggioRicchezza(this.giocatoreDTO.getPunteggioRicchezza());
		giocatoreDTO.setPunteggioVittoria(this.giocatoreDTO.getPunteggioVittoria());
	}



}
