package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import game.GameState;
import game.notify.Notify;

public class ClientInHandler implements Runnable {

private ObjectInputStream socketIn;
private GameState gameState;
	
	public ClientInHandler(ObjectInputStream socketIn, GameState gameState){
		this.socketIn=socketIn;
		this.gameState=gameState;
		
	}

	@Override
	public void run() {

		System.out.println("InHandler "+gameState);
		while(true){
			
			try {
				Notify line;
				line=(Notify) socketIn.readObject();
				line.stamp();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
