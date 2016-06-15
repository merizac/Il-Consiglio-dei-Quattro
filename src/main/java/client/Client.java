package client;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.grafica.Grafica;
import client.grafica.cli.CLI;
import client.grafica.gui.GUI;

public class Client {

	private static final String CLI = "CLI";
	private static final String GUI = "GUI";

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

		Scanner stdIn = new Scanner(System.in);
		Grafica grafica = scegliGrafica(stdIn);

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(grafica);
	}

	private Grafica scegliGrafica(Scanner stdIn) {
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

}
