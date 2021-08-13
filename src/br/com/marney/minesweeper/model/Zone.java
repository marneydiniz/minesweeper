package br.com.marney.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

import br.com.marney.minesweeper.exception.ExplosionException;

public class Zone {
	
	private final int line;
	private final int column;
	
	private boolean open = false;
	private boolean mined = false;
	private boolean marked = false;
	
	private List<Zone> neighbours = new ArrayList<>();
	
	Zone (int line, int column){
		this.line = line;
		this.column = column;
	}
	
	boolean addNeighbour (Zone neighbour) {
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
	
	void changeMarked() {
		if(!open) {
			marked = !marked;
		}
	}
	
	void changeMined() {
		if(!mined) {
			mined = true;
		}
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isClosed() {
		return !open;
	}
	
	public boolean isMined() {
		return mined;
	}
	
	boolean open() {
		if (!open && !marked) {
			open = true;
			
			if(mined) {
				throw new ExplosionException(); 
			}
			
			if(safeNeighbours()) {
				neighbours.stream().forEach(Zone::open);
			} return true;
		} return false;
	}
	
	boolean safeNeighbours() {
		return neighbours.stream().noneMatch(v -> v.mined);
	}

	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
	
	boolean questComplete() {
		boolean uncovered = !mined && open;
		boolean covered = mined && marked;
		return uncovered || covered;
	}
	
	long minesOnNeighbour() {
		return neighbours.stream().filter(neighbour -> neighbour.mined).count();
	}

	void resetZone() {
		open = false;
		mined = false;
		marked = false;
	}
	
	@Override
	public String toString() {
		if (marked) {
			return "x";
		} else if (open && mined) {
			return "*";
		} else if (open && minesOnNeighbour() > 0) {
			return Long.toString(minesOnNeighbour());
		} else if (open) {
			return " ";
		} return "?";
	}
	
}
