package tttPD;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import javax.naming.directory.SearchResult;

import trees.Tree;
import trees.Position;
import tttPD.SearchTreeNode;

public class SearchTree<E> {
	private SearchTree<E> parent = null;
	private SearchTreeNode<E> root = null;
		
	public SearchTree(SearchTreeNode<E> root ) {
		this.root = root;
	}

	public SearchTreeNode<E> root() {
		// TODO Auto-generated method stub
		return root;
	}

	public SearchTree<E> parent() {
		// TODO Auto-generated method stub
		return parent;
	}

	public Iterable<E> children(SearchTreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public int numChildren(SearchTreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return root.numChildren(p);
	}

	
	public boolean isInternal(SearchTreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return root.isInternal(p);
	}

	
	public boolean isExternal(SearchTreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return root.isExternal(p);
	}

	
	public boolean isRoot(SearchTreeNode<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return (p==root);
	}

	
	public int size() {
		// TODO Auto-generated method stub
		if(root !=null) {
			return root.numChildren(null)+1;			
		}
		return 0;
	}

	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (root==null);
	}

	
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setRoot(SearchTreeNode<E> root) {
		this.root = root;
	}
	
	
	public BoardPosition build(board board, int symbol, int emptySpaces) {
		return root.build(board, symbol, emptySpaces);
	}
	
	public void addChildToNode(SearchTreeNode<E> child, SearchTreeNode<E> node) {
		//node.addChild(child);
		for(SearchTreeNode<E> ch : root.getChildren()) {
			if(ch == node) {
				child.setParent(node);
				ch.addChild(child);
			}
		}
	}
	
	public void printChildren() {
		StringBuilder builder = new StringBuilder();
		for(SearchTreeNode<E> child : root.getChildren()) {
			if(child.getElemenet() != null) 
				builder.append(child.getElemenet().toString()+" <---> ");
		}
		builder.append("\n");
		System.out.println(builder.toString());
	}
	
	public void addChildToRoot(SearchTreeNode<E> node) {
		node.setParent(root);
		root.addChild(node);
	}

	
	public Iterable<SearchTreeNode<E>> nodes() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
