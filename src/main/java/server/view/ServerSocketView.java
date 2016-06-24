package server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.ExitDTO;
import common.azioniDTO.azioneVisitor.AzioneVisitor;
import common.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import common.gameDTO.GiocatoreDTO;
import server.Server;
import server.model.azioni.Azione;
import server.model.azioni.Chat;
import server.model.azioni.Exit;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.notify.MessageNotify;
import server.model.notify.Notify;
import server.view.clientNotify.MessageClientNotify;
import utility.ParameterException;

public class ServerSocketView extends View implements Runnable {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private GameState gameState;
	private Giocatore giocatore;

	/**
	 * constructor of the ServerSocketView with the socket passed as argument,
	 * and create the ObjectInputStream and the ObjectOutputStream
	 * 
	 * @param socket
	 * @throws IOException
	 */
	public ServerSocketView(Socket socket) throws IOException {
		this.socket = socket;
		this.socketIn = new ObjectInputStream(socket.getInputStream());
		this.socketOut = new ObjectOutputStream(socket.getOutputStream());
	}

	/**
	 * update the relative client
	 */
	@Override
	public void update(Notify o) {
		if (o.daInviare(giocatore)) {
			System.out.println("[SERVER] Inviata notifica " + o + " al giocatore " + giocatore.getNome());
			try {
				this.socketOut.writeObject(o.notifyToClientNotify());
				this.socketOut.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * set the playerDTO and the listen for action from the client
	 */
	@Override
	public void run() {

		try {
			GiocatoreDTO giocatoreDTO = (GiocatoreDTO) socketIn.readObject();
			this.giocatore = new Giocatore(giocatoreDTO.getNome());

		} catch (ClassNotFoundException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			Server.getInstance().aggiungiGiocatore(giocatore, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (true) {

			try {
				Object object = socketIn.readObject();
				if (object instanceof AzioneDTO) {

					AzioneDTO action = (AzioneDTO) object;
					if(action instanceof ExitDTO){
						disconnetti();
						Exit exit = new Exit();
						exit.setGiocatore(giocatore);
						this.notifyObserver(exit);
						return;
					}
					AzioneVisitor azioneVisitor = new AzioneVisitorImpl(gameState, giocatore);
					Azione azione = null;
					try {
						azione = action.accept(azioneVisitor);
					} catch (ParameterException e) {
						update(new MessageNotify(e.getMessage(), Arrays.asList(gameState.getGiocatoreCorrente())));
						System.out.println("[SERVER] Ricevuta l'azione " + azione + " dal giocatore "
								+ this.giocatore.getNome() + " con errore: " + e.getMessage());
						continue;
					}
					System.out
							.println("[SERVER] Ricevuta l'azione " + azione + " dal giocatore " + giocatore.getNome());

					if ((azione.isTurno(giocatore, gameState) 
							&& gameState.getStato().daEseguire(gameState.getStato().getAzioni(), azione))
							|| (azione instanceof Chat)){
						this.notifyObserver(azione);
						System.out.println("[SERVER] Inviata l'azione " + azione);
					} else {
						this.socketOut.writeObject(new MessageClientNotify("Non è il tuo turno"));
						this.socketOut.flush();
					}

				}
			} catch (ClassNotFoundException | IOException e1) {
				disconnetti();
				Exit exit = new Exit();
				exit.setGiocatore(giocatore);
				this.notifyObserver(exit);
				return;
			}

		}
	}

	/**
	 * set the gameState
	 * 
	 * @param gameState
	 */
	@Override
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	/**
	 * unregister himself as an observer of the gameState and then close the
	 * socket
	 */
	@Override
	public void disconnetti() {
		try {
			this.gameState.unregisterObserver(this);
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
