package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import game.notify.Notify;
import gameDTO.GameStateDTO;

public class ClientInHandler implements Runnable {

private ObjectInputStream socketIn;
private GameStateDTO gameStateDTO;
	
	public ClientInHandler(ObjectInputStream socketIn, GameStateDTO gameStateDTO){
		this.socketIn=socketIn;
		this.gameStateDTO=gameStateDTO;
		
	}

	@Override
	public void run() {

		System.out.println("InHandler "+gameStateDTO);
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
