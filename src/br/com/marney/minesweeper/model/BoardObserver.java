package br.com.marney.minesweeper.model;

@FunctionalInterface
public interface BoardObserver {
	
	public void eventBoardUp (ResultEvent event);

}
