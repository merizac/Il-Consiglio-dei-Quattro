package game;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

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
	 * create an HashSet that contains the cities connected with cittàEmporio that have an emporium
	 * of the same color of the player
	 * @param cittàEmporio
	 * @param coloreGiocatore
	 * @return cittàBonus
	 */
	public HashSet<Città> cittàCollegate(Città cittàEmporio, Colore coloreGiocatore){
		
		Mappa sottografo = new Mappa(cittàEmporio(coloreGiocatore));
		
		for(Strada s: stradeConnesse(coloreGiocatore)){
			sottografo.getGrafo().addEdge(s.getCittaPartenza(), s.getCittàDestinazione());
		}
		
		return cittàBonus(cittàEmporio, sottografo);
		
	}
	/**
	 * create an Hashet of cities with an emporium of the same color of player
	 * @param coloreGiocatore
	 * @return cittàEmporio
	 */
	private HashSet<Città> cittàEmporio(Colore coloreGiocatore){
		HashSet<Città> cittàEmporio = new HashSet<Città>();
		for(Città città: grafo.vertexSet()){
			if(città.getColoreCittà().equals(coloreGiocatore))
				cittàEmporio.add(città);
		}
		return cittàEmporio;
		
	}
	/**
	 * create an HashSet of road where each road connect two cities have both
	 * the same color of coloreGiocatore
	 * @param coloreGiocatore
	 * @return connessioniEmporio
	 */
	private HashSet<Strada> stradeConnesse(Colore coloreGiocatore){
		HashSet<Strada> connessioniEmporio =
				new HashSet<>(grafo.edgeSet());
		for(Strada d: connessioniEmporio){
			if(!coloreVertici(d.getCittaPartenza(), d.getCittàDestinazione(), coloreGiocatore))
					connessioniEmporio.remove(d);
		}
		return connessioniEmporio;
	}
	/**
	 * check if both citta1 and città2 have the same color of coloreGiocatore
	 * @param città1
	 * @param città2
	 * @param coloreGiocatore
	 * @return true if colors are equals and false in the other case
	 */
	private boolean coloreVertici(Città città1, Città città2, Colore coloreGiocatore ){
		if (città1.emporioColore(coloreGiocatore) &&
				città2.emporioColore(coloreGiocatore))
				
			return true;
		else
			return false;
	}
	/**
	 * 
	 * @param cittàEmporio
	 * @param sottoGrafo
	 * @return
	 */
	private HashSet<Città> cittàBonus(Città cittàEmporio, Mappa sottoGrafo){
		
		HashSet<Città> cittàBonus = new HashSet<Città>();
		
		BreadthFirstIterator<Città, Strada> bfs =
				new BreadthFirstIterator<>(sottoGrafo.getGrafo(), cittàEmporio);
		while(bfs.hasNext()){
			cittàBonus.add(bfs.next());
		}
		return cittàBonus;
	}
	
	/**
	 * @return the grafo
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
