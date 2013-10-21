/** @author:
 * Eric Dobritius 	 20110009
 * Gordan Just		 20091313 
 * Sebastian Kindt 	 20120023
 * 
 * @version: 1.0
 */


package Euklid;

import java.awt.event.*;

import javax.swing.*;

/**
 * Stellt den Frame für das GUI bereit,
 * instanziert EuDModel(Berechnung des ggT) und EuDView (GUI)
 *
 */
public class EuDFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EuDFrame(){
		super("Euklid'scher Algorithmus");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		EuDModel model = new EuDModel();
		EuDView view = new EuDView(model);
		getContentPane().add(view);
		setSize(500,200);
		pack();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)  {
		EuDFrame ef = new EuDFrame();
		ef.setVisible(true);
	}


}
