package game;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.ConnectivityInspector;

public class CittàCollegate {
	
	private final Set<Città> cittàCollegate;
	private final Set<Città> cittàVisitate;
	/**
	 * constructor 
	 */
	public CittàCollegate() {
		this.cittàCollegate= new HashSet<Città>();
		this.cittàVisitate = new HashSet<Città>();
	}
	/**
	 * 
	 * @param grafo
	 * @param emporio
	 * @param città
	 * @return HashSet of cities connected to the city where the player has built
	 */
	public HashSet<Città> cittàBonusEmporio(SimpleGraph<Città, Strada> grafo, Emporio emporio, Città città){
		aggiungiCittà(grafo, emporio, città);
		return (HashSet<Città>) cittàCollegate;
	}
	/**
	 * find city connected to città with the same color of Emporio and add to cittàCollegate
	 * @param grafo
	 * @param emporio
	 * @param città
	 */
	private void aggiungiCittà(SimpleGraph<Città, Strada> grafo, Emporio emporio, Città città) {
		ConnectivityInspector<Città, Strada> inspector= new ConnectivityInspector<>(grafo);
		for(Città c: inspector.connectedSetOf(città)){
			if(!c.emporioColore(emporio.getColore()) || cittàVisitate.contains(c))
				return;
			else {
					cittàVisitate.add(c);
					cittàCollegate.add(c);
					aggiungiCittà(grafo, emporio, c);
			}
					
		}
	}

}
