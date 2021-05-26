package tttPD;

import java.util.ArrayList;

// Multi Demensional tree, one node must be root
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

	// add child to current node
	public Node<E> addChild(Node<E> node) {
		node.setDepth(depth + 1);
		node.setParent(this);

		children.add(node);
		return node;
	}

	public boolean isEmpty() {
		return (size() == 0);
	}

	// return the root node for tree
	public Node<E> root() {
		if (isRoot()) {
			return this;
		}

		// root is only node without parent
		Node<E> temp = this;
		while (temp.parent != null) {
			temp = temp.parent;
		}

		return temp;
	}

	public boolean isRoot() {
		return (parent == null);
	}

	// child count for node
	public int numChildren() {
		return children.size();
	}

	// branch size including this node
	public int size() {
		return numChildren() + 1;
	}

	// ! node is leaf
	public boolean isInternal() {
		return !(numChildren() == 0);
	}

	// node is leaf
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
		node.getChildren().forEach(each -> printTree(each, appender + appender));
	}

	public String toString() {
		if (element != null) {
			return element.toString();
		}

		return "empty node\n";
	}

	// tic tac toe stuff
	public void createChildren(board board) {
		if (isRoot())
			this.element = (E) board;
		if (children == null)
			children = new ArrayList<Node<E>>();
		generateChildren(board);
	}

	// generate move permutation tree
	private int generateChildren(board board) {
		// is current perumation a win for ai or player
		if (board.isWin(-1)) {
			return getWeight();
		} else if (board.isWin(1)) {
			return getWeight();
		}

		// is current permuation almost a win for ai or player
		// if so weight tree to find advantageous route for ai
		if (board.isNearWin(-1) && board.isNearWin(1)) {
			setWeight(0);
		} else if (board.isNearWin(-1)) {
			setWeight(-1);
		}

		if (children == null)
			children = new ArrayList<Node<E>>();

		// clone board positions, starting with blank board
		for (int n = 0; n < board.getRows(); n++) {
			for (int s = 0; s < board.getColumns(); s++) {
				board b = board.deepClone();

				// if blank board
				if (b.getSpaces()[n][s] == 0) {
					Node<E> node = new Node<E>();

					// mark current node in turn order player -> ai -> player -> etc.
					b.makeMoveClamp(b.getLastMove() * -1, n, s);
					node.setBoardPosition(new BoardPosition(n, s));
					node.setElement((E) b);

					// recursively create permuations of above board layout
					node.generateChildren(b.deepClone());

					// outright win for either player
					if (b.isWin(-1)) {
						node.setWeight(-1); // ai win
					} else if (b.isWin(1)) {
						node.setWeight(1); // player win
					}

					// both are close to winning or a tie
					if (b.isNearWin(-1) && b.isNearWin(1)) {
						node.setWeight(0); // neutral / tie
					} else if (b.isNearWin(-1)) {
						node.setWeight(-1); // ai is about to win (2 in a row)
					}

					// add current board permuation to tree
					addChild(node);
				}
			}
		}

		return 0;
	}

	// get next move to maximize ai chance of winning
	public BoardPosition getBestMove(boolean isMax) {
		int determinant = 0; // used to help determine winning weight at node depth

		// greatest weight for ai win
		Node<E> bestmove = null;
		int max = -10;
		for (Node<E> child : root().getChildren()) {

			determinant = child.minmax();
			if (determinant > max) {
				max = determinant;
				bestmove = child;
			}
		}

		return bestmove.getBoardPosition();
	}

	// rate tree weight for ai win / tie over player win
	public int minmax() {
		int min = 10;
		int max = -10;
		board b = (board) getElement();

		// is win for ai
		if (b.isWin(-1)) {
			return 1;
		}
		// is win for player
		else if (b.isWin(1)) {
			return -1;
		}

		// game is over
		if (b.isEndGame())
			return 0;

		int determinant = 0;
		board rootBoard = (board) root().getElement();

		// if maximizeing
		if (b.getLastMove() == rootBoard.getLastMove()) {
			for (Node<E> child : getChildren()) {
				determinant = child.minmax();
				if (determinant > max) {
					max = determinant;
				}
			}
			return max;
		}

		// if minimizing
		for (Node<E> child : getChildren()) {
			determinant = child.minmax();
			if (determinant < min) {
				min = determinant;
			}
		}
		return min;

	}

}