package gameDTO.azioniDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import game.CartaPolitica;
import game.Città;
import game.Consigliere;
import game.Regione;
import game.TesseraPermesso;
import gameDTO.gameDTO.CartaPoliticaDTO;
import gameDTO.gameDTO.CittàDTO;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class ControlloParametri {

	public static Regione cercaRegione (RegioneDTO regione, ArrayList<Regione> regioni) throws IllegalArgumentException {
		for(Regione r: regioni){
			if(r.getNome().equals(regione.getNome()))
				return r;
		}
		throw new IllegalArgumentException("La regione è inesistente!");
	}

	public static ArrayList<CartaPolitica> cercaCartePolitica(List<CartaPoliticaDTO> carte,
			ArrayList<CartaPolitica> cartePolitica) throws IllegalArgumentException{
		ArrayList<CartaPolitica> carteGiocatore=new ArrayList<>();
		for(CartaPoliticaDTO c: carte){
			if(cartePolitica.contains(c)){
				for(CartaPolitica cp: cartePolitica){
					if(cp.getColore().getColore().equals(c.getColore())){
						cartePolitica.remove(cp);
						carteGiocatore.add(cp);
						break;
					}
				}	
			}
			else
				throw new IllegalArgumentException("Una delle carte passate è inesistente!");
		}
		
		return carteGiocatore;
	}
	public static Città cercaCittà(CittàDTO città, Set<Città> cittàGameState) throws IllegalArgumentException{
		for(Città c: cittàGameState){
			if(c.getNome().equals(città.getNome()))
				return c;
		}
		throw new IllegalArgumentException("La città è inesistente!");
	}
	//riguarda
	public static TesseraPermesso cercaTesseraPermesso(TesseraPermessoDTO tesseraPermesso,
			ArrayList<TesseraPermesso> tesserePermesso) throws IllegalArgumentException{
		for(TesseraPermesso t: tesserePermesso){
			if(!t.getBonus().containsAll(tesseraPermesso.getBonus())
					|| !tesseraPermesso.getBonus().containsAll(t.getBonus()))
				continue;
			else{
				for(CittàDTO cDTO: tesseraPermesso.getCittà()){
					for(Città c: t.getCittà()){
						if(!cDTO.getNome().equals(c.getNome()))
							continue;
						else
							break;
					}
				}
			}
		}
		
		throw new IllegalArgumentException("La tessera permesso è inesistente!");
	}

	public static Consigliere cercaConsigliere(ConsigliereDTO consigliereDTO, ArrayList<Consigliere> consiglieri) throws IllegalArgumentException {
		for(Consigliere c: consiglieri){
			if(c.getColore().getColore().equals(consigliereDTO.getColoreConsigliere()))
					return c;
		}
		throw new IllegalArgumentException("Il consigliere è inesistente!");
	}

}
