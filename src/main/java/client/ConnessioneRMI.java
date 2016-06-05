package client;

import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;
import view.ServerRMIRegistrationViewRemote;
import view.ServerRMIViewRemote;
import view.clientNotify.ClientNotify;

public class ConnessioneRMI extends UnicastRemoteObject implements Serializable,ClientRMI {

	private static final long serialVersionUID = 5904563829768967721L;
	private GameStateDTO gameStateDTO;
	private ServerRMIViewRemote view;
	private final static int PORT = 1099;
	private final static String IP = "127.0.0.1";

	public ConnessioneRMI(String giocatore) throws RemoteException {
		this.gameStateDTO = new GameStateDTO();
		GiocatoreDTO giocatoreDTO = new GiocatoreDTO();
		giocatoreDTO.setNome(giocatore);
		this.gameStateDTO.setGiocatoreDTO(giocatoreDTO);
	}

	@Override
	public void start() {
		String name = "GIOCO";
		Registry registry = null;
		while (registry == null) {
			try {
				registry = LocateRegistry.getRegistry(IP, PORT);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServerRMIRegistrationViewRemote game = null;
		while (game == null) {
			try {
				game = (ServerRMIRegistrationViewRemote) registry.lookup(name);
			} catch (AccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (registry != null) {
				try {
					view = game.register(this);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public void inviaAzione(AzioneDTO azioneDTO) throws RemoteException {
		try {
			this.view.eseguiAzione(azioneDTO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GiocatoreDTO getGiocatoreDTO() throws RemoteException{
		return this.gameStateDTO.getGiocatoreDTO();
	}

	public void aggiorna(ClientNotify notify) throws RemoteException {
		notify.update(gameStateDTO);
		notify.stamp();
	}

}
