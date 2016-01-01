//Ari Meles-Braverman

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Main {

	public static Configure configure;
	public static GUI gui;
	
	public static void main(String[] args) {
		
		configure = new Configure();
		configure.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		configure.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - configure.getSize().getWidth())/2, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - configure.getSize().getHeight())/3);
		configure.setVisible(true);
		configure.close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				configure.setVisible(false);
				gui = new GUI(configure);
				gui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				gui.setSize(150,175);
				gui.setResizable(false);
				gui.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - gui.getSize().getWidth())/2, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - gui.getSize().getHeight())/3);
				gui.setVisible(true);
			}
		});
		
	}
}
