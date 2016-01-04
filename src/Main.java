//Ari Meles-Braverman

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;


public class Main {

	public static Configure configure;
	public static GUI gui;
	
	public static void main(String[] args) {
		
		configure = new Configure();
		configure.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		configure.setSize(100,150);
		configure.setResizable(false);
		configure.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - configure.getSize().getWidth())/2, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - configure.getSize().getHeight())/3);
		configure.setVisible(true);
		configure.close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				configure.setVisible(false);
				gui.setVisible(true);
				
			}
		});
		
		gui = new GUI(configure);
		gui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		gui.setSize(150,145);
		gui.setResizable(false);
		gui.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - gui.getSize().getWidth())/2, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - gui.getSize().getHeight())/3);
		gui.addListener(new F2Listener());
		
	}
	
	private static class F2Listener implements KeyListener{
		public void keyReleased(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_F2){
				try{Thread.sleep(20);}catch(Exception ex){}
				configure.setVisible(true);
			}
		}
		public void keyPressed(KeyEvent e){}
		public void keyTyped(KeyEvent e){}
	}
	
}
