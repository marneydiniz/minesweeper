package br.com.marney.minesweeper.view;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class FirstViewPanel extends JPanel {

	private final JLabel gameTitle = new JLabel();
	private final JLabel dificultyTitle = new JLabel();
	private final JRadioButton easyButton = new JRadioButton();
	private final JRadioButton mediumButton = new JRadioButton();
	private final JRadioButton hardButton = new JRadioButton();
	private final ButtonGroup menuDificulty = new ButtonGroup();
	

	public FirstViewPanel() {

		setLayout(null);

		gameTitle.setText("Minesweeper");
		gameTitle.setFont(new Font("courier", Font.BOLD, 30));
		gameTitle.setBounds(20, 1, 200, 35);
		add(gameTitle);

		dificultyTitle.setText("Select Game Dificulty: ");
		dificultyTitle.setFont(new Font("courier", Font.PLAIN, 15));
		dificultyTitle.setBounds(20, 35, 250, 35);
		add(dificultyTitle);

		easyButton.setText("Easy");
		easyButton.setBounds(80, 70, 250, 35);
		mediumButton.setText("Medium");
		mediumButton.setBounds(80, 100, 250, 35);
		hardButton.setText("Hard");
		hardButton.setBounds(80, 130, 250, 35);

		menuDificulty.add(easyButton);
		menuDificulty.add(mediumButton);
		menuDificulty.add(hardButton);

		add(easyButton);
		add(mediumButton);
		add(hardButton);

		setVisible(true);
	}


	public JRadioButton getEasyButton() {
		return easyButton;
	}


	public JRadioButton getMediumButton() {
		return mediumButton;
	}


	public JRadioButton getHardButton() {
		return hardButton;
	}
	
}
