package utility;

public interface Observer {

	public void update();
	public <C> void update(C change);
}
