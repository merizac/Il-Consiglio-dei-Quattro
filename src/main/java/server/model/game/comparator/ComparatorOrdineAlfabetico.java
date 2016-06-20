package server.model.game.comparator;

import java.io.Serializable;
import java.util.Comparator;

import common.gameDTO.CittàDTO;

public class ComparatorOrdineAlfabetico implements Comparator<CittàDTO>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1640007559415803090L;

	@Override
	public int compare(CittàDTO c1, CittàDTO c2) {
		String nome1= c1.getNome();
		String nome2 = c2.getNome();
		int res = String.CASE_INSENSITIVE_ORDER.compare(nome1, nome2);
        if (res == 0) {
            res = nome1.compareTo(nome2);
        }
        return res;
	}

}
