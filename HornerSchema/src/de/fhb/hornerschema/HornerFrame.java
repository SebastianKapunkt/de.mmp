package de.fhb.hornerschema;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class HornerFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public HornerFrame() {

		super("Zahlensystem-Umwandlung");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		HornerModel model = new HornerModel();
		HornerView view = new HornerView(model);
		getContentPane().add(view);

		/**
		 * Nimmt den Bildschirm größe und setzt die Startposition des Fenster
		 * auf die Mitte des Bildschirm
		 */
		Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit()
				.getScreenSize());
		setPreferredSize(new Dimension(900, 450));
		Dimension windowSize = new Dimension(getPreferredSize());
		int wdwLeft = screenSize.width / 2 - windowSize.width / 2;
		int wdwTop = screenSize.height / 2 - windowSize.height / 2;

		pack();
		setLocation(wdwLeft, wdwTop);
	}

	public static void main(String[] args) {
		HornerFrame hf = new HornerFrame();
		hf.setVisible(true);
	}
}
