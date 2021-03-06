package arkanoid;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class Main 
{
	// https://niklasf.github.io/chessops/modules.html
	static Logger logger = Logger.getLogger("Main");
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		
		frame.add(new Game());
		
		frame.setTitle("Arkonoid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		logger.log(Level.INFO, "Program has been started.");
	}

}