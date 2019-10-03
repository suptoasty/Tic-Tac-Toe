package tttPD;

import java.util.TreeMap;

public class player {
	private board gameBoard = null;
	private TreeMap<String, Character> moves = null;
	private int moveCount = 0;
	
	public player(board gameBoard) {
		this.gameBoard = gameBoard;
		moves = new TreeMap<String, Character>();
	}

	public void makeMove(int row, int column) {
		moves.put(Integer.toString(moveCount), 1);
		return;
	}
	
	public void getMoves() {
		return;
	}
}
