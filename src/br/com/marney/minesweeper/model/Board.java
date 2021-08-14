package br.com.marney.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

import br.com.marney.minesweeper.exception.ExplosionException;

public class Board {
	
	private int lines;
	private int columns;
	private int mines;
	
	private final List<Zone> zones = new ArrayList<>();

	public Board(int lines, int columns, int mines) {
		super();
		this.lines = lines;
		this.columns = columns;
		this.mines = mines;
		
		createZones();
		createNeighbours();
		mixUpMines();
	}
	
	public void openZone(int line, int column) {
		try {
			zones.stream().filter(zone -> zone.getLine() == line && zone.getColumn() == column).findFirst().ifPresent(Zone::open);
		} catch (ExplosionException e) {
			zones.forEach(zone -> zone.setOpen(true));
			throw e;
		}
	}
	
	public void markZone(int line, int column) {
		zones.stream().filter(zone -> zone.getLine() == line && zone.getColumn() == column).findFirst().ifPresent(Zone::changeMarked);
	}

	private void createZones() {
		for (int line = 0; line < lines; line++) {
			for (int column = 0; column < columns; column++) {
				zones.add(new Zone(line, column));
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
	
	public boolean gameComplete() {
		return zones.stream().allMatch(Zone::questComplete);
	}
	
	public void resetGame() {
		zones.forEach(Zone::resetZone);
		mixUpMines();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  ");
		for (int column = 0; column < columns; column++) {
			sb.append(" ");
			sb.append(column);
			sb.append(" ");
		}
		sb.append("\n");
		
		int i = 0;
		for (int line = 0; line < lines; line++) {
			sb.append(" ");
			sb.append(line);
			for (int column = 0; column < columns; column++) {
				sb.append(" ");
				sb.append(zones.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
	

}