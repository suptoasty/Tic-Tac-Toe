import java.awt.EventQueue;
import tttUI.mainFrame;

// Entry point for the program
public class Main {

	public static void main(String[] args) {
		// Entrypoint for App under Swing UI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Initialize window and game
					mainFrame window = new mainFrame();
					window.setVisible(true);
					window.getGame().setFrame(window);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
