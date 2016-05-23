package mapping;

import java.io.IOException;

import org.modelmapper.ModelMapper;

import game.GameState;
import mapping.gameToMap.GameStateDTO;

public class ProvaMapper {

	public static void main(String[] args) throws IOException {
	GameState gameState =new GameState();
	ModelMapper modelMapper=new ModelMapper();
	GameStateDTO gameStateDTO=modelMapper.map(gameState, GameStateDTO.class);
	System.out.println(gameStateDTO);
	

	}

}
