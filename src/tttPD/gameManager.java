package tttPD;

import java.util.Random;
import java.util.Scanner;

import tttUI.mainFrame;

// Interface for player/ai moves and board interaction
public class gameManager {
	private board gameBoard = null;
	private player gamePlayer = null;
	private ai gameAi = null;

	private boolean end = false;
	private boolean endTurn = false;
	private int[][] spaces = null;
	private boolean winner = false;
	private int victor = 0;

	private mainFrame frame = null;

	public gameManager() {
		gameBoard = new board();
		gamePlayer = new player(gameBoard);
		gameAi = new ai(gameBoard);
	}

	public gameManager(int[][] spaces) {
		gameBoard = new board();
		gamePlayer = new player(gameBoard);
		gameAi = new ai(gameBoard);
		this.spaces = spaces;
	}

	public gameManager(int rows, int columns) {
		gameBoard = new board(rows, columns);
		gamePlayer = new player(gameBoard);
		gameAi = new ai(gameBoard);
	}

	public void setFrame(mainFrame frame) {
		this.frame = frame;
	}

	public void runGame() {
		end = false;
		endTurn = false;
		while (!gameBoard.isEndGame()) {

			while (!endTurn) {
			}
			endTurn = false;
			// if(r != -1 || c != -1)
			// if(c != -1 || r != -1)
			// spaces[r][c] = -1;

			if (gameBoard.isEndGame()) {
				break;
			}

			// ai move here
			Random rand = new Random(3);
			gameAi.makeMove(rand.nextInt(3), rand.nextInt(3));
			gameBoard.printBoard();
			System.out.println("");
		}
	}

	public void runGameCLI() {
		Scanner scanner = new Scanner(System.in);
		end = false;
		endTurn = false;
		int r = -1, c = -1;
		while (!gameBoard.isEndGame()) {
			while (!endTurn) {
				// input up here
				System.out.println("Enter Row: ");
				r = scanner.nextInt();
				r--;
				r = clamp(r, 0, 3);
				System.out.println("Enter Column: ");
				c = scanner.nextInt();
				c--;
				c = clamp(c, 0, 3);
				endTurn = makeMove(1, r, c); // validation and output here
			}
			endTurn = false;
			// if(r != -1 || c != -1)
			// if(c != -1 || r != -1)
			// spaces[r][c] = -1;

			if (gameBoard.isEndGame()) {
				break;
			}

			// ai move here
			Random rand = new Random(3);
			gameAi.makeMove(rand.nextInt(3), rand.nextInt(3));
			gameBoard.printBoard();
			System.out.println("");
		}
		gameBoard.printBoard();
	}

	public board getBoard() {
		return gameBoard;
	}

	public ai getAi() {
		return gameAi;
	}

	public player getPlayer() {
		return gamePlayer;
	}

	public boolean makeMove(int symbol, int row, int column) {
		int err = gameBoard.makeMove(symbol, row, column);

		if (err < 0) {
			if (err == -1) {
				System.out.println("invalid move");
			} else if (err == -2) {
				System.out.println("space taken");
			}
			return false;
		}

		return true;
	}

	public boolean makeMoveClamp(int symbol, int row, int column) {
		row = clamp(row, 0, gameBoard.getRows());
		column = clamp(column, 0, gameBoard.getColumns());

		int err = gameBoard.makeMove(symbol, row, column);

		if (err < 0) {
			if (err == -1) {
				System.out.println("invalid move");
			} else if (err == -2) {
				System.out.println("space taken");
			}
			return false;
		}

		return true;
	}

	public void printBoard() {
		gameBoard.printBoard();
	}

	public static int clamp(int val, int min, int max) {
		max--;
		return Math.max(min, Math.min(max, val));
	}

}
