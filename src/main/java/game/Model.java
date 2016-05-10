package game;

import java.util.List;
import java.util.Set;

public interface Model {
	
	public List<Regione> getRegioni();
	public List<CartaPolitica> getCartePoliticaGiocatore();
	public List<TesseraPermesso> getTesserePermessoGiocatore();
	public Set<Città> getCittà();
	public List<Consigliere> getConsiglieri();
}
