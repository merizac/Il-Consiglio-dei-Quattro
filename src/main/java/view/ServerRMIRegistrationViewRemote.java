package view;

import java.rmi.Remote;
import java.rmi.RemoteException;
import client.ConnessioneRMI;

public interface ServerRMIRegistrationViewRemote extends Remote {
	public ServerRMIViewRemote register(ConnessioneRMI connessioneRMI) throws RemoteException;
}
