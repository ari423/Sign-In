//Ari Meles-Braverman

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.*;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.awt.Font;

public class GUI extends JFrame {
	
	public JPanel panel;
	public JLabel name;
	public JTextField code;
	public JButton go, close;
	
	public File file;
	public Scanner reader;
	public PrintWriter writer;
	
	public Member[] key;
	public ArrayList<String> list = new ArrayList<String>();
	
	public Configure configure;	
	
	public GUI(Configure configure){
		super();
		
		this.configure = configure;
		
		file = new File("Files/" + (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()) + ".txt");
		
		try{
			reader = new Scanner(file);
			while(reader.hasNext()){
				list.add(reader.nextLine());
			}
			
		}catch(Exception e){
			//e.printStackTrace();
		}
		
		try{
			writer = new PrintWriter(file);
			reader = new Scanner(new File("Files/Key.txt"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for(int i=0; i<list.size(); i++){
			writer.println(list.get(i));
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
		
		close = new JButton("Close");
		panel.add(close);
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				writer.close();
				System.exit(0);
			}
		});
	}
	
	public void writeCode(){
		try{
			String codetxt = code.getText();
			code.setText("");
			for(int i=0; i<key.length; i++){
				if(key[i].check(codetxt)){
					if(list.contains(key[i].name)){
						name.setText("Already Signed-In");
						break;
					}else{
						writer.println(key[i].name + "    " + timeLeft());
						name.setText(key[i].name);
						list.add(key[i].name);
						break;
					}
				}
				name.setText("Not In System");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Member[] readKey(Scanner reader){
		ArrayList<String> tmp = new ArrayList<String>();
		while(reader.hasNext()){
			tmp.add(reader.nextLine());
		}
		
		Member[] array = new Member[tmp.size()];
		for(int i=0; i<tmp.size(); i++){
			array[i] = new Member(tmp.get(i).split("\\t")[1], tmp.get(i).split("\\t")[0]);
		}
		
		return array;
	}
	
	public double timeLeft(){
		long start = configure.startDate.getTime();
		long current = (new Date()).getTime();
		long end = configure.endDate.getTime();
		System.out.println(start);
		System.out.println(current);
		System.out.println(end);
		if(Math.abs(start-current)<=600000L){
			System.out.println("close");
			return (end-start)/600000L;
		}else{
			System.out.println("not");
			return (end-current)/600000L;
		}
	}
	
}
