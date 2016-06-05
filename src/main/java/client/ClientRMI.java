package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import gameDTO.gameDTO.GiocatoreDTO;
import view.clientNotify.ClientNotify;

public interface ClientRMI extends Remote, Connessione {
	
	public GiocatoreDTO getGiocatoreDTO() throws RemoteException;
	public void aggiorna(ClientNotify notify) throws RemoteException;

}
