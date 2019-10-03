package tttPD;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import tttPD.BoardPosition;

public class Node<E> {
	private E element = null;
	private int weight = 0;
	private ArrayList<Node<E>> children = null;
	private Node<E> parent = null;
	private int size = 0;
	private int depth = 0;
	BoardPosition b;
	BoardPosition nextMove;
	
	public Node() {
		
	}
	
	public Node(E element) {
		this.element = element;
	}
	
	public Node(E element, Node<E> parent) {
		this.element = element;
		this.parent = parent;
	}
	
	public Node(E element, Node<E> parent, int weight) {
		this.element = element;
		this.parent = parent;
		this.weight = weight;
	}
	
	public void setNextMove(BoardPosition next) {
		nextMove = next;
	}
	
	public BoardPosition getNextMove() {
		return nextMove;
	}
	
	public void setBoardPosition(BoardPosition b) {
		this.b = b;
	}
	
	public BoardPosition getBoardPosition() {
		return b;
	}
	
	public Node<E> addChild(Node<E> node) {
		node.setDepth(depth+1);
		node.setParent(this);
		
		children.add(node);
		return node;
	}
	
	public boolean isEmpty() {
		return (size()==0);
	}
	
	public Node<E> root() {
		if(isRoot()) {
			return this;
		}
		
		Node<E> temp = this;
		while(temp.parent != null) {
			temp = temp.parent;
		}
		return temp;
	}
	
	public boolean isRoot() {
		return (parent==null);
	}
	
	public int numChildren() {
		return children.size();
	}
	
	public int size() {
		return numChildren()+1;
	}
	
	public boolean isInternal() {
		return !(numChildren()==0);
	}
	
	public boolean isExternal() {
		return !isInternal();
	}
	
	public ArrayList<Node<E>> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Node<E>> children) {
		this.children = children;
	}

	public Node<E> getParent() {
		return parent;
	}

	public void setParent(Node<E> parent) {
		this.parent = parent;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public int getWeight() {
		return weight;
	}

	public int setWeight(int weight) {
		this.weight = weight;
		return weight;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public static <E> void printTree(Node<E> node, String appender) {
		System.out.println(appender + node.getElement());
		node.getChildren().forEach(each-> printTree(each, appender + appender));
	}
	
	public String toString() {
		if(element != null) {
			return element.toString();
		}
		
		return "empty node\n";
	}
	
	//tic tac toe stuff
	public void createChildren(board board) {	
		if(isRoot()) this.element = (E)board;
		if(children == null) children = new ArrayList<Node<E>>();
		generateChildren(board);	
	}
	
	private int generateChildren(board board) {
		if(board.isWin(-1)) {
			return getWeight();
		} 
		else if (board.isWin(1)) {
			return getWeight();
		}
		
		if(board.isNearWin(-1) && board.isNearWin(1)) {
			setWeight(0);
		}
		else if(board.isNearWin(-1)) {
			setWeight(-1);
		}
		
		if(children == null) children = new ArrayList<Node<E>>();
		
		for(int n = 0; n < board.getRows(); n++) {
			for(int s = 0; s < board.getColumns(); s++) {
				board b = board.deepClone();
					
				if(b.getSpaces()[n][s]==0) {
					Node<E> node = new Node<E>();
					b.makeMoveClamp(b.getLastMove()*-1, n, s);
					node.setBoardPosition(new BoardPosition(n, s));
					node.setElement((E)b);
			
					node.generateChildren(b.deepClone());
					int d = 0;
					if(b.isWin(-1)) {
						d = node.setWeight(-1);
					} else if(b.isWin(1)) {
						d = node.setWeight(1);
					}
					if(b.isNearWin(-1) && b.isNearWin(1)) {
						d = node.setWeight(0);
					}
					else if(b.isNearWin(-1)) {
						 d = node.setWeight(-1);
					}
					
					addChild(node);
					
				}
			}
		}
		
		return 0;
	}

	public BoardPosition getBestMove(boolean isMax) {
		int d = 0;

		Node<E> bestmove = null;
		int max = -10;
		for(Node<E> child : root().getChildren()) {
			
			d = child.minmax();
			if(d>max) {
				max = d;
				bestmove = child;
			}
		}
		
		return bestmove.getBoardPosition();
	}
	
	public int minmax() {	
		int min = 10;
		int max = -10;
		board b = (board)getElement();

		if(b.isWin(-1)) {
			return 1;
		}  else if (b.isWin(1)) {
			return -1;
		}
		if(b.isEndGame()) return 0;
		
		int d = 0;
		board rootBoard = (board)root().getElement();
		if(b.getLastMove()==rootBoard.getLastMove()) {
			for(Node<E> child : getChildren()) {
				d = child.minmax();
				if(d>max) {
					max = d;
				}
			}
			return max;
		} else {
			for(Node<E> child : getChildren()) {
				d = child.minmax();
				if(d<min) {
					min = d;
				}
			}
			return min;
		}
	}
	
}