package game;

import java.util.HashSet;
import java.util.Set;


import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.SimpleGraph;

public class Mappa {
	private final SimpleGraph<Città, Strada> grafo;
	/**
	 * create the graph
	 * @param città
	 */
	public Mappa(HashSet<Città> città){
		
		grafo= new SimpleGraph<Città, Strada>(Strada.class);
		
		this.aggiungiVertici(città);
		this.collegaVertici((grafo.vertexSet()));
	
	}
	
	/**
	 * add cities at the graph
	 * @param città
	 */
	private void aggiungiVertici(HashSet<Città> città) {
		
		for(Città indiceCittà: città){
			grafo.addVertex(indiceCittà);
		}
	}

	/**
	 * connect the cities
	 * @param città
	 */
	
	
	private void collegaVertici(Set<Città> città) {
		
		for(Città cittàPartenza: città){
			for(Città cittàDestinazione: cittàPartenza.getCittàCollegate())
			{
				if(!grafo.containsEdge(cittàDestinazione, cittàPartenza))
					grafo.addEdge(cittàPartenza, cittàDestinazione);
				
			}
		}
	}
	/**
	 * 
	 * @param cittàEmporio
	 * @param emporio
	 * @return a set of city connected to cittàEmporio with the same color of Emporio 
	 */
	public HashSet<Città> trovaCittà(Città cittàEmporio, Emporio emporio){
		
		CittàCollegate cittàCollegate = new CittàCollegate();
		
		return cittàCollegate.cittàBonusEmporio(grafo, emporio, cittàEmporio);
		
	}
	
	
	/**
	 * calculate the minimum distance between cittàPartenza e cittàDestinazione 
	 * @param cittàPartenza
	 * @param cittàDestinazione
	 * @return minimaDistanza
	 */
	public int minimaDistanza(Città cittàPartenza, Città cittàDestinazione){
		DijkstraShortestPath<Città, Strada> distanza 
			= new DijkstraShortestPath<Città, Strada>(grafo, cittàPartenza, cittàDestinazione);
		return (int)distanza.getPathLength();
	}
	
	
	/**
	 * 
	 * @return grafo
	 */
	public SimpleGraph<Città, Strada> getGrafo() {
		return grafo;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Map [grafo=" + grafo + "]";
	}
	

}
