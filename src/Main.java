import java.awt.EventQueue;

import javax.swing.text.Position;

import tttPD.SearchTree;
import tttPD.SearchTreeNode;
import tttPD.board;
import tttPD.gameManager;
import tttUI.mainFrame;

// Entry point for the program
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame window = new mainFrame();
					window.setVisible(true);
					window.getGame().setFrame(window);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		window.main(args);
		
//		gameManager game = new gameManager();
//		SearchTreeNode<Integer> root = new SearchTreeNode<Integer>(new Integer(4), 0, game.getBoard().deepClone(), null, null);
//		root.addChild(new SearchTreeNode<Integer>(new Integer(5), 0, game.getBoard().deepClone(), null, null));
//		SearchTree<Integer> tree = new SearchTree<Integer>(root);
//		System.out.println(tree.root().getChildren().get(0).getBoard().isEndGame());
		
//		game.runGameCLI();
//		
	}

}
