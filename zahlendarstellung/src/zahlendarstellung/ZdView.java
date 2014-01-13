package zahlendarstellung;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import exceptions.NoInputFoundException;
import exceptions.ToBigSystemException;
import exceptions.ToSmallSystemException;
import exceptions.ValueToBigException;
import exceptions.WrongInputException;
import exceptions.WrongNumberInputException;

/**
 * Erstellt das GUI, überprüft die Eingabe und zeigt das Ergebnis der Berechnung
 * des Models an übernimmt somit die Rolle der View und des Controllers
 */
public class ZdView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;

	ZdModel model;
	private JTextField inputnumber = new JTextField();;
	private JTextField inputsystem = new JTextField();;
	private JTextField outputnumber = new JTextField();;
	private JTextField outputsystem = new JTextField();;

	private JTextField controlresult = new JTextField();
	private JTextField a = new JTextField();
	private JTextField n = new JTextField();
	private JTextField m = new JTextField();
	private JTextField result = new JTextField();

	JLabel lblNumbersystem = new JLabel("Numbersystem 2-36 for Integer numbers");
	JLabel inNuLabel = new JLabel("input Number:");
	JLabel inSyLabel = new JLabel("input System: ");
	JLabel ouNuLabel = new JLabel("output Number:");
	JLabel ouSyLabel = new JLabel("output System:");

	JLabel lblModularePotenz = new JLabel("Modulare Potenz");
	JLabel lblA = new JLabel("a ");
	JLabel lblN = new JLabel("n ");
	JLabel lblModM = new JLabel("mod m");
	JLabel lblAnmodm = new JLabel("a^n mod m");
	JLabel lblControlValue = new JLabel("control Value");

	JButton btnConvert = new JButton("convert");
	JButton btnClear = new JButton("clear");

	JButton btnis = new JButton("=");

	public ZdView(ZdModel model) {
		this.model = model;
		model.addObserver(this);

		Box verticalBox = Box.createVerticalBox();
		add(verticalBox);

		Component verticalStrut_4 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_4);

		lblNumbersystem.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(lblNumbersystem);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		Box verticalBox_1 = Box.createVerticalBox();
		horizontalBox.add(verticalBox_1);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_1);

		horizontalBox_1.add(inNuLabel);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut);

		horizontalBox_1.add(inputnumber);
		inputnumber.setColumns(10);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox_1.add(verticalStrut);

		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalStrut_7.setPreferredSize(new Dimension(350, 0));
		horizontalStrut_7.setMinimumSize(new Dimension(400, 0));
		horizontalStrut_7.setMaximumSize(new Dimension(400, 0));
		verticalBox_1.add(horizontalStrut_7);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_2);

		horizontalBox_2.add(inSyLabel);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_1);
		inputsystem.setMaximumSize(new Dimension(40, 2147483647));

		horizontalBox_2.add(inputsystem);
		inputsystem.setColumns(4);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox_2.add(horizontalGlue);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_4);

		Box verticalBox_2 = Box.createVerticalBox();
		horizontalBox.add(verticalBox_2);
		btnConvert.setMaximumSize(new Dimension(90, 50));
		btnConvert.setPreferredSize(new Dimension(90, 50));

		btnConvert.addActionListener(this);
		verticalBox_2.add(btnConvert);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_5);

		Box verticalBox_3 = Box.createVerticalBox();
		horizontalBox.add(verticalBox_3);

		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBox_3.add(horizontalBox_3);

		horizontalBox_3.add(ouNuLabel);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_2);
		outputnumber.setEditable(false);
		horizontalBox_3.add(outputnumber);
		outputnumber.setColumns(10);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox_3.add(verticalStrut_1);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalStrut_6.setPreferredSize(new Dimension(350, 0));
		horizontalStrut_6.setMinimumSize(new Dimension(400, 0));
		horizontalStrut_6.setMaximumSize(new Dimension(400, 0));
		verticalBox_3.add(horizontalStrut_6);

		Box horizontalBox_4 = Box.createHorizontalBox();
		verticalBox_3.add(horizontalBox_4);

		horizontalBox_4.add(ouSyLabel);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_3);
		outputsystem.setMaximumSize(new Dimension(40, 2147483647));

		horizontalBox_4.add(outputsystem);
		outputsystem.setColumns(4);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox_4.add(horizontalGlue_1);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);

		Box horizontalBox_5 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_5);

		btnClear.addActionListener(this);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox_5.add(horizontalGlue_2);
		horizontalBox_5.add(btnClear);

		Component verticalStrut_5 = Box.createVerticalStrut(20);
		verticalStrut_5.setPreferredSize(new Dimension(0, 80));
		verticalStrut_5.setMaximumSize(new Dimension(32767, 80));
		verticalBox.add(verticalStrut_5);

		lblModularePotenz.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBox.add(lblModularePotenz);

		Component verticalStrut_6 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_6);

		Box horizontalBox_6 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_6);

		horizontalBox_6.add(lblA);

		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_8);

		a.setMaximumSize(new Dimension(100, 2147483647));
		horizontalBox_6.add(a);
		a.setColumns(10);

		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_9);

		horizontalBox_6.add(lblN);

		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_10);

		n.setMaximumSize(new Dimension(100, 2147483647));
		horizontalBox_6.add(n);
		n.setColumns(10);

		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_11);

		horizontalBox_6.add(lblModM);

		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_12);

		m.setMaximumSize(new Dimension(100, 2147483647));
		horizontalBox_6.add(m);
		m.setColumns(10);

		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_15);

		btnis.addActionListener(this);
		horizontalBox_6.add(btnis);

		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_13);

		horizontalBox_6.add(lblAnmodm);

		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_14);

		result.setEditable(false);
		result.setMaximumSize(new Dimension(100, 2147483647));
		horizontalBox_6.add(result);
		result.setColumns(10);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBox_6.add(horizontalGlue_3);

		Component verticalStrut_7 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_7);

		Box horizontalBox_7 = Box.createHorizontalBox();
		horizontalBox_7.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox.add(horizontalBox_7);

		Component horizontalStrut_17 = Box.createHorizontalStrut(20);
		horizontalStrut_17.setMaximumSize(new Dimension(63, 32767));
		horizontalBox_7.add(horizontalStrut_17);

		horizontalBox_7.add(lblControlValue);

		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		horizontalBox_7.add(horizontalStrut_16);

		controlresult.setMaximumSize(new Dimension(86, 2147483647));
		horizontalBox_7.add(controlresult);
		controlresult.setColumns(10);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		outputnumber.setText(model.getOutputnumber());
		inputnumber.setText(model.getInputnumber());
		outputsystem.setText(model.getOutputsystem() + "");
		inputsystem.setText(model.getInputsystem() + "");
		result.setText(model.getModPot() + "");
	}

	public void clear() {
		inputnumber.setText("");
		model.setInputnumber("0");
		inputsystem.setText("");
		model.setInputsystem(0);
		outputnumber.setText("");
		model.setOutputnumber("0");
		outputsystem.setText("");
		model.setOutputsystem(0);
		result.setText("");
		model.setModPot(0);
	}

	public void readInput() throws NoInputFoundException {
		if (inputnumber.getText().equals("")
				|| inputsystem.getText().equals("")
				|| outputsystem.getText().equals("")) {
			throw new NoInputFoundException();
		} else {
			model.setInputnumber(inputnumber.getText());
			model.setInputsystem(Integer.parseInt(inputsystem.getText()));
			model.setOutputsystem(Integer.parseInt(outputsystem.getText()));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConvert) {
			try {
				outputnumber.setText("");
				readInput();
				model.setNumber(model.transformToArray(model.getInputnumber()));
				model.checknumber(model.getInputsystem());
				model.setOutputnumber(model.transformFromTen(
						model.transformToTen(model.getInputsystem()),
						model.getOutputsystem()));
			} catch (WrongInputException e1) {
				JOptionPane.showMessageDialog(this,
						"Eingabefelder enthalten ungültige Zeichen",
						"Eingabefehler", JOptionPane.ERROR_MESSAGE);
			} catch (WrongNumberInputException e1) {
				JOptionPane.showMessageDialog(this,
						"Ausgangs Zahl enthält zu große Werte",
						"Eingabefehler", JOptionPane.ERROR_MESSAGE);
			} catch (ToBigSystemException e1) {
				JOptionPane.showMessageDialog(this,
						"Zahlensystem ist zu groß gewählt", "Eingabefehler",
						JOptionPane.ERROR_MESSAGE);
			} catch (ValueToBigException e1) {
				JOptionPane.showMessageDialog(this,
						"Wert überschreitet wertebereich von Integer",
						"To Big Value", JOptionPane.ERROR_MESSAGE);
			} catch (ToSmallSystemException e1) {
				JOptionPane
						.showMessageDialog(
								this,
								"Zahlensystem ist zu klein, Zahl kann nicht dargestellt werden",
								"Eingabefehler", JOptionPane.ERROR_MESSAGE);
			} catch (NoInputFoundException e1) {
				JOptionPane.showMessageDialog(this,
						"ein oder mehrere Eingabefelder sind leer",
						"Eingabefehler", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException e1) {
				JOptionPane
						.showMessageDialog(
								this,
								"Input und/oder Output System ist/sind keine ganze Zahl",
								"Eingabefehler", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == btnClear) {
			clear();
		}
		if (e.getSource() == btnis) {
			try {
				model.setModPot(model.runModPot(
						Integer.parseInt(m.getText()),
						Integer.parseInt(a.getText()),
						model.stringReverse(model.transformFromTen(
								Integer.parseInt(n.getText()), 2))));

			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (ToBigSystemException e1) {
				e1.printStackTrace();
			} catch (ToSmallSystemException e1) {
				e1.printStackTrace();
			}
		}
	}

}
