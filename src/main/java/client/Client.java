package client;

import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {

	private final static String SOCKET = "1";
	private final static String RMI = "2";
	private String giocatore;
	private Scanner stdIn;
	private Connessione connessione;

	public Client() {
		this.stdIn = new Scanner(System.in);
	}

	public static void main(String[] args) {
		Client client = new Client();

		client.startClient();

	}

	private void startClient() {
		giocatore = scegliNome();
		connessione = scegliConnessione();
		try {
			connessione.start();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String scegliNome() {
		String nome = null;
		while (nome == null | "".equals(nome)) {
			System.out.println("Inserisci il nome");
			nome = stdIn.nextLine();
		}

		return nome;
	}

	public Connessione scegliConnessione() {
		System.out.println("Inserisci connessione\nSocket[1]\nRMI[2]");
		String connessione = null;
		while (true) {
			{
				connessione=stdIn.nextLine();
				if (SOCKET.equals(connessione))
					return new ConnessioneSocket(this.giocatore);
				else if (RMI.equals(connessione))
					try {
						return new ConnessioneRMI(this.giocatore);
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
