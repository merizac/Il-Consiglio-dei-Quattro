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

	public ControlloParametriDTO() {
	}

	/**
	 * check if the counselor selected is in the reserve of counselor and return
	 * it
	 * 
	 * @param consigliere
	 * @param consiglieri
	 * @return the counselor selected if it is present in the reserve, or null
	 *         in the other case
	 */
	public static ConsigliereDTO consiglieri(String consigliere, List<ConsigliereDTO> consiglieri) {
		for (ConsigliereDTO c : consiglieri) {
			if (consigliere.equals(c.getColoreConsigliere()))
				return c;
		}
		return null;
	}

	/**
	 * check if the region selected exist and return the regionDTO associate
	 * 
	 * @param regione
	 * @param regioni
	 * @return regioneDTO, or null in the other case
	 */
	public static RegioneDTO regioni(String regione, List<RegioneDTO> regioni) {
		for (RegioneDTO r : regioni) {
			if (regione.equals(r.getNome()))
				return r;
		}
		return null;
	}

	/**
	 * check if the card passed is present in the player hand and return the
	 * cartaPoliticaDTO associate
	 * 
	 * @param carta
	 * @param cartePolitica
	 * @return cartaPoliticaDTO, or null in the other case
	 */
	public static CartaPoliticaDTO carteGiocatore(String carta, List<CartaPoliticaDTO> cartePolitica) {
		for (CartaPoliticaDTO c : cartePolitica) {
			if (carta.equals(c.getColore())) {
				cartePolitica.remove(c);
				return c;
			}
		}
		return null;
	}

	/**
	 * check if tesseraPermesso passed is present in the list of tesserePermesso
	 * and return it
	 * 
	 * @param tessera
	 * @param tesserePermesso
	 * @return tesseraPermessoDTO if it exist, or null in the other case
	 */
	public static TesseraPermessoDTO tessereGiocatore(String tessera, List<TesseraPermessoDTO> tesserePermesso) {
		if (Utils.isNumeric(tessera)) {
			if (Integer.parseInt(tessera) > 0 && Integer.parseInt(tessera) <= tesserePermesso.size())
				return tesserePermesso.get(Integer.parseInt(tessera) - 1);
		}
		return null;
	}

	/**
	 * check if the city selected is in the set of city, and if it doesn't
	 * contains an emporium of the colore passed as argument
	 * 
	 * @param cittàScelta
	 * @param città
	 * @param colore
	 * @return the cittàDTO, or null if it doesn't exist or if it contains an
	 *         emporium of the colore passed as argument
	 */
	public static CittàDTO città(String cittàScelta, Set<? extends CittàDTO> città, ColoreDTO colore) {
		for (CittàDTO c : città) {
			if (cittàScelta.equals(c.getNome()) && (!c.getEmpori().contains(colore.getColore()))) {
				return c;
			} else
				continue;
		}
		return null;
	}

	/**
	 * check if the city selected is in the set of city, and if it contains an
	 * emporium of the colore passed as argument
	 * 
	 * @param cittàScelta
	 * @param città
	 * @param colore
	 * @return the cittàDTO, or null if it doesn't exist or if it doesn't
	 *         contains an emporium of the colore passed as argument
	 */
	public static CittàDTO cittàBonus(String cittàScelta, Set<? extends CittàDTO> città, ColoreDTO colore) {
		for (CittàDTO c : città) {
			if (cittàScelta.equals(c.getNome()) && (c.getEmpori().contains(colore.getColore()))) {
				return c;
			} else
				continue;
		}
		return null;
	}

}
