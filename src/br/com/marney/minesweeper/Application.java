package br.com.marney.minesweeper;

import br.com.marney.minesweeper.model.Board;
import br.com.marney.minesweeper.view.ConsoleBoard;

public class Application {
	
	public static void main(String[] args) {
		
		Board board = new Board(6, 6, 3);
		
		new ConsoleBoard(board);

	}

}
