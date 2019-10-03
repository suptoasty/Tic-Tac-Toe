package tttUI;

import javax.swing.JPanel;

import tttPD.BoardPosition;
import tttPD.Node;
import tttPD.ai;
import tttPD.gameManager;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;
import tttPD.board;
import javax.swing.JLabel;

public class gamePanel extends JPanel {
	private gameManager game = null;
	private board board = null;
	private JButton[][] spaces = null;
	private ai ai = null;
	private HashMap<JButton, Integer> buttonX = new HashMap<JButton, Integer>();
	private HashMap<JButton, Integer> buttonY = new HashMap<JButton, Integer>();
	private boolean turn = false;
	JLabel label = new JLabel("");
	//can create collection of buttons and find button with gridx,y value to set symbol for player move
	/**
	 * Create the panel.
	 */
	public gamePanel(gameManager game, ai ai) {
		label.setText("");
		this.game = game;
		this.ai = ai;
		this.board = game.getBoard();
		spaces = new JButton[3][3];	
		makeMove mv = new makeMove(buttonX, buttonY);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{108, 167, 162, 0};
		gridBagLayout.rowHeights = new int[]{84, 58, 90, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnNewButton = new JButton(" ");	//value
		btnNewButton.addMouseListener(mv);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0; //row
		gbc_btnNewButton.gridy = 0; //column
		add(btnNewButton, gbc_btnNewButton);
		spaces[0][0] = btnNewButton;
		buttonX.put(btnNewButton, new Integer(0));
		buttonY.put(btnNewButton, new Integer(0));
		
		JButton btnNewButton_3 = new JButton(" ");
		btnNewButton_3.addMouseListener(mv);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 0;
		add(btnNewButton_3, gbc_btnNewButton_3);
		spaces[0][1] = btnNewButton_3;
		buttonX.put(btnNewButton_3, new Integer(0));
		buttonY.put(btnNewButton_3, new Integer(1));
		
		JButton btnNewButton_4 = new JButton(" ");
		btnNewButton_4.addMouseListener(mv);
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 2;
		gbc_btnNewButton_4.gridy = 0;
		add(btnNewButton_4, gbc_btnNewButton_4);
		spaces[0][2] = btnNewButton_4;
		buttonX.put(btnNewButton_4, new Integer(0));
		buttonY.put(btnNewButton_4, new Integer(2));
		
		JButton btnNewButton_2 = new JButton(" ");
		btnNewButton_2.addMouseListener(mv);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 1;
		add(btnNewButton_2, gbc_btnNewButton_2);
		spaces[1][0] = btnNewButton_2;
		buttonX.put(btnNewButton_2, new Integer(1));
		buttonY.put(btnNewButton_2, new Integer(0));
		
		JButton btnNewButton_5 = new JButton(" ");
		btnNewButton_5.addMouseListener(mv);
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 1;
		gbc_btnNewButton_5.gridy = 1;
		add(btnNewButton_5, gbc_btnNewButton_5);
		spaces[1][1] = btnNewButton_5;
		buttonX.put(btnNewButton_5, new Integer(1));
		buttonY.put(btnNewButton_5, new Integer(1));
		
		JButton btnNewButton_8 = new JButton(" ");
		btnNewButton_8.addMouseListener(mv);
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_8.gridx = 2;
		gbc_btnNewButton_8.gridy = 1;
		add(btnNewButton_8, gbc_btnNewButton_8);
		spaces[1][2] = btnNewButton_8;
		buttonX.put(btnNewButton_8, new Integer(1));
		buttonY.put(btnNewButton_8, new Integer(2));
		
		JButton btnNewButton_1 = new JButton(" ");
		btnNewButton_1.addMouseListener(mv);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		add(btnNewButton_1, gbc_btnNewButton_1);
		spaces[2][0] = btnNewButton_1;
		buttonX.put(btnNewButton_1, new Integer(2));
		buttonY.put(btnNewButton_1, new Integer(0));
		
		JButton btnNewButton_6 = new JButton(" ");
		btnNewButton_6.addMouseListener(mv);
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 1;
		gbc_btnNewButton_6.gridy = 2;
		add(btnNewButton_6, gbc_btnNewButton_6);
		spaces[2][1] = btnNewButton_6;
		buttonX.put(btnNewButton_6, new Integer(2));
		buttonY.put(btnNewButton_6, new Integer(1));
		
		JButton btnNewButton_7 = new JButton(" ");
		btnNewButton_7.addMouseListener(mv);
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_7.gridx = 2;
		gbc_btnNewButton_7.gridy = 2;
		add(btnNewButton_7, gbc_btnNewButton_7);
		spaces[2][2] =  btnNewButton_7;
		buttonX.put(btnNewButton_7, new Integer(2));
		buttonY.put(btnNewButton_7, new Integer(2));
		
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 3;
		add(label, gbc_label);
		
//		JButton btnPlayagain = new JButton("PlayAgain?");
//		btnPlayagain.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				board = new board();
//				label.setText("");
//			}
//		});
//		GridBagConstraints gbc_btnPlayagain = new GridBagConstraints();
//		gbc_btnPlayagain.insets = new Insets(0, 0, 0, 5);
//		gbc_btnPlayagain.gridx = 1;
//		gbc_btnPlayagain.gridy = 5;
//		add(btnPlayagain, gbc_btnPlayagain);
		
		mv = new makeMove(buttonX, buttonY);
	}
	
	public void run() {
		boolean end = false;
		boolean endTurn = false;
		int r = -1, c = -1;
		while(!board.isEndGame()) {
			while(!endTurn) {
				endTurn = turn;
			}
			endTurn = false;
			turn = endTurn;
			if(r != -1 || c != -1)
				if(c != -1 || r != -1) 
					spaces[r][c].setText(Integer.toString(1));
			
			if(board.isEndGame()) {
				break;
			}
			
			//ai move here?
			Random rand = new Random(3);
			game.getAi().makeMove(rand.nextInt(3), rand.nextInt(3));
			game.getBoard().printBoard();
			System.out.println("");
		}
	}
	
	public static int clamp(int val, int min, int max) {
		max--;
		return Math.max(min, Math.min(max, val));
	}
	
	class makeMove implements MouseListener {
		private HashMap<JButton, Integer> x = null;
		private HashMap<JButton, Integer> y = null;
		
		public makeMove(HashMap<JButton, Integer> x, HashMap<JButton, Integer> y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(!board.isEndGame()) {
				//Player
				JButton btn = (JButton)arg0.getSource();
				board.makeMoveClamp(1, x.get(btn), y.get(btn));
				board.printBoard();
				btn.setText("X");
				btn.setEnabled(false);
				btn.removeMouseListener(this);

				//AI
				BoardPosition mov = ai.updateBoard(board.deepClone()); //clone board for ai and get next best move
				board.makeMoveClamp(-1, mov.getX(), mov.getY()); //make that move on the board
				
				//reparent tree
				for(Node<board> child : ai.getRoot().getChildren()) {
					board b2 = (board)child.getElement();
					if(b2.equals(b2.getSpaces(), board.getSpaces())) {
						//System.out.println("New root Found");
						ai.setRoot(child);
					}
				}	
				
				//disable ai button "pushed"
				JButton btn2 = spaces[mov.getX()][mov.getY()];
				btn2.setText("O");
				btn2.setEnabled(false);
				spaces[mov.getX()][mov.getY()] = btn2;
				btn2.removeMouseListener(this);
				
				
			} else {
				if(board.getLastMove() == 1) {
					label.setText("Player Won");
					//System.out.println("PLayer won");
				} else if (board.getLastMove() == -1) {
					label.setText("Computer Won");
					//System.out.println("Ai won");
				}
			}
//			System.out.println("Ah Yee! - "+btn);
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
