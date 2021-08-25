package br.com.marney.minesweeper.model;

@FunctionalInterface
public interface ZoneObserver {
	
	public void eventUp (Zone zone, EventZone event);

}
