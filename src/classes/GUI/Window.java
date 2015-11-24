package classes.GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.xml.ws.handler.MessageContext.Scope;

import actions.display.Displayer;
import actions.toolsets.Toolbox;
import application.main.Application;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;

@SuppressWarnings("serial")
public class Window extends JFrame {

	JButton Refresh;
	JLabel L;
	JTextField text;
	Container contentPane;
ArrayList<Label> labels;
public Window (){
	
}
	public Window(final TreeMap<String, String> refresh) {
		this.setResizable(false);
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		labels = new ArrayList<Label>();
		L = new JLabel(new ImageIcon("dreams.jpg"));
		this.setContentPane(L);
		contentPane = this.getContentPane();

		FlowLayout b = new FlowLayout(FlowLayout.CENTER, 500, 30);
		contentPane.setLayout(b);
		text = new javax.swing.JTextField("File path", 70);
		text.setAutoscrolls(true);
		text.addActionListener(new ActionListener() {
			String Path = "";
			
			public void actionPerformed(ActionEvent arg0) {
				Path += text.getText();
			}
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	
		Refresh = new JButton();
		Refresh.setIcon(new ImageIcon("repeat.png"));
		Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
				//calling display that should return a hash table
				//containing values of the current state
		    	//Refresh(display());
				try {
					Application.tick();
					Refresh(refresh);
				} catch (FileNotFoundException e) {
					Refresh.disable();
				}
				
			} } 
				);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		Refresh.setBorder(emptyBorder);
		Refresh.setContentAreaFilled(false);
		Refresh.setVisible(true);
		contentPane.add(Refresh);
		this.setSize(1000, 1000);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
public void Refresh(final TreeMap<String, String> refresh){
	JLabel l;
	for (String x : refresh.keySet()) {
		l =new JLabel(x+"  "+refresh.get(x));
		l.setVisible(true);
		l.setOpaque(true);
		l.setLocation(100, 100);;
		contentPane.add(l);
		l.setFont(new Font("Courier New", Font.CENTER_BASELINE, 20));
		l.setForeground(new Color(0x00FFFF));
	}
		

}
	
//	public static void main(String[]args) {
//		
//		new Window(Displayer.display(it));
//	}
}
