package bonus;
import controller.Partita;

public abstract class BonusNobiltà extends Bonus {

	public BonusNobiltà() {
		super();
	}
	
	public abstract void usaBonus(Partita partita);

}
