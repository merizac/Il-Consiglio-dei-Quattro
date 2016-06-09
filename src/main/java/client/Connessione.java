package client;

import java.rmi.RemoteException;

import gameDTO.azioniDTO.AzioneDTO;

public interface Connessione {

	public void start() throws RemoteException;

	public void inviaAzione(AzioneDTO action) throws RemoteException;
	
	public void disconnetti() throws RemoteException;

}
