package game;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Balcone {

	private Queue<Consigliere> consiglieri;
	
	public Balcone(ArrayList<Consigliere> consiglieri){
		this.consiglieri= new ArrayBlockingQueue<>(4, true, )
	}
	public void aggiungiConsigliere(Consigliere consigliere) {
		throw new UnsupportedOperationException();
	}

	private Consigliere togliConsigliere() {
		throw new UnsupportedOperationException();
	}

	public Consigliere[] getConsigliere() {
		throw new UnsupportedOperationException();
	}
}
