package game;

public class Aiutante {

	private int numeroAiutanti;
	/**
	 * 
	 * @param numeroAiutanti
	 */
	public Aiutante(int numeroAiutanti){
		this.numeroAiutanti= numeroAiutanti;
	}
	/**
	 * get the private variable numeroAiutanti
	 * @return numeroAiutanti
	 */
	public int getAiutante() {
		return this.numeroAiutanti;
	}
	/**
	 * Add the numeroAiutanti passed as a parameter to the local variable numeroAiutanti
	 * @param numeroAiutanti the number of Aiutanti to add
	 */
	public void aggiungiAiutanti(int numeroAiutanti) {
		this.numeroAiutanti = this.numeroAiutanti + numeroAiutanti;
	}
	/**
	 * Decrement the numeroAiutanti 
	 * @param numeroAiutanti
	 * @return true if it possible to decrease the variable numeroAiutanti and false in the other case
	 */
	public boolean togliAiutanti(int numeroAiutanti) {
		
		if(this.numeroAiutanti< numeroAiutanti) return false;
		else {
			this.numeroAiutanti = this.numeroAiutanti - numeroAiutanti;
			return true;
		}

	}
}
