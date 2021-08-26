package br.com.marney.minesweeper.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.marney.minesweeper.model.EventZone;
import br.com.marney.minesweeper.model.Zone;
import br.com.marney.minesweeper.model.ZoneObserver;

@SuppressWarnings("serial")
public class ZoneButton extends JButton implements ZoneObserver, MouseListener {
	
	private final Color DEFAUT_BG = new Color (184, 184, 184);
	private final Color MARKED_BG = new Color (8, 179, 247);
	private final Color EXPLOSION_BG = new Color (189, 66, 68);
	private final Color GREEN_TEXT = new Color (0, 100, 0);
	
	private Zone zone;
	
	public ZoneButton(Zone zone) {
		this.zone = zone;
		setBackground(DEFAUT_BG);
		setBorder(BorderFactory.createBevelBorder(0));
		
		addMouseListener(this);
		zone.registerObserver(this);
	}

	@Override
	public void eventUp(Zone zone, EventZone event) {
		switch (event) {
		case OPEN:
			applyOpenStyle();
			break;
		case MARKED:
			applyMarkedStyle();
			break;
		case EXPLOSION:
			applyExplosiondStyle();
			break;
		default:
			applyDefaultStyle();
		}
	}

	private void applyDefaultStyle() {
		setBackground(DEFAUT_BG);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");
	}

	private void applyExplosiondStyle() {
		setBackground(EXPLOSION_BG);
		setForeground(Color.WHITE);
		setText("X");
	}

	private void applyMarkedStyle() {
		setBackground(MARKED_BG);
		setForeground(Color.BLACK);
		setText("M");
	}

	private void applyOpenStyle() {

		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if (zone.isMined()) {
			setBackground(EXPLOSION_BG);
			return;
		}
		
		setBackground(DEFAUT_BG);
		
		switch (zone.minesOnNeighbour()) {
		case 1:
			setForeground(GREEN_TEXT);
			break;
		case 2:
			setForeground(Color.BLUE);
			break;
		case 3:
			setForeground(Color.YELLOW);
			break;
		case 4:
		case 5:
		case 6:
			setForeground(Color.RED);
			break;
		default:
			setForeground(Color.PINK);
		}
		
		setText(!zone.safeNeighbours() ? zone.minesOnNeighbour() + "" : "");
	}
	
	//Mouse events Interface
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			zone.open();
		} else {
			zone.changeMarked();
		}
	}

	//Unused Mouse Events
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
