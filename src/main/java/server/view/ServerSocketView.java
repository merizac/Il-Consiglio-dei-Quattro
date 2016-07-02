package server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.azioniDTO.AzioneDTO;
import common.azioniDTO.AzioneMappaDTO;
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
import utility.Utils;

public class ServerSocketView extends View implements Runnable {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private GameState gameState;
	private Giocatore giocatore;
	private static final Logger log = Logger.getLogger(ServerSocketView.class.getName());

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
			Utils.print("[SERVER] Inviata notifica " + o + " al giocatore " + giocatore.getNome());
			try {
				this.socketOut.writeObject(o.notifyToClientNotify());
				this.socketOut.reset();
				this.socketOut.flush();
			} catch (IOException e) {
				log.log(Level.SEVERE, "Errore nell'invio della notifica sul socket", e);
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
		} catch (ClassNotFoundException e) {
			log.log(Level.SEVERE, "Errore l'oggetto ricevuto non è un giocatoreDTO", e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Errore nella lettura dal socket", e);
		}

		Server.getInstance().aggiungiGiocatore(giocatore, this);

		while (true) {

			try {
				Object object = socketIn.readObject();

				AzioneDTO action = (AzioneDTO) object;
				if (action instanceof ExitDTO) {
					disconnetti();
					Exit exit = new Exit();
					exit.setGiocatore(giocatore);
					this.notifyObserver(exit);
					return;
				} else if (action instanceof AzioneMappaDTO) {
					Server.setMappa(((AzioneMappaDTO) action).getMappa());
				}

				else {
					AzioneVisitor azioneVisitor = new AzioneVisitorImpl(gameState, giocatore);
					Azione azione = null;
					try {
						azione = action.accept(azioneVisitor);
					} catch (ParameterException e) {
						update(new MessageNotify(e.getMessage(), Arrays.asList(gameState.getGiocatoreCorrente())));
						Utils.print("[SERVER] Ricevuta l'azione " + azione + " dal giocatore "
								+ this.giocatore.getNome() + " con errore: " + e.getMessage());
						continue;
					}
					System.out
							.println("[SERVER] Ricevuta l'azione " + azione + " dal giocatore " + giocatore.getNome());

					if ((azione.isTurno(giocatore, gameState)
							&& gameState.getStato().daEseguire(gameState.getStato().getAzioni(), azione))
							|| (azione instanceof Chat)) {
						this.notifyObserver(azione);
						Utils.print("[SERVER] Inviata l'azione " + azione);
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
		this.gameState.unregisterObserver(this);
		try {
			this.socket.close();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Errore nella chiusura del socket", e);
		}

	}

}
