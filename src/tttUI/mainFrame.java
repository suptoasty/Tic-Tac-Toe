package tttUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tttPD.gameManager;
import tttUI.gamePanel;

public class mainFrame extends JFrame {

	private JButton[][] spaces;
	private JPanel contentPane;
	private gameManager game = new gameManager();
	private gamePanel panel = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					mainFrame frame = new mainFrame();
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		panel = new gamePanel(game, game.getAi());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(panel);
	}
	public gamePanel getGamePanel() {
		return panel;
	}
	
	public gameManager getGame() {
		return game;
	}

}
