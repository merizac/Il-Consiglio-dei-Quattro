package client;

import java.rmi.RemoteException;
import java.util.Scanner;

import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;

public class Client {

	private static final String SOCKET = "1";
	private static final String RMI = "2";
	private static final String CLI = "CLI";
	private static final String GUI = "GUI";
	private Scanner stdIn;

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
	 * 
	 * @throws RemoteException
	 */
	private void startClient() throws RemoteException {
		String nome = scegliNome();
		GiocatoreDTO giocatoreDTO = new GiocatoreDTO();
		giocatoreDTO.setNome(nome);
		GameStateDTO gameStateDTO = new GameStateDTO();
		gameStateDTO.setGiocatoreDTO(giocatoreDTO);
		Grafica grafica = scegliGrafica();
		Connessione connessione = scegliConnessione();
		grafica.setConnessione(connessione);
		grafica.setGameStateDTO(gameStateDTO);
		connessione.setGameStateDTO(gameStateDTO);
		connessione.setGrafica(grafica);
		connessione.start();
	}

	private Grafica scegliGrafica() {
		System.out.println("Inserisci grafica\nCLI[CLI]\nGUI[GUI]");
		String grafica = null;
		while (true) {
			grafica = stdIn.nextLine();
			if (CLI.equals(grafica)) {
				return new CLI();

			} else if (GUI.equals(grafica))
				return new GUI();

			else
				System.out.println("Inserisci un valore valido");
		}

	}

	/**
	 * this method let the client chose the name
	 * 
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
	 * 
	 * @param giocatore
	 * @return the connection selected
	 * @throws RemoteException
	 */
	public Connessione scegliConnessione() throws RemoteException {
		System.out.println("Inserisci connessione\nSocket[1]\nRMI[2]");
		String connessioneClient = null;
		while (true) {
			{
				connessioneClient = stdIn.nextLine();
				if (SOCKET.equals(connessioneClient)) {
					return new ConnessioneSocket();

				} else if (RMI.equals(connessioneClient))
					try {
						return new ConnessioneRMI();
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
