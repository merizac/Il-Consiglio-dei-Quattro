package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import game.azioni.Azione;
import game.notify.Notify;
import gameDTO.GameStateDTO;

public class ServerSocketView extends View implements Runnable {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private GameStateDTO gameStateDTO;
	
	
	
	public ServerSocketView(Socket socket, GameStateDTO gameStateDTO) throws IOException{
		this.socket=socket;
		this.gameStateDTO=gameStateDTO;
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
			this.socketOut.writeObject(gameStateDTO);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*while(true){
			
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
		
				
				
			
		}*/
	}

	@Override
	public void input(Scanner input) {
		// TODO Auto-generated method stub
		
	}
}
