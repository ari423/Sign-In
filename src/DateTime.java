//Ari Meles-Braverman

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTime extends JPanel{

	public Calendar date;
	
	public JPanel monthp, dayp, hourp, minp, amp;
	public JSpinner month, day, hour, min;
	public JButton am;
	public boolean amv;
	public JLabel monthl, dayl, hourl, minl, aml;
	
	public DateTime(){
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		date = GregorianCalendar.getInstance();
		
		monthp = new JPanel();
		monthp.setLayout(new BoxLayout(monthp, BoxLayout.PAGE_AXIS));
		this.add(monthp);
		dayp = new JPanel();
		dayp.setLayout(new BoxLayout(dayp, BoxLayout.PAGE_AXIS));
		this.add(dayp);
		hourp = new JPanel();
		hourp.setLayout(new BoxLayout(dayp, BoxLayout.PAGE_AXIS));
		this.add(hourp);
		minp = new JPanel();
		minp.setLayout(new BoxLayout(minp, BoxLayout.PAGE_AXIS));
		this.add(minp);
		amp = new JPanel();
		amp.setLayout(new BoxLayout(amp, BoxLayout.PAGE_AXIS));
		this.add(amp);
		
		monthl = new JLabel("MONTH");
		monthp.add(monthl);
		dayl = new JLabel("DAY");
		dayp.add(dayl);
		hourl = new JLabel("HOUR");
		hourp.add(hourl);
		minl = new JLabel("MINUTE");
		minp.add(minl);
		aml = new JLabel("     ");
		amp.add(aml);
		
		month = new JSpinner(new SpinnerNumberModel(date.get(Calendar.MONTH), 1, 12, 1));
		day = new JSpinner(new SpinnerNumberModel(date.get(Calendar.DATE), 1, date.getActualMaximum(Calendar.DAY_OF_MONTH), 1));
		hour = new JSpinner(new SpinnerNumberModel(date.get(Calendar.HOUR), 1, 12, 1));
		min = new JSpinner(new SpinnerNumberModel(date.get(Calendar.MINUTE), 0, 60, 1));
		am = new JButton((date.get(Calendar.AM_PM) == Calendar.AM) ? "AM" : "PM");
		amv = (date.get(Calendar.AM_PM) == Calendar.AM);
		
		monthp.add(month);
		dayp.add(day);
		hourp.add(hour);
		minp.add(min);
		amp.add(am);
		
		month.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				date.set(Calendar.MONTH, (int)(month.getValue()));
				day = new JSpinner(new SpinnerNumberModel(date.get(Calendar.DATE), 1, date.getActualMaximum(Calendar.DAY_OF_MONTH), 1));
			}
		});
		day.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				date.set(Calendar.DATE, (int)(day.getValue()));
			}
		});
		hour.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				date.set(Calendar.HOUR, (int)(hour.getValue()) + (amv ? 0 : 12));
			}
		});
		min.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				date.set(Calendar.MINUTE, (int)(min.getValue()));
			}
		});
		am.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				date.set(Calendar.HOUR, (int)(hour.getValue()) + (amv ? 0 : 12));
				am.setText(amv ? "AM" : "PM");
			}
		});
	}
	
}
