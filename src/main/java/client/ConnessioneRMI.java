package client;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.ExitDTO;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;
import view.ServerRMIViewRemote;
import view.clientNotify.ClientNotify;

public class ConnessioneRMI extends UnicastRemoteObject implements Serializable, ConnessioneRMIRemota {

	private static final long serialVersionUID = 5904563829768967721L;
	private GameStateDTO gameStateDTO;
	private ServerRMIViewRemote view;
	private ClientOutHandler clientOutHandler;
	private final static int PORT = 1099;
	private final static String IP = "127.0.0.1";

	public ConnessioneRMI(String giocatore) throws RemoteException {
		this.gameStateDTO = new GameStateDTO();
		GiocatoreDTO giocatoreDTO = new GiocatoreDTO();
		giocatoreDTO.setNome(giocatore);
		this.gameStateDTO.setGiocatoreDTO(giocatoreDTO);
	}

	@Override
	public void start() throws RemoteException {
		String name = "GIOCO";
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry(IP, PORT);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			view = (ServerRMIViewRemote) registry.lookup(name);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.register(this, gameStateDTO.getGiocatoreDTO());
		ExecutorService executor = Executors.newFixedThreadPool(1);
		this.clientOutHandler = new ClientOutHandler(this, gameStateDTO);
		executor.submit(this.clientOutHandler);

	}

	@Override
	public void inviaAzione(AzioneDTO azioneDTO) throws RemoteException {
		try {
			if (azioneDTO instanceof ExitDTO) {
				this.view.unregister(this);
			} else
				this.view.eseguiAzione(azioneDTO, this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void aggiorna(ClientNotify notify) throws RemoteException {
		notify.update(gameStateDTO);
		notify.stamp();
	}

	@Override
	public void disconnetti() throws RemoteException {
		this.clientOutHandler.stop();
	}

}
