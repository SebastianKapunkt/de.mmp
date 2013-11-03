/** @author:
 * Eric Dobritius 	 20110009
 * Gordan Just		 20091313 
 * Sebastian Kindt 	 20120023
 * 
 * @version: 1.0
 */
package de.fhb.kryptografie;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class KgFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public KgFrame() {
		super("Cryptography: Caesar and Vigenere");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		KgModel model = new KgModel();
		KgView view = new KgView(model);
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
		KgFrame kf = new KgFrame();
		kf.setVisible(true);
	}
}
