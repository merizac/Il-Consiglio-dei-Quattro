package game;

import java.util.HashSet;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.ConnectivityInspector;

public class CittàCollegate {
	
	private final HashSet<CittàBonus> cittàCollegate;
	private final HashSet<Città> cittàVisitate;
	/**
	 * constructor 
	 */
	public CittàCollegate() {
		this.cittàCollegate= new HashSet<CittàBonus>();
		this.cittàVisitate = new HashSet<Città>();
	}
	/**
	 * 
	 * @param grafo
	 * @param emporio
	 * @param città
	 * @return HashSet of cities connected to the city where the player has built
	 */
	public HashSet<CittàBonus> cittàBonusEmporio(SimpleGraph<Città, DefaultEdge> grafo, Colore coloreEmporio, Città città){
		cittàVisitate.add(città);
		if(città instanceof CittàBonus)
			cittàCollegate.add((CittàBonus)città);
		aggiungiCittà(grafo, coloreEmporio, città);
		return cittàCollegate;
	}
	/**
	 * find city connected to città with the same color of Emporio and add to cittàCollegate
	 * @param grafo
	 * @param emporio
	 * @param città
	 */
	private void aggiungiCittà(SimpleGraph<Città, DefaultEdge> grafo, Colore coloreEmporio, Città città) {
		for(Città c: città.getCittàCollegate()){
			if (!cittàVisitate.contains(c) && c.emporioColore(coloreEmporio)){
					cittàVisitate.add(c);
					if( c instanceof CittàBonus){
						cittàCollegate.add((CittàBonus)c);
					}
					aggiungiCittà(grafo, coloreEmporio, c);
			}
			
		}
					
		}
	}

