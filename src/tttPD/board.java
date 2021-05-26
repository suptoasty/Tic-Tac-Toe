package tttPD;

// the game board
public class board {
	private int[][] playerMoves = null; // record of player moves
	private int[][] aiMoves = null; // record of ai moves
	private int[][] boardSpaces = null; // squares in the game board
	private int boardRows = 3;
	private int boardColumns = 3;
	private int lastMoved = 0; // last player/ai to move

	public board() {
		clearBoard();
	}

	// only used for cli, not gui
	public board(int rows, int columns) {
		boardRows = rows;
		boardColumns = columns;

		clearBoard();
	}

	// mark board space
	public void setBoardSpaces(int[][] b) {
		boardSpaces = b;
	}

	// clear the board
	public void clearBoard() {
		playerMoves = new int[boardRows][boardColumns];
		aiMoves = new int[boardRows][boardColumns];
		boardSpaces = new int[boardRows][boardColumns];

		// set each space to neutral symbol value of 0
		for (int i = 0; i < boardRows; i++) {
			for (int n = 0; n < boardColumns; n++) {
				boardSpaces[i][n] = 0;
			}
		}
	}

	public void setLastMove(int move) {
		this.lastMoved = move;
	}

	public int getLastMove() {
		return lastMoved;
	}

	// evaluat win condition
	public boolean isEndGame() {
		if (isWin(lastMoved) && lastMoved != 0) {
			return true;
		}

		int filledSpaces = 0;
		for (int i = 0; i < boardRows; i++) {
			for (int n = 0; n < boardColumns; n++) {
				if (boardSpaces[i][n] != 0) {
					filledSpaces++;
				}
			}
		}

		if (filledSpaces >= (boardRows * boardColumns)) {
			return true;
		}

		return false;
	}

	public int makeMove(int symbol, int row, int column) {
		lastMoved = symbol;
		row = clamp(row, 0, boardRows);
		column = clamp(column, 0, boardColumns);
		if (symbol == -1) {
			if (boardSpaces[row][column] != 0) {
				return -2;
			}
			aiMoves[row][column] = symbol;
			boardSpaces[row][column] = symbol;
			return 0;
		} else if (symbol == 1) {
			if (boardSpaces[row][column] != 0) {
				return -2;
			}
			playerMoves[row][column] = symbol;
			boardSpaces[row][column] = symbol;
			return 0;
		}

		return -1;
	}

	public boolean makeMoveClamp(int symbol, int row, int column) {
		row = clamp(row, 0, getRows());
		column = clamp(column, 0, getColumns());
		int err = makeMove(symbol, row, column);
		if (err < 0) {
			if (err == -1) {
				System.out.println("invalid move");
			} else if (err == -2) {
				System.out.println("space taken");
			}
			// System.out.println(toString());
			return false;
		} else {
			// System.out.println(toString());
			return true;
		}
	}

	public int getEmptySpaceCount() {
		int empty = 0;
		for (int i = 0; i < this.boardRows; i++) {
			for (int n = 0; n < this.boardColumns; n++) {
				int row = clamp(i, 0, boardRows);
				int column = clamp(n, 0, boardColumns);
				if (boardSpaces[row][column] == 0) {
					empty++;
				}
			}
		}

		return empty;
	}

	public board getBoard() {
		return this;
	}

	public int getRows() {
		return this.boardRows;
	}

	public int getColumns() {
		return this.boardColumns;
	}

	public void printBoard() {
		for (int i = 0; i < boardRows; i++) {
			for (int n = 0; n < boardColumns; n++) {
				System.out.print("[" + boardSpaces[i][n] + "] ");
			}
			System.out.print("\n");
		}
		System.out.println("");
	}

	public int getPosition(int r, int c) {
		r = clamp(r, 0, this.boardRows);
		c = clamp(c, 0, this.boardColumns);
		return boardSpaces[r][c];
	}

	public int[][] getSpaces() {
		return this.boardSpaces;
	}

	public void setSpace(int i, int r, int c) {
		int[][] spaces = getSpaces();
		spaces[r][c] = i;
	}

	// did a player get 3 in a row
	public boolean isWin(int symbol) {
		return ((boardSpaces[0][0] + boardSpaces[0][1] + boardSpaces[0][2] == symbol * 3)
				|| (boardSpaces[1][0] + boardSpaces[1][1] + boardSpaces[1][2] == symbol * 3)
				|| (boardSpaces[2][0] + boardSpaces[2][1] + boardSpaces[2][2] == symbol * 3)
				|| (boardSpaces[0][0] + boardSpaces[1][0] + boardSpaces[2][0] == symbol * 3)
				|| (boardSpaces[0][1] + boardSpaces[1][1] + boardSpaces[2][1] == symbol * 3)
				|| (boardSpaces[0][2] + boardSpaces[1][2] + boardSpaces[2][2] == symbol * 3)
				|| (boardSpaces[0][0] + boardSpaces[1][1] + boardSpaces[2][2] == symbol * 3)
				|| (boardSpaces[2][0] + boardSpaces[1][1] + boardSpaces[0][2] == symbol * 3));
	}

	// is a player about to get 3 in a row
	public boolean isNearWin(int symbol) {
		return ((boardSpaces[0][0] + boardSpaces[0][1] + boardSpaces[0][2] == symbol * 2)
				|| (boardSpaces[1][0] + boardSpaces[1][1] + boardSpaces[1][2] == symbol * 2)
				|| (boardSpaces[2][0] + boardSpaces[2][1] + boardSpaces[2][2] == symbol * 2)
				|| (boardSpaces[0][0] + boardSpaces[1][0] + boardSpaces[2][0] == symbol * 2)
				|| (boardSpaces[0][1] + boardSpaces[1][1] + boardSpaces[2][1] == symbol * 2)
				|| (boardSpaces[0][2] + boardSpaces[1][2] + boardSpaces[2][2] == symbol * 2)
				|| (boardSpaces[0][0] + boardSpaces[1][1] + boardSpaces[2][2] == symbol * 2)
				|| (boardSpaces[2][0] + boardSpaces[1][1] + boardSpaces[0][2] == symbol * 2));
	}

	// clone board
	public board deepClone() {
		board newBoard = new board(getRows(), getColumns());
		int[][] b = new int[getRows()][getColumns()];
		for (int i = 0; i < getRows(); i++) {
			for (int n = 0; n < getColumns(); n++) {
				int row = clamp(i, 0, getRows());
				int column = clamp(n, 0, getColumns());
				b[row][column] = boardSpaces[row][column];
				newBoard.setBoardSpaces(b);
			}
		}
		newBoard.setLastMove(lastMoved);
		return newBoard;
	}

	// evaluate equality of 2d game board
	public static boolean equals(int[][] m1, int[][] m2) {
		if (m1.length != m2.length)
			return false;
		for (int i = 0; i < m1.length; i++) {
			if (m1[i].length != m2[i].length)
				return false;
			for (int j = 0; j < m1[i].length; j++) {
				int b1 = m1[i][j];
				int b2 = m2[i][j];
				if (b1 != b2)
					return false;
			}
		}
		return true;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.boardRows; i++) {
			for (int n = 0; n < this.boardColumns; n++) {
				builder.append("[" + boardSpaces[i][n] + "] ");
			}
			builder.append("\n");
		}

		return builder.toString();
	}

	public static int clamp(int val, int min, int max) {
		max--;
		return Math.max(min, Math.min(max, val));
	}
}
