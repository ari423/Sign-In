//Ari Meles-Braverman

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.awt.Font;
import javax.swing.Timer;

public class GUI extends JFrame {
	
	private JPanel panel;
	private JLabel name;
	private JTextField code;
	private JButton go;
	
	private File file, keyFile;
	private Scanner reader;
	private PrintWriter writer;
	private int numcommas;
	
	private Member[] key;
	private ArrayList<String> attendance;
	private String header;
	
	private Configure configure;
	
	private Timer timer;
	
	public GUI(Configure configure){
		super();
		
		this.configure = configure;
		
		file = new File("Files/attendance.csv");
		keyFile = new File("Files/key.csv");
		
		attendance = new ArrayList<String>();
		try{
			reader = new Scanner(file);
			while(reader.hasNext()){
				attendance.add(reader.nextLine());
			}
			if(attendance.size() == 0)
				attendance.add(" ");
			numcommas = attendance.get(0).split(",").length;
			header = attendance.remove(0);
			header += "," + (configure.startDate.get(Calendar.MONTH) +1) + "/" + configure.startDate.get(Calendar.DATE);
		}catch(Exception e){
			e.printStackTrace();
			numcommas = 0;
		}
		
		try{
			reader = new Scanner(keyFile);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		key = readKey(reader);
		
		panel = new JPanel();
		this.add(panel);
		
		name = new JLabel("                     ");
		panel.add(name);
		
		code = new JTextField();
		panel.add(code);
		code.setColumns(5);
		code.setFont(new Font(null, Font.PLAIN, 20));
		code.setAlignmentX(JTextField.CENTER_ALIGNMENT);
		code.setAlignmentY(JTextField.CENTER_ALIGNMENT);
		code.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					writeCode();
			}
		});
		
		go = new JButton("GO");
		panel.add(go);
		go.setFont(new Font(null, Font.PLAIN, 30));
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				writeCode();
			}
		});
		
		KeyListener listen = new F4Listener();
		this.addKeyListener(listen);
		panel.addKeyListener(listen);
		name.addKeyListener(listen);
		code.addKeyListener(listen);
		go.addKeyListener(listen);
		
		this.addWindowListener(new WindowListener(){
			public void windowOpened(WindowEvent e){}
			public void windowClosed(WindowEvent e){}
			public void windowClosing(WindowEvent e){
				int ans = JOptionPane.showOptionDialog(panel, "Are you sure you want to exit without saving?", "Save?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Save", "Don't save", "Cancel"}, "Save");
				switch(ans){
					case 0:
						exit();
						break;
					case 1:
						System.exit(0);
						break;
					default:
						break;
				}
			}
			public void windowDeactivated(WindowEvent e){}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
		});
		
	}
	
	public void writeCode(){
		String codestr = code.getText();
		code.setText("");
		String namestr = getName(codestr);
		
		if(namestr == null){
			setNameText("Not In System");
		}else{
			for(int i=0; i<attendance.size(); i++){
				if(attendance.get(i).contains(namestr)){
					if(attendance.get(i).split(",").length -1 < numcommas){
						attendance.set(i, attendance.get(i) + "," + getTime());
						setNameText(namestr);
						
					}else{
						String[] line = attendance.get(i).split(",");
						line[line.length -1] = (Double.parseDouble(line[line.length -1]) - getTime()) + "";
						String tmp = line[0];
						for(int j=1; j<line.length; j++){
							tmp += "," + line[j];
						}
						attendance.set(attendance.size()-1, tmp);
						setNameText("* " + namestr);
					}
					return;
				}
			}
			attendance.add(namestr);
			for(int i=1; i<numcommas; i++){
				attendance.set(attendance.size()-1, attendance.get(attendance.size()-1) + ",0");
			}
			attendance.set(attendance.size()-1, attendance.get(attendance.size()-1) + "," + getTime());
		}
	}
	
	public String getName(String a){
		for(int i=0; i<key.length; i++){
			if(key[i].check(a)){
				return key[i].name;
			}
		}
		return null;
	}
	
	public double getTime(){
		long start = configure.startDate.getTimeInMillis();
		long end = configure.endDate.getTimeInMillis();
		long current = Calendar.getInstance().getTimeInMillis();
		
		if(Math.abs(current-start)<3600000L){
			current = start;
		}
		return ((double)(Math.round((end - current)/900000L)))/4.;
	}
	
	public Member[] readKey(Scanner reader){
		ArrayList<String> tmp = new ArrayList<String>();
		while(reader.hasNext()){
			tmp.add(reader.nextLine());
		}
		
		Member[] array = new Member[tmp.size()];
		for(int i=0; i<tmp.size(); i++){
			array[i] = new Member(tmp.get(i).split(",")[1], tmp.get(i).split(",")[0]);
		}
		
		return array;
	}
	
	private void setNameText(String str){
		if(timer != null) timer = null;
		name.setText(str);
		timer = new Timer(2000, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				name.setText("                     ");
				timer = null;
			}
		});
		timer.start();
	}
	
	public void addListener(KeyListener key){
		this.addKeyListener(key);
		panel.addKeyListener(key);
		name.addKeyListener(key);
		code.addKeyListener(key);
		go.addKeyListener(key);
	}
	
	public void exit(){
		for(int i=0; i<attendance.size(); i++){
			if(attendance.get(i).split(",").length -1 < numcommas){
				attendance.set(i, attendance.get(i) + ",0");
			}
		}
		
		try{
			writer = new PrintWriter(file);
			writer.println(header);
			for(int i=0; i<attendance.size(); i++){
				writer.println(attendance.get(i));
			}
			writer.close();
			System.exit(0);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private class F4Listener implements KeyListener{
		public void keyReleased(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_F4){
				try{Thread.sleep(20);}catch(Exception ex){}
				exit();
			}
		}
		public void keyTyped(KeyEvent e){}
		public void keyPressed(KeyEvent e){}
	}
	
}
