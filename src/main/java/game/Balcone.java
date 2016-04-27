package game;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Balcone {

	private final Queue<Consigliere> consiglieri;
	/**
	 * constructor of Balcone
	 * @param numConsiglieri the size of Balcone
	 * @param consiglieri consiglieri to add at Balcone
	 */
	public Balcone(int numConsiglieri, ArrayList<Consigliere> consiglieri){
		this.consiglieri= new ArrayBlockingQueue<>(numConsiglieri, true,consiglieri);
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
}
