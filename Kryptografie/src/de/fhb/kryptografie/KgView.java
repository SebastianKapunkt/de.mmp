package de.fhb.kryptografie;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class KgView extends JPanel implements ActionListener, Observer{
	
	KgModel model;
	
	JButton encipher = new JButton("encipher");
	JButton decipher = new JButton("decipher");
	
	JButton cleark = new JButton("clear");
	JButton clearc = new JButton("clear");
	
	JTextArea plainText = new JTextArea("\"plaine text\" ",15,20);
	JTextArea cipherText = new JTextArea("\"cipher text\" ",15,20);
	
	JScrollPane plainscroll = new JScrollPane (plainText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	JScrollPane cipherscroll = new JScrollPane (cipherText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	
	
	JTextField key = new JTextField("",5);
	
	public KgView(KgModel model) {
		this.model = model;
		model.addObserver(this);
		setBackground(Color.lightGray);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(20,20,20,50));
			Box boxH = Box.createHorizontalBox();
			JLabel label1 = new JLabel(" plain text");
			boxH.add(label1);
			boxH.add(Box.createHorizontalStrut(120));
			boxH.add(encipher);
			box.add(boxH);
		box.add(Box.createVerticalStrut(5));
		box.add(plainscroll);
		box.add(Box.createVerticalStrut(5));
		box.add(clearc);
		add(box);
		
		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 50));
			Box box2H = Box.createHorizontalBox();
			JLabel label2 = new JLabel(" cipher text");
			box2H.add(label2);
			box2H.add(Box.createHorizontalStrut(120));
			box2H.add(decipher);
			box2.add(box2H);
		box2.add(Box.createVerticalStrut(5));
		box2.add(cipherscroll);
		box2.add(Box.createVerticalStrut(5));
		box2.add(cleark);
		add(box2);
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
