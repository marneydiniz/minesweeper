package br.com.marney.minesweeper.view;

import javax.swing.JFrame;

import br.com.marney.minesweeper.model.Board;

@SuppressWarnings("serial")
public class MainView extends JFrame {
	
	public MainView() {
		Board board = new Board(16, 30, 50);
		add(new BoardPanel(board));
		
		setTitle("Minesweeper");
		setSize(690, 438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new MainView();
	}

}
