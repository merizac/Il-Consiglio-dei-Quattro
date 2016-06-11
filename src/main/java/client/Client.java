package client;

import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {

	private static final String SOCKET = "1";
	private static final String RMI = "2";
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
	/**
	 * this method start the client
	 * @throws RemoteException
	 */
	private void startClient() throws RemoteException {
		String giocatore = scegliNome();
		connessione = scegliConnessione(giocatore);
		connessione.start();
	}
	
	/**
	 * this method let the client chose the name
	 * @return the name of the player
	 */
	public String scegliNome() {
		String nome = null;
		while (nome == null || "".equals(nome)) {
			System.out.println("Inserisci il nome");
			nome = stdIn.nextLine();
		}

		return nome;
	}

	/**
	 * this method let the player chose the connection between socket and rmi
	 * @param giocatore
	 * @return the connection selected
	 * @throws RemoteException
	 */
	public Connessione scegliConnessione(String giocatore) throws RemoteException {
		System.out.println("Inserisci connessione\nSocket[1]\nRMI[2]");
		String connessioneClient=null;
		while (true) {
			{
				connessioneClient = stdIn.nextLine();
				if (SOCKET.equals(connessioneClient)) {
					return new ConnessioneSocket(giocatore);

				} else if (RMI.equals(connessioneClient))
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
