package tttPD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import trees.Position;
import tttPD.SearchTree;

public class SearchTreeNode<E> {
	E element = null;
	int weight = 0;
	private SearchTreeNode<E> parent = null;
	private ArrayList<SearchTreeNode<E>> children = null;
	int size = 0;
	int depth = 0;
	int[][] lastMove;
	BoardPosition pos = new BoardPosition();
	
	public SearchTreeNode() {
		parent = this;
		children = new ArrayList<SearchTreeNode<E>>();
	}
			
	public SearchTreeNode(E element, int weight, SearchTreeNode<E> parent, ArrayList<SearchTreeNode<E>> children) {
		this.element = element;
		this.weight = weight;
		this.parent = parent;
		this.children = children;
		
		if(this.children == null) {
			this.children = new ArrayList<SearchTreeNode<E>>();
		}
	}
	
	public SearchTreeNode(E element, int weight) {
		this.element = element;
		this.weight = weight;
		children = new ArrayList<SearchTreeNode<E>>();
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public int getDepth() {
		return this.depth;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	
	public E getElemenet() {
		return this.element;
	}
	
	public void setChildren(ArrayList<SearchTreeNode<E>> children) {
		this.children = children;
	}
	
	public ArrayList<SearchTreeNode<E>> getChildren() {
		return this.children;
	}
	
	public void addChildImediate(SearchTreeNode<E> child) {
		child.setDepth(this.depth++);
		child.setParent(this);
		this.children.add(child);
	}
	
	public SearchTreeNode<E> addChild(SearchTreeNode<E> child) {
		child.setDepth(this.depth++);
		child.setParent(this);
		this.children.add(child);
		return child;
	}
	
	public void addChildren(List<SearchTreeNode<E>> children) {
		children.forEach(each-> each.setParent(this));
		this.children.addAll(children);
	}
	
	public static int minmax(int depth, int nodeIndex, boolean isMax, int scores[], int h) {
		if(depth == h) {
			return scores[nodeIndex];
		}
		
		if(isMax) {
			return Math.max(minmax(depth+1, nodeIndex*2, false, scores, h), minmax(depth+1, nodeIndex*2+1, false, scores, h));
		} else {
			return Math.min(minmax(depth+1, nodeIndex*2, true, scores, h), minmax(depth+1, nodeIndex*2+1, true, scores, h));
		}
	}
	
	public BoardPosition build(board board, int symbol, int emptySpaces) {
		if(emptySpaces == 0) {
			System.out.println("Failure");
		}
		
		BoardPosition pos = new BoardPosition();
		int x = 0, y = 0;
		for(int i = 0; i < emptySpaces; i++) {
			Random rn = new Random(System.currentTimeMillis());
			x = rn.nextInt(3);
			y = rn.nextInt(3);
			board.makeMove(symbol, x, y);
			SearchTreeNode<E> newNode = new SearchTreeNode<E>((E)board.deepClone(), 0, this, null);
			if(board.isWin(-1))
				newNode.setWeight(-1);
			else if(board.isWin(1))
				newNode.setWeight(1);
			else
				newNode.setWeight(0);
			symbol *= -1;
			
			newNode.setDepth(parent.getDepth()+1);
			pos = newNode.build(board.deepClone(), symbol, emptySpaces-1);
			System.out.println(pos.getX()+", "+pos.getY());
			this.addChild(newNode);
			printTree(this, " ");
		}
			
		if(board.isWin(-1)) {
			return pos;
		}
		return pos;
	}
	
	public void makeMove(int symbol, int row, int col) {
		if((board)element != null) {
			board B = (board)element;
			B.makeMoveClamp(symbol, row, col);
		}
	}
	
	public BoardPosition createTree(SearchTreeNode<E> node, board board, int symbol, int EmptySpaces) {
		
		//generate all children then children's sub children
		
		//offset for each in base loop below
//		if(!board.isWin(1) || !board.isWin(-1)) {
//			
//		boolean moved = false;
//		for(int i = 0; i < EmptySpaces; i++) {
//			SearchTreeNode<E> newNode = new SearchTreeNode<E>((E)board.deepClone(), 0, this, null);
//			board b = (board)newNode.getElemenet();
//			for(int n = 0; n < b.getRows() && !moved; n++) {
//				for(int s = 0; s < b.getColumns() && !moved; s++) {
//					if(s >= b.getColumns()) {
//						s = 0;
//						if(n < b.getRows())
//							n++;
//					}
//					
//					if(b.getSpaces()[n][s]==0) {
//						pos.x = n;
//						pos.y = s;
//						newNode.makeMove(symbol, n, s);	
//						moved = true;
//					}
//				}
//			}
//			
//			moved = false;
//			symbol*=-1;
//			addChild(newNode);
//		}
		//mark n distance away
		//if distance is taken search for nearist blank
		boolean moved = false;
		for(int i = 0; i < EmptySpaces; i++) { //handles each siblings
			//find place to mark
			SearchTreeNode<E> newNode = new SearchTreeNode<E>((E)board.deepClone(), 0, this, null);
			board b = (board)newNode.getElemenet();
			
			//move mark for each sibling to the right by one
			
			if(moved == false) { //if no move made fill in empty space
				
			}
			addChild(newNode);
		}
		
		
			for(SearchTreeNode<E> child : children) {
				child.createTree(child, board.deepClone(), (symbol*-1), EmptySpaces-1);
			}
//		}
		
		if(board.isEndGame()) {
			return pos;
		}
		
		
//		boolean marked = false;
//		for(int i = 0; i < board.getRows(); i++) {
//			for(int n = 0; n < board.getColumns(); n++) {
//				if(board.makeMoveClamp(symbol, i, n)) {
//					marked = true;
//					pos.x = i;
//					pos.y = n;
//				}
//				if(marked) {
//					break;
//				}
//			}
//			if(marked) {
//				break;
//			}
//		}
//		newNode.setBoard(board);
//		if(symbol < 0) {
//			newNode.setWeight(-1);
//		} else if (symbol > 0) {
//			newNode.setWeight(1);
//		} else {
//			newNode.setWeight(0);
//		}
//		addChild(newNode);
//		
//		
//		System.out.println(newNode.toString());
//		if(!board.isEndGame()) {
//			symbol *= -1;
//			newNode.createTree(newNode, board.deepClone(), symbol, EmptySpaces-1);
//		}
		
		
		return pos;
				
	}
	
	public void setParent(SearchTreeNode<E> parent) {
		this.parent = parent;
	}
	
	public SearchTreeNode<E> getParent() {
		return this.parent;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int setWeight(int weight) {
		this.weight = weight;
		return weight;
	}
	
	public Iterable<SearchTreeNode<E>> children(SearchTreeNode<E> p) throws IllegalArgumentException {
		return null;
	}
	
	public int numChildren(SearchTreeNode<E> p) throws IllegalArgumentException {
		return children.size();
	//	return size;
	}

	
	public boolean isInternal(SearchTreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return !children.isEmpty();
	}

	
	public boolean isExternal(SearchTreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return children.isEmpty();
	}

	
	public boolean isRoot(SearchTreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return (parent==null);
	}
	
	public SearchTreeNode<E> getRoot() {
		if(parent == null) {
			return this;
		}
		return parent.getRoot();
	}

	
	public int size() {
		return numChildren(null);
	}

	
	public boolean isEmpty() {
		return (size()==0);
	}

	
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		//return this.children.iterator();
		return null;
	}

	
	public Iterable<SearchTreeNode<E>> childNodes() {
		// TODO Auto-generated method stub
//		Iterator<E> it = children.iterator();
//		ArrayList<SearchTreeNode<E>> list = new ArrayList<SearchTreeNode<E>>();
//		while(it.hasNext()) {
//			list.add((SearchTreeNode<E>) it.next());
//		}
//			
//		return list;
		return null;
	}
	
	public static <E> void printTree(SearchTreeNode<E> node, String appender) {
		System.out.println(appender + node.getElemenet());
		node.getChildren().forEach(each-> printTree(each, appender + appender));
	}
	
	public String toString() {
		if(element != null)
			return "Elem-> "+element.toString()+" Weight-> "+weight+" depth-> "+depth;
		return "weight-> "+weight+" depth-> "+depth;
	}	
}