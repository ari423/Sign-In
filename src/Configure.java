//Ari Meles-Braverman

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;


public class Configure extends JFrame{

	public JPanel panel;
	public JSpinner start, end;
	public JLabel startl, breakl, endl;
	public JButton close;
	
	public Date startDate, endDate;
	
	public Configure(){
		super();
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		this.add(panel);
		
		startl = new JLabel("START TIME");
		panel.add(startl);
		
		breakl = new JLabel("    ");
		panel.add(breakl);
		
		startDate = new Date();
		start = new JSpinner(new SpinnerDateModel(startDate, null, null,Calendar.HOUR_OF_DAY));
		start.setEditor(new JSpinner.DateEditor(start, "hh:mm"));
		panel.add(start);
		
		endl = new JLabel("END TIME");
		panel.add(endl);
		
		endDate = new Date();
		endDate.setHours(startDate.getHours() + 2);
		end = new JSpinner(new SpinnerDateModel(endDate, null, null,Calendar.HOUR_OF_DAY));
		end.setEditor(new JSpinner.DateEditor(end, "hh:mm"));
		panel.add(end);
		
		close = new JButton("CLOSE");
		panel.add(close);
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startDate = (Date) start.getValue();
				endDate = (Date) end.getValue();
			}
		});
		
	}
	
}
