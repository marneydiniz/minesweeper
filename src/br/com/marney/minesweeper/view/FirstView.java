package br.com.marney.minesweeper.view;

import java.awt.Button;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FirstView extends JFrame {
	
	private final Button startGameButton = new Button();
	FirstViewPanel panel = new FirstViewPanel();
	
	public FirstView() {
		panel.setBounds(20, 10, 220, 200);
		add(panel);
		setLayout(null);
		
		setTitle("Minesweeper");
		setSize(300, 310);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		startGameButton.setLabel("Start Game");
		startGameButton.setBounds(30, 220, 220, 30);
		startGameButton.addActionListener(e -> {
			
				if (panel.getEasyButton().isSelected()) {
					new GameView(16, 30, 10, this);
					setVisible(false);
				} else if (panel.getMediumButton().isSelected()) {
					new GameView(16, 30, 30, this);
					setVisible(false);
				} else if (panel.getHardButton().isSelected()) {
					new GameView(16, 30, 50, this);
					setVisible(false);
				}
				
		});
		add(startGameButton);
		
		setVisible(true);
		
	}

}
