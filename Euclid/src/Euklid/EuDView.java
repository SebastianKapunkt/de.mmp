package Euklid;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class EuDView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;

	EuDModel model;
	
	JButton compute = new JButton("Berechnen");

	JTextField a = new JTextField("",10);
	JTextField b = new JTextField("",10);
	JTextField g = new JTextField("",6);
	JOptionPane error = new JOptionPane();

	public EuDView(EuDModel model)  {
		this.model = model;
		model.addObserver(this);
		setBackground(Color.lightGray);
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 50));
		JLabel label1 = new JLabel("  a");
		label1.setAlignmentY(TOP_ALIGNMENT);
		box.add(label1);
		box.add(Box.createVerticalStrut(5));
		a.setAlignmentX(LEFT_ALIGNMENT);
		box.add(a);
		box.add(Box.createVerticalStrut(20));
		box.add(new JLabel("  b"));
		box.add(Box.createVerticalStrut(5));
		b.setAlignmentX(LEFT_ALIGNMENT);
		box.add(b);
		box.add(Box.createVerticalStrut(15));
		compute.addActionListener(this);
		compute.setAlignmentX(LEFT_ALIGNMENT);
		box.add(compute);
		add(box);		
		
		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		box2.add(new JLabel("  ggt"));		
		box2.add(Box.createVerticalStrut(5));
		g.setAlignmentX(LEFT_ALIGNMENT);
		g.setEditable(false);
		box2.add(g);
		add(box2);
		
	}

	private void readInput() throws NegativeNumberException{
		try {
			if(Integer.valueOf(a.getText()) < 0 || Integer.valueOf(b.getText()) < 0){
				throw new NegativeNumberException();
			}
			model.setA(Integer.valueOf(a.getText()));
			model.setB(Integer.valueOf(b.getText()));
			try{
				model.ggt();
			}catch (ArithmeticException e){
				JOptionPane.showMessageDialog(this,"ggt(0,0) ist undefiniert","Mathematisches Problem",JOptionPane.ERROR_MESSAGE);
				model.zw = 0;
				a.setText("");
				b.setText("");
				g.setText("");
			}
		}catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,"Falsches Zahlenformat","Eingabefehler",JOptionPane.ERROR_MESSAGE);
			a.setText("");
			b.setText("");
			g.setText("");
		} 
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == compute){
			try {
				readInput();
			} catch (NegativeNumberException e1) {
				JOptionPane.showMessageDialog(this,"Negative Zahl(en)","Eingabefehler",JOptionPane.ERROR_MESSAGE);
				a.setText("");
				b.setText("");
				g.setText("");
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		g.setText(model.getGgt()+"");
	}
}



