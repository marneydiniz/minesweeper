package br.com.marney.minesweeper;

import java.util.Scanner;

import br.com.marney.minesweeper.model.Board;
import br.com.marney.minesweeper.view.ConsoleBoard;

public class Application {
	
	public static void main(String[] args) {
		
		Scanner enter = new Scanner(System.in);
		
		System.out.println("*** MINESWEEPER (Console) ***");
		System.out.print("ENTER TOTAL LINES: ");
		int enterLines = enter.nextInt();
		System.out.print("ENTER TOTAL COLUMN: ");
		int enterColumns = enter.nextInt();
		System.out.print("ENTER TOTAL MINES: ");
		int enterMines = enter.nextInt();
		
		Board board = new Board(enterLines, enterColumns, enterMines);
		new ConsoleBoard(board);
		
		enter.close();
	}

}
