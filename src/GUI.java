//Ari Meles-Braverman

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {
	
	public JPanel panel;
	
	public JTextField code_field;
	
	public GUI(){
		super();
		
		panel = new JPanel();
		this.add(panel);
		
		code_field = new JTextField();
	}
	
	
	
}
