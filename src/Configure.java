//Ari Meles-Braverman

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;


public class Configure extends JFrame{

	private JPanel panel;
	private JSpinner start, end;
	private JLabel startl, breakl, endl;
	public JButton close;
	
	public Calendar startDate, endDate;
	
	public Configure(GUI gui){
		super();
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		this.add(panel);
		
		startl = new JLabel("START TIME");
		panel.add(startl);
		
		startDate = Calendar.getInstance();
		start = new JSpinner(new SpinnerDateModel(startDate.getTime(), null, null,Calendar.HOUR_OF_DAY));
		start.setEditor(new JSpinner.DateEditor(start, "HH:mm"));
		panel.add(start);
		
		breakl = new JLabel("    ");
		panel.add(breakl);
		
		endl = new JLabel("END TIME");
		panel.add(endl);
		
		endDate = Calendar.getInstance();
		endDate.set(Calendar.HOUR_OF_DAY, startDate.get(Calendar.HOUR_OF_DAY) + 2);
		end = new JSpinner(new SpinnerDateModel(endDate.getTime(), null, null,Calendar.HOUR_OF_DAY));
		end.setEditor(new JSpinner.DateEditor(end, "HH:mm"));
		panel.add(end);
		
		close = new JButton("BEGIN");
		panel.add(close);
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startDate.set(Calendar.HOUR_OF_DAY, ((Date) start.getValue()).getHours());
				startDate.set(Calendar.MINUTE, ((Date) start.getValue()).getMinutes());
				endDate.set(Calendar.HOUR_OF_DAY, ((Date) end.getValue()).getHours());
				endDate.set(Calendar.MINUTE, ((Date) end.getValue()).getMinutes());
			}
		});
		
	}
	
	public void addListener(KeyListener key){
		panel.addKeyListener(key);
		start.addKeyListener(key);
		end.addKeyListener(key);
		startl.addKeyListener(key);
		breakl.addKeyListener(key);
		endl.addKeyListener(key);
		close.addKeyListener(key);
	}
	
}
