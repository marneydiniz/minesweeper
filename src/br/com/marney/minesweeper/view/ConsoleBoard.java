package br.com.marney.minesweeper.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.marney.minesweeper.exception.ExitException;
import br.com.marney.minesweeper.exception.ExplosionException;
import br.com.marney.minesweeper.model.Board;

public class ConsoleBoard {
	
	private Board board;
	private Scanner input = new Scanner(System.in);
	
	public ConsoleBoard(Board board) {
		this.board = board;
		
		executeGame();
	}

	private void executeGame() {
		try {
			boolean next = true;
			while (next) {
				loopGame();
				
				System.out.println("Go to the Next Game?? (Y/n) ");
				String response = input.nextLine();
				
				if ("n".equalsIgnoreCase(response)) {
					next = false;
				} else {
					board.resetGame();
				}
			}
		} catch (ExitException e) {
			System.out.println("Exit!!");
		} finally {
			input.close();
		}
	}

	private void loopGame() {
		try {
			
			while(!board.gameComplete()) {
				System.out.println(board);
				
				String enter = getConsoleInput("Enter (line, column): ");
				
				Iterator<Integer> xy = Arrays.stream(enter.split(",")).map(e -> Integer.parseInt(e.trim())).iterator();
				
				enter = getConsoleInput("1 to Open or 2 to (Un)mark: ");
				
				if ("1".equalsIgnoreCase(enter)) {
					board.openZone(xy.next(), xy.next());
				} else if("2".equalsIgnoreCase(enter)) {
					board.markZone(xy.next(), xy.next());
				}
				
			}
			
			System.out.println(board);
			System.out.println("You Won!!");
		} catch (ExplosionException e) {
			System.out.println(board);
			System.out.println("Game Over!"); 
		}
	}

	private String getConsoleInput(String text) {
		System.out.print(text);
		String inputText = input.nextLine();
		if ("exit".equalsIgnoreCase(inputText)) {
			throw new ExitException();
		}
		return inputText;
	}
	
}
