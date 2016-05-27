package client;

import java.util.ArrayList;
import java.util.Set;

import gameDTO.gameDTO.CartaPoliticaDTO;
import gameDTO.gameDTO.CittàDTO;
import gameDTO.gameDTO.ColoreDTO;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class ControlloParametri {

	public static ConsigliereDTO consiglieri(String consigliere, ArrayList<ConsigliereDTO> consiglieri) {
		for(ConsigliereDTO c: consiglieri){
			if(consigliere.equals(c.getColoreConsigliere()))
				return c;
		}
		return null;
	}

	public static RegioneDTO regioni(String regione, ArrayList<RegioneDTO> regioni) {
		for(RegioneDTO r: regioni){
			if(regione.equals(r.getNome()))
				return r;
		}
		return null;
	}
	
	public static CartaPoliticaDTO carteGiocatore(String carta, ArrayList<CartaPoliticaDTO> cartePolitica){
		for(CartaPoliticaDTO c: cartePolitica){
			if(carta.equals(c.getColore())){
				cartePolitica.remove(c);
				return c;
				}
			}
			return null;
		}

	public static TesseraPermessoDTO tessereGiocatore(String tessera, ArrayList<TesseraPermessoDTO> tesserePermesso) {
		if(isNumeric(tessera)){
			if(Integer.parseInt(tessera)>0 && Integer.parseInt(tessera)<=tesserePermesso.size())
				return tesserePermesso.get(Integer.parseInt(tessera)-1);
		}
		return null;
	}

	private static boolean isNumeric(String tessera) {
		try{
			Integer.parseInt(tessera);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}

	public static CittàDTO città(String cittàScelta, Set<CittàDTO> città, ColoreDTO colore) {
		for(CittàDTO c: città){
			if(cittàScelta.equals(c.getNome()) && !(c.getEmpori().contains(colore.getColore())))
				return c;
		}
		return null;
	}

}
