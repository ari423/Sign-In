//Ari Meles-Braverman

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Main {

	public static void main(String[] args) {
		
		/*GUI gui = new GUI();
		gui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		gui.setSize(150,175);
		gui.setResizable(false);
		gui.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - gui.getSize().getWidth())/2, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - gui.getSize().getHeight())/3);
		gui.setVisible(false);*/
		
		Configure configure = new Configure();
		configure.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		configure.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - configure.getSize().getWidth())/2, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - configure.getSize().getHeight())/3);
		configure.setVisible(true);
		
	}

}
