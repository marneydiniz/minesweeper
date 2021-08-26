package br.com.marney.minesweeper.view;

import javax.swing.JFrame;

import br.com.marney.minesweeper.model.Board;

@SuppressWarnings("serial")
public class GameView extends JFrame {
	
	public GameView(int line, int collumn, int mine, JFrame firstView) {
		Board board = new Board(line, collumn, mine);
		add(new BoardPanel(board, firstView, this));
		
		setTitle("Minesweeper");
		setSize(690, 438);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		new FirstView();
		
	}

}
