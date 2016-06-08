package client;

import java.rmi.RemoteException;
import java.util.Scanner;

import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;

public class Client {

	private final static String SOCKET = "1";
	private final static String RMI = "2";
	private Scanner stdIn;
	private Connessione connessione;

	public Client() {
		this.stdIn = new Scanner(System.in);
	}

	public static void main(String[] args) {
		Client client = new Client();

		try {
			client.startClient();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void startClient() throws RemoteException {
		String giocatore = scegliNome();
		connessione = scegliConnessione(giocatore);
		connessione.start();
	}

	public String scegliNome() {
		String nome = null;
		while (nome == null | "".equals(nome)) {
			System.out.println("Inserisci il nome");
			nome = stdIn.nextLine();
		}

		return nome;
	}

	public Connessione scegliConnessione(String giocatore) throws RemoteException {
		System.out.println("Inserisci connessione\nSocket[1]\nRMI[2]");
		String connessione = null;
		while (true) {
			{
				connessione = stdIn.nextLine();
				if (SOCKET.equals(connessione)) {
					return new ConnessioneSocket(giocatore);

				} else if (RMI.equals(connessione))
					try {
						return new ConnessioneRMI(giocatore);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else
					System.out.println("Inserisci un valore valido");
			}
		}
	}

}
