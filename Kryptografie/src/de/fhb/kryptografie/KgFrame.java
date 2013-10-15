package de.fhb.kryptografie;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class KgFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public KgFrame(){
		super("Kryptografie: Cäsar und Vigenère");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		KgModel model = new KgModel();
		KgView view = new KgView(model);
		getContentPane().add(view);
		pack();
		setSize(900,450);
	}
	
	public static void main(String[] args){
		KgFrame kf = new KgFrame();
		kf.setVisible(true);
	}
}
