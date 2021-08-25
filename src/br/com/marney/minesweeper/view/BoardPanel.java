package br.com.marney.minesweeper.view;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.marney.minesweeper.model.Board;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	public BoardPanel(Board board) {
		
		setLayout(new GridLayout(board.getLines(), board.getColumns()));
		
		board.forEachZone(z -> add(new ZoneButton(z)));
		
		board.registerObserver(e -> {
			
			SwingUtilities.invokeLater(() -> {
				if (e.isWon()) {
					JOptionPane.showMessageDialog(this, "You Won!");
				} else {
					JOptionPane.showMessageDialog(this, "Game Over!");
				}
				
				board.resetGame();
			});
		});
	}
}
