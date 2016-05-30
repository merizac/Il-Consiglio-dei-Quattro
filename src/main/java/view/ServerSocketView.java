package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import game.GameState;
import game.Giocatore;
import game.azioni.Azione;
import game.notify.ErrorNotify;
import game.notify.GameStateNotify;
import game.notify.GameStateStartNotify;
import game.notify.GiocatoreDTONotify;
import game.notify.Notify;
import game.notify.NotifyGiocatoreCorrente;
import game.notify.NotifyGiocatori;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import gameDTO.gameDTO.GiocatoreDTO;
import server.Server;

public class ServerSocketView extends View implements Runnable {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private GameState gameState;
	private Giocatore giocatore;
	private Server server;

	public ServerSocketView(Socket socket, GameState gameState, Server server) throws IOException {
		this.socket = socket;
		this.gameState = gameState;
		this.socketIn = new ObjectInputStream(socket.getInputStream());
		this.socketOut = new ObjectOutputStream(socket.getOutputStream());
		this.server=server;
	}

	@Override
	public void update(Notify o) {
		
		if(o instanceof GameStateStartNotify){
				((GameStateStartNotify) o).setGiocatoreDTO(giocatore);
		}
		
		if(o instanceof NotifyGiocatori){
			try {
				this.socketOut.writeObject(o);
				this.socketOut.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if ((o instanceof NotifyGiocatoreCorrente) && giocatore.equals(gameState.getGiocatoreCorrente()))
			try {
				System.out.println("Sending to the client " + o);
				this.socketOut.writeObject(o);
				this.socketOut.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void run() {
		
		try {
			GiocatoreDTO giocatoreDTO =(GiocatoreDTO) socketIn.readObject();
			this.giocatore= new Giocatore(giocatoreDTO.getNome());
			
		} catch (ClassNotFoundException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		server.aggiungiGiocatore(giocatore);

		while (true) {

			try {
				Object object = socketIn.readObject();
				if (object instanceof AzioneDTO) {
					AzioneVisitor azioneVisitor = new AzioneVisitorImpl(gameState, giocatore);
					AzioneDTO action = (AzioneDTO) object;
					try {
						Azione azione = action.accept(azioneVisitor);
						System.out.println("VIEW: received the action " + azione);
						this.notifyObserver(azione);
					} catch (IllegalArgumentException e) {
						this.socketOut.writeObject(new ErrorNotify(e.getMessage()));
					}
				}

			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}

}
