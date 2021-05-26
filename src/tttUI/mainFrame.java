package tttUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tttPD.gameManager;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	private gameManager game = new gameManager();
	private gamePanel panel = null;

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
