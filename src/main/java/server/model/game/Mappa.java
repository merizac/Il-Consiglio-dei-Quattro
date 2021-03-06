package server.model.game;

import java.util.Set;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Mappa  {

	private final SimpleGraph<Città, DefaultEdge> grafo;
	/**
	 * create the graph
	 * @param città
	 */
	public Mappa(Set<Città> città){
		
		grafo= new SimpleGraph<>(DefaultEdge.class);
		this.aggiungiVertici(città);
		this.collegaVertici(grafo.vertexSet());
	}
	
	/**
	 * add cities at the graph
	 * @param città
	 */
	private void aggiungiVertici(Set<Città> città) {
		
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
	public Set<CittàBonus> trovaCittà(Città cittàEmporio, Colore coloreEmporio){
		
		CittàCollegate cittàCollegate = new CittàCollegate();
		
		return cittàCollegate.cittàBonusEmporio(grafo, coloreEmporio, cittàEmporio);
		
	}
	
	
	/**
	 * calculate the minimum distance between cittàPartenza e cittàDestinazione 
	 * @param cittàPartenza
	 * @param cittàDestinazione
	 * @return minimaDistanza
	 */
	public int minimaDistanza(Città cittàPartenza, Città cittàDestinazione){
		DijkstraShortestPath<Città, DefaultEdge> distanza 
			= new DijkstraShortestPath<>(grafo, cittàPartenza, cittàDestinazione);
		return (int)distanza.getPathLength();
	}
	
	
	/**
	 * 
	 * @return grafo
	 */
	public SimpleGraph<Città, DefaultEdge> getGrafo() {
		return grafo;
	}
	
	public Città getCittà(String città){
		Città cittàScelta = null;
		for(Città c: grafo.vertexSet()){
			if(c.getNome().equals(città)){
				cittàScelta = c;
				break;
			}
		}
		return cittàScelta;
			
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Map [grafo=" + grafo + "]";
	}
	

}
