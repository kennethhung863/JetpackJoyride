import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Jetpack Joyride");
		
		GameObject game = new GameObject();
		
		//make everything drawable under 1 superclass, then extend that to jpanel

	    frame.add(game);
		
        frame.pack();

		frame.setSize(1200, 685);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		//refreshes frame
		//while (true) {
			game.paint(null);
			//refresh gap
			Thread.sleep(12);
		//}
		
	}

}
