package view;

import java.rmi.Remote;
import java.rmi.RemoteException;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.gameDTO.GiocatoreDTO;

public interface ServerRMIViewRemote extends Remote {
	
	public void eseguiAzione(AzioneDTO azioneDTO) throws RemoteException;
	public void creaGiocatore(GiocatoreDTO giocatoreDTO) throws RemoteException;

}
