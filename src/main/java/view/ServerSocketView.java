package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import game.GameState;
import game.azioni.Azione;
import game.notify.Notify;
import gameDTO.GameStateDTO;

public class ServerSocketView extends View implements Runnable {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private GameState gameState;
	
	
	
	public ServerSocketView(Socket socket, GameState gameState) throws IOException{
		this.socket=socket;
		this.gameState=gameState;
		this.socketIn=new ObjectInputStream(socket.getInputStream());
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
	}
	
	
	@Override
	public void update(Notify o) {

		System.out.println("Sending to the client "+o);
		try{
			this.socketOut.writeObject(o);
			this.socketOut.flush();
		}catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		
		try {
			this.socketOut.writeObject(gameState);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			
			try {
				Object object=socketIn.readObject();
				if(object instanceof Azione){
					Azione action= (Azione) object;
					System.out.println("VIEW: received the action "+ action);
					this.notifyObserver(action);
				}
				
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void input(Scanner input) {
		// TODO Auto-generated method stub
		
	}
}
