package zahlendarstellung;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

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

	JLabel inNuLabel = new JLabel("input Number:");
	JLabel inSyLabel = new JLabel("input System: ");
	JLabel ouNuLabel = new JLabel("output Number:");
	JLabel ouSyLabel = new JLabel("output System:");

	JButton btnConvert = new JButton("convert");
	JButton btnClear = new JButton("clear");

	public ZdView(ZdModel model) {
		this.model = model;
		model.addObserver(this);

		Box verticalBox = Box.createVerticalBox();
		add(verticalBox);

		Component verticalStrut_4 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_4);

		JLabel lblNumbersystem = new JLabel("Numbersystem 1-36");
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

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_2);

		horizontalBox_2.add(inSyLabel);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_1);
		inputsystem.setMaximumSize(new Dimension(40, 2147483647));

		horizontalBox_2.add(inputsystem);
		inputsystem.setColumns(4);

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
		outputsystem.setColumns(10);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);

		Box horizontalBox_5 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_5);

		btnClear.addActionListener(this);
		horizontalBox_5.add(btnClear);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		outputnumber.setText(model.getOutputnumber());
		inputnumber.setText(model.getInputnumber());
		outputsystem.setText(model.getOutputsystem() + "");
		inputsystem.setText(model.getInputsystem() + "");
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
				model.transformFromTen(
						model.transformToTen(model.getInputsystem()),
						model.getOutputsystem());
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
			} catch (NumberFormatException e1){
				JOptionPane.showMessageDialog(this,
						"Input und/oder Output System ist/sind keine ganze Zahl",
						"Eingabefehler", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == btnClear) {
			clear();
		}
	}

}
