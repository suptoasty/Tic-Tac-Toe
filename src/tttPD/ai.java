package tttPD;

import java.util.Arrays;
import java.util.Random;
import tttPD.SearchTreeNode;
import tttPD.SearchTree;

public class ai {
	private board gameBoard = null;
	//private SearchTree<board> moves = null;
	private Node<board> root = null;
	private Random rand = new Random();
	private board b = new board();
	
	public ai(board gameBoard) {
		this.gameBoard = gameBoard;
		root = new Node<board>();
		//this.moves = new SearchTree<board>(root);
		b.setLastMove(-1);
		root.createChildren(b);
	}
	
	public BoardPosition updateBoard(board board) {
		this.gameBoard = board;
		return createTree(board);
	}
	
	public void setRoot(Node<board> node) {
		this.root = node;
		root.setParent(null);
	}
	
	public Node<board> getRoot() {
		return root;
	}
	
	public BoardPosition createTree(board board) {
//		if(root.getChildren() == null) {
////			System.out.println("Generating First tree");
////			root.generateFirstChildren(b);
//			root.createChildren(b);
//		}
//		
		for(Node<board> child : root.getChildren()) {
			board b2 = (board)child.getElement();
			if(b2.equals(b2.getSpaces(), board.getSpaces())) {
				//System.out.println("New root Found");
				root = child;
				root.setParent(null);
			}
		}					
		
			
		BoardPosition mov = root.getBestMove(true);
		
		return mov;
	}
	
	public BoardPosition getBestMove(Node<board> treeRoot) {
		//find best position to mark
		
		return null;
	}
	
	public int makeMove(int row, int column) {
		Random rand = new Random(3);
		row = clamp(row, 0, gameBoard.getRows());
		column = clamp(column, 0, gameBoard.getColumns());
		while(gameBoard.makeMove(-1, row, column) == -2 && !gameBoard.isEndGame()) {
			//change row and column here
			row = rand.nextInt(3);
			column = rand.nextInt(3);
			System.out.println(row+", "+column);
			row = clamp(row, 0, gameBoard.getRows());
			column = clamp(column, 0, gameBoard.getColumns());
		}
		
		return -1;
	}
	
	public static int clamp(int val, int min, int max) {
		max--;
		return Math.max(min, Math.min(max, val));
	}
}
