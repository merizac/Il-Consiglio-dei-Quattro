package utility;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<C> {

	private List<Observer<C>> observers;

	public Observable() {
		this.observers = new ArrayList<>();
	}

	/**
	 * add observer
	 * 
	 * @param o
	 */
	public void registerObserver(Observer<C> o) {
		this.observers.add(o);
	}

	/**
	 * remove observer
	 * 
	 * @param o
	 */
	public void unregisterObserver(Observer<C> o) {
		this.observers.remove(o);
	}

	/**
	 * notify who is register like observer
	 * 
	 * @param c
	 */
	public void notifyObserver(C c) {
		for (Observer<C> o : this.observers) {
			o.update(c);
		}
	}
}
