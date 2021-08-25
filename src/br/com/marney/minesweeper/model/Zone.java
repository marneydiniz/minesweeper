package br.com.marney.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

public class Zone {
	
	private final int line;
	private final int column;
	
	private boolean open = false;
	private boolean mined = false;
	private boolean marked = false;
	
	private List<Zone> neighbours = new ArrayList<>();
	private List<ZoneObserver> observers = new ArrayList<>();
	
	public Zone (int line, int column){
		this.line = line;
		this.column = column;
	}
	
	public boolean addNeighbour (Zone neighbour) {
		boolean distinctLine = this.line != neighbour.line;
		boolean distinctColumn = this.column != neighbour.column;
		boolean diagonal = distinctLine && distinctColumn;
		
		int distanceLine = Math.abs(this.line - neighbour.line);
		int distanceColumn = Math.abs(this.column - neighbour.column);
		int distance = distanceColumn + distanceLine;
		
		if ((distance == 1 && !diagonal) || (distance == 2 && diagonal)) {
			neighbours.add(neighbour);
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public boolean isOpen() {
		return open;
	}

	public boolean isClosed() {
		return !open;
	}
	
	public boolean isMined() {
		return mined;
	}
	
	public boolean open() {
		if (!open && !marked) {
			if(mined) {
				notifyObservers(EventZone.EXPLOSION);
				return true;
			}
			
			setOpen(true);
			
			if(safeNeighbours()) {
				neighbours.forEach(Zone::open);
			} return true;
		} return false;
	}
	
	public boolean safeNeighbours() {
		return neighbours.stream().noneMatch(v -> v.mined);
	}
	
	public boolean questComplete() {
		boolean uncovered = !mined && open;
		boolean covered = mined && marked;
		return uncovered || covered;
	}

	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int minesOnNeighbour() {
		return (int) neighbours.stream().filter(neighbour -> neighbour.mined).count();
	}

	public void registerObserver (ZoneObserver observer) {
		observers.add(observer);
	}
	
	public void setOpen(boolean open) {
		this.open = open;
		
		if (open) {
			notifyObservers(EventZone.OPEN);
		}
	}
	
	public void resetZone() {
		open = false;
		mined = false;
		marked = false;
		notifyObservers(EventZone.RESET);
	}

	public void changeMarked() {
		if(!open) {
			marked = !marked;
			
			if (marked) {
				notifyObservers(EventZone.MARKED);
			} else {
				notifyObservers(EventZone.UNMARKED);
			}
		}
	}
	
	public void changeMined() {
		if(!mined) {
			mined = true;
		}
	}
	
	private void notifyObservers (EventZone event) {
		observers.forEach(o -> o.eventUp(this, event));
	}
	
}
