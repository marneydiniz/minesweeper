package br.com.marney.minesweeper.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JFrame;

public class Board implements ZoneObserver {
	
	private final int lines;
	private final int columns;
	private final int mines;
	
	private final List<Zone> zones = new ArrayList<>();
	private final List<BoardObserver> observers = new ArrayList<>(); 

	public Board(int lines, int columns, int mines) {
		super();
		this.lines = lines;
		this.columns = columns;
		this.mines = mines;
		
		createZones();
		createNeighbours();
		mixUpMines();
	}
	
	public void forEachZone(Consumer<Zone> function) {
		zones.forEach(function);
	}
	
	public int getLines() {
		return lines;
	}

	public int getColumns() {
		return columns;
	}

	public void registerObserver(BoardObserver observer) {
		observers.add(observer);
	}

	private void notifyObservers (boolean result) {
		observers.forEach(o -> o.eventBoardUp(new ResultEvent(result)));
	}
	
	public void openZone(int line, int column) {
			zones.stream().filter(zone -> zone.getLine() == line && zone.getColumn() == column).findFirst().ifPresent(Zone::open);
	}
	
	public void markZone(int line, int column) {
		zones.stream().filter(zone -> zone.getLine() == line && zone.getColumn() == column).findFirst().ifPresent(Zone::changeMarked);
	}

	public boolean gameComplete() {
		return zones.stream().allMatch(Zone::questComplete);
	}
	
	public void resetGame(JFrame firstView, JFrame gameView) {
		zones.forEach(Zone::resetZone);
		mixUpMines();
		firstView.setVisible(true);
		gameView.dispose();
	}

	@Override
	public void eventUp(Zone zone, EventZone event) {
		if(event == EventZone.EXPLOSION) {
			showMines();
			notifyObservers(false);
		} else if (gameComplete()) {
			notifyObservers(true);
		}
	}
	
	private void createZones() {
		for (int line = 0; line < lines; line++) {
			for (int column = 0; column < columns; column++) {
				Zone zone = new Zone(line, column);
				zone.registerObserver(this);
				zones.add(zone);
			}
		}
	}

	private void createNeighbours() {
		for (Zone zone1 : zones) {
			for (Zone zone2 : zones) {
				zone1.addNeighbour(zone2);
			}
		}
	}
	
	private void mixUpMines() {
		long armedMines = 0;
		while (armedMines < mines) {
			int random = (int) (Math.random() * zones.size());
			zones.get(random).changeMined();
			armedMines = zones.stream().filter(Zone::isMined).count();
		}
	}
	
	private void showMines() {
		zones.stream().filter(Zone::isMined).filter(z -> !z.isMarked()).forEach(zone -> zone.setOpen(true));
	}
	
}