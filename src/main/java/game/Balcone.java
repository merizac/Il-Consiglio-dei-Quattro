package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Balcone {

	private final Queue<Consigliere> consiglieri;
	/**
	 * constructor of Balcone
	 * @param numConsiglieri the size of Balcone
	 * @param consiglieri consiglieri to add at Balcone
	 */
	public Balcone(int numConsiglieri, List<Consigliere> consiglieri){
		List<Consigliere> consiglieriDaAggiungere=pescaConsiglieri(consiglieri, numConsiglieri);
		this.consiglieri=new ArrayBlockingQueue<>(numConsiglieri, true, consiglieriDaAggiungere);	
	}
	
	public Balcone(List<Consigliere> consiglieri){
		this.consiglieri=new ArrayBlockingQueue<>(4, true, consiglieri);	
	}
	
	private List<Consigliere> pescaConsiglieri(List<Consigliere> consiglieri, int numConsiglieri) {
		
		List<Consigliere> consiglieriNuovi=new ArrayList<>();
		Collections.shuffle(consiglieri);
		for(int i=0; i<numConsiglieri; i++){
			consiglieriNuovi.add(consiglieri.remove(0));
		}
		return consiglieriNuovi;
		
		
	}

	/**
	 * add consigliere passed as parameter at tail of the Queue consiglieri and return the head of the Queue consiglieri
	 * @param consigliere the consigliere that has to be added at the Queue consiglieri
	 * @return the head of the Queue consiglieri
	 */
	public Consigliere aggiungiConsigliere(Consigliere consigliere) {
		 Consigliere consigliereTolto = this.togliConsigliere();
		 this.consiglieri.add(consigliere);
		 return consigliereTolto;
	}
	/**
	 * remove the head of the queue
	 * @return the head of the queue
	 */
	private Consigliere togliConsigliere() {
		return consiglieri.remove();
	}
	/**
	 * get the Queue consiglieri
	 * @return consiglieri
	 */
	public Queue<Consigliere> getConsigliere() {
		return consiglieri;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return consiglieri.toString();
	}
	
	
}
