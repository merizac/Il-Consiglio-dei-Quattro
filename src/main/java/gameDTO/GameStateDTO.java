package gameDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import game.Città;
import game.Consigliere;
import game.Regione;

public class GameStateDTO implements Serializable {

	private static final long serialVersionUID = 7597822798907073182L;
	private ArrayList<Consigliere> consiglieri;
	private ArrayList<Regione> regioni;
	private Set<Città> città;
	
	
	public ArrayList<Consigliere> getConsiglieri() {
		return consiglieri;
	}
	public void setConsiglieri(ArrayList<Consigliere> consiglieri) {
		this.consiglieri = consiglieri;
	}
	public ArrayList<Regione> getRegioni() {
		return regioni;
	}
	public void setRegioni(ArrayList<Regione> regioni) {
		this.regioni = regioni;
	}
	public Set<Città> getCittà() {
		return città;
	}
	public void setCittà(Set<Città> città) {
		this.città = città;
	}
	@Override
	public String toString() {
		return "GameStateDTO\nconsiglieri=" + consiglieri + "\nregioni="+regioni+  "\ncittà="  ;
	}
	
	

}
