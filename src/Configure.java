//Ari Meles-Braverman

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class Configure extends JFrame{

	public JPanel panel;
	public DateTime start, end;
	
	public Configure(){
		super();
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		this.add(panel);
		
		
		
	}
	
}
