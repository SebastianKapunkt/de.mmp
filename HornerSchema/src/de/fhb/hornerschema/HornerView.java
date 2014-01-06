package de.fhb.hornerschema;

import de.fhb.hornerschema.exceptions.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class HornerView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNumber = new JTextField();
	private JTextField textFieldb1 = new JTextField();
	private JTextField textFieldb2 = new JTextField();
	private JTextField textFieldResult = new JTextField();

	private JButton btnTransform = new JButton("transform");
	private JButton btnClear = new JButton("clear");

	private JLabel lblNumber = new JLabel("number");
	private JLabel lblZahlensystem = new JLabel("b1");
	private JLabel lblZahlensystem_1 = new JLabel("b2");
	private JLabel lblNumber_1 = new JLabel("destination number");

	HornerModel model;

	public HornerView(HornerModel model) {

		this.model = model;
		model.addObserver(this);

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setAlignmentY(Component.TOP_ALIGNMENT);
		add(verticalBox);

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(horizontalBox_1);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue);
		horizontalBox_1.add(lblNumber);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_7);
		Box verticalBox_2 = Box.createVerticalBox();
		horizontalBox_1.add(verticalBox_2);
		Box verticalBox_6 = Box.createVerticalBox();
		verticalBox_2.add(verticalBox_6);

		verticalBox_6.add(textFieldNumber);
		textFieldNumber.setPreferredSize(new Dimension(500, 20));
		textFieldNumber.setMaximumSize(new Dimension(100, 20));
		textFieldNumber.setColumns(10);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut);

		horizontalBox_1.add(lblZahlensystem);
		Box verticalBox_3 = Box.createVerticalBox();
		horizontalBox_1.add(verticalBox_3);

		verticalBox_3.add(textFieldb1);
		textFieldb1.setMaximumSize(new Dimension(40, 20));
		textFieldb1.setColumns(10);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);
		Component verticalGlue = Box.createVerticalGlue();
		horizontalBox_1.add(verticalGlue);
		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(horizontalBox_2);
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox_2.add(horizontalGlue_1);

		horizontalBox_2.add(lblZahlensystem_1);
		Box verticalBox_4 = Box.createVerticalBox();
		horizontalBox_2.add(verticalBox_4);

		verticalBox_4.add(textFieldb2);
		textFieldb2.setMaximumSize(new Dimension(40, 20));
		textFieldb2.setColumns(10);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(30, 0));
		horizontalBox_2.add(horizontalStrut_2);

		btnTransform.addActionListener(this);
		horizontalBox_2.add(btnTransform);
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setPreferredSize(new Dimension(40, 0));
		horizontalBox_2.add(horizontalStrut_4);
		Box verticalBox_5 = Box.createVerticalBox();
		verticalBox_5.setMinimumSize(new Dimension(44, 0));
		horizontalBox_2.add(verticalBox_5);
		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(horizontalBox_3);
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox_3.add(horizontalGlue_2);

		horizontalBox_3.add(lblNumber_1);
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_6);

		textFieldResult.setEditable(false);
		textFieldResult.setMaximumSize(new Dimension(200, 20));
		horizontalBox_3.add(textFieldResult);
		textFieldResult.setColumns(10);
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(33, 0));
		horizontalBox_3.add(horizontalStrut_3);

		btnClear.addActionListener(this);
		horizontalBox_3.add(btnClear);
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setPreferredSize(new Dimension(40, 0));
		horizontalBox_3.add(horizontalStrut_5);
		Component verticalGlue_1 = Box.createVerticalGlue();
		verticalBox.add(verticalGlue_1);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		textFieldResult.setText(model.getOutputString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			textFieldb1.setText("");
			textFieldb2.setText("");
			textFieldNumber.setText("");
			textFieldResult.setText("");
		}
		if (e.getSource() == btnTransform) {
			try {
				if (textFieldb1.getText().isEmpty()
						|| textFieldb2.getText().isEmpty()
						|| textFieldNumber.getText().isEmpty()) {
					throw new NoValueFoundException();
				} else {

					model.transformStringToNumbersystem(textFieldb1.getText(),
							textFieldNumber.getText(), textFieldb2.getText());
				}
			} catch (integerOverflow e1) {
				JOptionPane.showMessageDialog(this,
						"Number is bigger than MaxInt.", "Overflow",
						JOptionPane.ERROR_MESSAGE);
			} catch (NoValueFoundException e1) {
				JOptionPane.showMessageDialog(this,
						"Not all fields are filled with numbers",
						"No value found", JOptionPane.ERROR_MESSAGE);
			} catch (WrongNumberFormatException e1) {
				JOptionPane.showMessageDialog(this, "Wrong number format",
						"Number format", JOptionPane.ERROR_MESSAGE);
			} catch (TooBigNumberSystem e1) {
				JOptionPane.showMessageDialog(this, "Numbersystem is too big",
						"Numbersystem Error", JOptionPane.ERROR_MESSAGE);

			}

		}
	}
}
