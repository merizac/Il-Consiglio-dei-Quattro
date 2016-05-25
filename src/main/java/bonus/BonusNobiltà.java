package bonus;
import controller.Controller;

public abstract class BonusNobiltà extends Bonus {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4543850194700774391L;

	public BonusNobiltà() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public abstract void usaBonus(Controller partita);


}
