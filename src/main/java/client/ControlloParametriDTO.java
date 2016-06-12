package client;

import java.util.List;
import java.util.Set;

import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.ColoreDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import utility.Utils;

public class ControlloParametriDTO {

	public static ConsigliereDTO consiglieri(String consigliere, List<ConsigliereDTO> consiglieri) {
		for (ConsigliereDTO c : consiglieri) {
			if (consigliere.equals(c.getColoreConsigliere()))
				return c;
		}
		return null;
	}

	public static RegioneDTO regioni(String regione, List<RegioneDTO> regioni) {
		for (RegioneDTO r : regioni) {
			if (regione.equals(r.getNome()))
				return r;
		}
		return null;
	}

	public static CartaPoliticaDTO carteGiocatore(String carta, List<CartaPoliticaDTO> cartePolitica) {
		for (CartaPoliticaDTO c : cartePolitica) {
			if (carta.equals(c.getColore())) {
				cartePolitica.remove(c);
				return c;
			}
		}
		return null;
	}

	public static TesseraPermessoDTO tessereGiocatore(String tessera, List<TesseraPermessoDTO> tesserePermesso) {
		if (Utils.isNumeric(tessera)) {
			if (Integer.parseInt(tessera) > 0 && Integer.parseInt(tessera) <= tesserePermesso.size())
				return tesserePermesso.get(Integer.parseInt(tessera) - 1);
		}
		return null;
	}

	public static CittàDTO città(String cittàScelta, Set<? extends CittàDTO> città, ColoreDTO colore) {
		for (CittàDTO c : città) {
			if (cittàScelta.equals(c.getNome())) {
				//&& (c.getEmpori().contains(colore.getColore()))
				return c;
			} else
				continue;
		}
		return null;
	}

}
