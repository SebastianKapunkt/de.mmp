package Euklid;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import Euklid.Exception.NegativeNumberException;

public class EuDView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;

	EuDModel model;
	
	JButton compute = new JButton("Berechnen");

	JTextField a = new JTextField("",10);
	JTextField b = new JTextField("",10);
	JTextField g = new JTextField("",6);
	JTextField x = new JTextField("",6);
	JTextField y = new JTextField("",6);

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
		
		Box box3 = Box.createVerticalBox();
		box3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		JLabel label2 = new JLabel("Bézout-Koeffizienten");
		label2.setAlignmentY(TOP_ALIGNMENT);
		box3.add(label2);		
		box3.add(Box.createVerticalStrut(5));
		x.setAlignmentX(LEFT_ALIGNMENT);
		x.setEditable(false);
		box3.add(new JLabel("x"));	
		box3.add(x);
		box3.add(Box.createVerticalStrut(5));
		y.setAlignmentX(LEFT_ALIGNMENT);
		y.setEditable(false);
		box3.add(new JLabel("y"));	
		box3.add(y);
		add(box3);
		
		Box box2 = Box.createVerticalBox();
		box2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		box2.add(new JLabel("  ggt"));		
		box2.add(Box.createVerticalStrut(5));
		g.setAlignmentX(LEFT_ALIGNMENT);
		g.setEditable(false);
		box2.add(g);
		add(box2);
	}

	private void readInput() {
		try {
			
			model.setA(Integer.valueOf(a.getText()));
			model.setB(Integer.valueOf(b.getText()));
			model.ggt();
			
		}catch (ArithmeticException e){
			JOptionPane.showMessageDialog(this,"ggt(0,0) ist undefiniert","Mathematisches Problem",JOptionPane.ERROR_MESSAGE);
			clear();
		
		}catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(this,"Falsches Zahlenformat","Eingabefehler",JOptionPane.ERROR_MESSAGE);
			clear();
		
		} 
		catch (NegativeNumberException e1) {
			JOptionPane.showMessageDialog(this,e1.getMessage(),"Eingabefehler",JOptionPane.ERROR_MESSAGE);
			clear();
		}
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == compute){ 
				readInput();	
		}
	}
		

	@Override
	public void update(Observable arg0, Object arg1) {
		g.setText(model.getGgt()+"");
		x.setText(model.getX()+"");
		y.setText(model.getY()+"");
	}
	public void clear(){
		a.setText("");
		b.setText("");
		g.setText("");
		x.setText("");
		y.setText("");
	}
}



