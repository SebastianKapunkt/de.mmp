/** @author:
 * Eric Dobritius 	 20110009
 * Gordan Just		 20091313 
 * Sebastian Kindt 	 20120023
 * 
 * @version: 1.0
 */
package de.fhb.kryptografie;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.fhb.kryptografie.exceptions.NoValueFoundException;
import de.fhb.kryptografie.exceptions.WrongNumberFormatException;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JRadioButton;

public class KgView extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	KgModel model;
	SetMaxText maxText;

	JTextField keyField = new JTextField();

	JButton plainClear = new JButton("clear");
	JButton btnEncipher = new JButton("cipher -->");
	JButton btnDecipher = new JButton("<-- decipher");
	JButton cipherClear = new JButton("clear");
	JButton btnChosePlainFile = new JButton("chose plain file");
	JButton btnChoseCipherFile = new JButton("chose cipher file");

	JLabel plainLabel = new JLabel("plain text");
	JLabel keyLabel = new JLabel("key");
	JLabel cipherLabel = new JLabel("cipher text");
	JLabel plainSourceLabel = new JLabel("");
	JLabel cipherSourceLabel = new JLabel("");

	JTextArea plainArea = new JTextArea(10, 10);
	JTextArea cipherArea = new JTextArea();

	JFileChooser plainChooser = new JFileChooser();
	JFileChooser cipherChooser = new JFileChooser();

	JRadioButton rdbtnNormal = new JRadioButton("normal");
	JRadioButton rdbtnEntschlsseln = new JRadioButton("decrypt Caesar");
	JRadioButton rdbtnEntschlsselnVigenere = new JRadioButton(
			"decrypt Vigenere");

	public KgView(KgModel model) {
		this.model = model;

		// Generieren der Constanten Hash-Tabelle
		model.generateConstSignProbability();

		model.addObserver(this);
		setBackground(Color.lightGray);

		Box verticalBox = Box.createVerticalBox();
		add(verticalBox);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		btnChosePlainFile.addActionListener(this);
		horizontalBox_1.add(btnChosePlainFile);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_5);

		horizontalBox_1.add(plainSourceLabel);

		ButtonGroup group = new ButtonGroup();

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_2);
		horizontalBox_1.add(rdbtnNormal);

		rdbtnNormal.addActionListener(this);
		rdbtnNormal.setSelected(true);
		rdbtnNormal.setActionCommand("N");
		group.add(rdbtnNormal);
		horizontalBox_1.add(rdbtnEntschlsseln);

		rdbtnEntschlsseln.addActionListener(this);
		rdbtnEntschlsseln.setActionCommand("C");
		group.add(rdbtnEntschlsseln);
		horizontalBox_1.add(rdbtnEntschlsselnVigenere);

		rdbtnEntschlsselnVigenere.addActionListener(this);
		rdbtnEntschlsselnVigenere.setActionCommand("V");
		rdbtnEntschlsselnVigenere.setEnabled(false);
		group.add(rdbtnEntschlsselnVigenere);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_3);

		horizontalBox_1.add(cipherSourceLabel);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_6);

		btnChoseCipherFile.addActionListener(this);
		horizontalBox_1.add(btnChoseCipherFile);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		horizontalBox.setAlignmentY(Component.CENTER_ALIGNMENT);

		Box leftBox = Box.createVerticalBox();
		horizontalBox.add(leftBox);

		Box rowOneLeftBox = Box.createHorizontalBox();
		leftBox.add(rowOneLeftBox);

		rowOneLeftBox.add(plainLabel);

		Component verticalStrut = Box.createVerticalStrut(40);
		rowOneLeftBox.add(verticalStrut);

		Box rowTwoLeftBox = Box.createHorizontalBox();
		leftBox.add(rowTwoLeftBox);

		plainArea.setLineWrap(true);
		plainArea.setWrapStyleWord(true);
		rowTwoLeftBox.add(new JScrollPane(plainArea));

		Component verticalStrut_2 = Box.createVerticalStrut(300);
		verticalStrut_2.setMaximumSize(new Dimension(0, 400));
		rowTwoLeftBox.add(verticalStrut_2);

		Component horizontalStrut_3 = Box.createHorizontalStrut(300);
		horizontalStrut_3.setMaximumSize(new Dimension(200, 0));
		leftBox.add(horizontalStrut_3);

		Box rowThreeLeftBox = Box.createHorizontalBox();
		leftBox.add(rowThreeLeftBox);

		plainClear.addActionListener(this);
		rowThreeLeftBox.add(plainClear);

		Component horizontalGlue = Box.createHorizontalGlue();
		rowThreeLeftBox.add(horizontalGlue);

		Box middleBox = Box.createVerticalBox();
		horizontalBox.add(middleBox);

		Box horizontalBox_3 = Box.createHorizontalBox();
		middleBox.add(horizontalBox_3);
		horizontalBox_3.setMaximumSize(new Dimension(200, 200));

		Component horizontalStrut = Box.createHorizontalStrut(10);
		horizontalStrut.setMaximumSize(new Dimension(10, 0));
		horizontalBox_3.add(horizontalStrut);

		Box verticalBox_3 = Box.createVerticalBox();
		horizontalBox_3.add(verticalBox_3);

		verticalBox_3.add(keyLabel);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setMaximumSize(new Dimension(20, 0));
		horizontalBox_3.add(horizontalStrut_2);

		Box verticalBox_4 = Box.createVerticalBox();
		horizontalBox_3.add(verticalBox_4);
		keyField.setMinimumSize(new Dimension(25, 20));
		keyField.setMaximumSize(new Dimension(2147483647, 20));
		keyField.setColumns(15);
		keyField.setDocument(new SetMaxText(10));
		verticalBox_4.add(keyField);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setMaximumSize(new Dimension(20, 0));
		horizontalBox_3.add(horizontalStrut_1);

		Component verticalStrut_4 = Box.createVerticalStrut(40);
		horizontalBox_3.add(verticalStrut_4);

		Box horizontalBox_4 = Box.createHorizontalBox();
		middleBox.add(horizontalBox_4);

		Box verticalBox_5 = Box.createVerticalBox();
		horizontalBox_4.add(verticalBox_5);

		Component verticalGlue_1 = Box.createVerticalGlue();
		verticalBox_5.add(verticalGlue_1);

		Box horizontalBox_7 = Box.createHorizontalBox();
		verticalBox_5.add(horizontalBox_7);
		btnEncipher.addActionListener(this);
		btnEncipher.setMaximumSize(new Dimension(110, 23));
		btnEncipher.setMinimumSize(new Dimension(110, 23));
		btnEncipher.setPreferredSize(new Dimension(100, 23));
		horizontalBox_7.add(btnEncipher);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox_5.add(verticalStrut_1);

		Box horizontalBox_8 = Box.createHorizontalBox();
		verticalBox_5.add(horizontalBox_8);
		btnDecipher.addActionListener(this);
		btnDecipher.setMinimumSize(new Dimension(110, 23));
		btnDecipher.setMaximumSize(new Dimension(110, 23));
		btnDecipher.setPreferredSize(new Dimension(100, 23));
		horizontalBox_8.add(btnDecipher);

		Component verticalGlue = Box.createVerticalGlue();
		verticalBox_5.add(verticalGlue);

		Box rightBox = Box.createVerticalBox();
		horizontalBox.add(rightBox);

		Box horizontalBox_5 = Box.createHorizontalBox();
		rightBox.add(horizontalBox_5);

		horizontalBox_5.add(cipherLabel);

		Component horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalBox_5.add(horizontalGlue_4);

		Component verticalStrut_3 = Box.createVerticalStrut(50);
		verticalStrut_3.setMaximumSize(new Dimension(0, 50));
		horizontalBox_5.add(verticalStrut_3);

		Box horizontalBox_6 = Box.createHorizontalBox();
		rightBox.add(horizontalBox_6);

		cipherArea.setLineWrap(true);
		cipherArea.setWrapStyleWord(true);
		horizontalBox_6.add(new JScrollPane(cipherArea));

		Component verticalStrut_5 = Box.createVerticalStrut(300);
		verticalStrut_5.setMaximumSize(new Dimension(0, 400));
		horizontalBox_6.add(verticalStrut_5);

		Component horizontalStrut_4 = Box.createHorizontalStrut(300);
		horizontalStrut_4.setMaximumSize(new Dimension(200, 0));
		rightBox.add(horizontalStrut_4);

		Box horizontalBox_9 = Box.createHorizontalBox();
		rightBox.add(horizontalBox_9);

		cipherClear.addActionListener(this);
		horizontalBox_9.add(cipherClear);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox_9.add(horizontalGlue_1);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		plainChooser.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
		cipherChooser.addChoosableFileFilter(new FileNameExtensionFilter("txt",
				"txt"));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		plainArea.setText(model.getPlainText());
		cipherArea.setText(model.getCypherText());
		keyField.setText(model.getKey());
		if (plainChooser.getSelectedFile() != null) {
			plainSourceLabel.setText(plainChooser.getSelectedFile().getName());
		}
		if (cipherChooser.getSelectedFile() != null) {
			cipherSourceLabel
					.setText(cipherChooser.getSelectedFile().getName());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Plain-text Clear Button Action
		if (e.getSource() == plainClear) {
			plainArea.setText("");
			model.setPlainText("");
			plainSourceLabel.setText("");
		}
		// Encipher Button Action
		if (e.getSource() == btnEncipher) {
			readInputDecypherArea();
			try {
				if (keyField.getText().isEmpty()
						|| plainArea.getText().isEmpty()) {
					throw new NoValueFoundException();
				} else {
					model.cipher(model.transform(plainArea.getText()),
							model.calculateKey(keyField.getText()));
				}
			} catch (WrongNumberFormatException e1) {
				JOptionPane.showMessageDialog(this,
						"KEY: Only letters A-Z or a-z possible.",
						"wrong input", JOptionPane.ERROR_MESSAGE);
			} catch (NoValueFoundException e1) {
				JOptionPane.showMessageDialog(this,
						"No content at plain text or key", "No value found",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		// Decipher Button Action
		if (e.getSource() == btnDecipher && rdbtnNormal.isSelected()) {
			readInputCypherArea();
			try {
				if (keyField.getText().isEmpty()
						|| cipherArea.getText().isEmpty()) {
					throw new NoValueFoundException();
				} else {
					model.decipher(model.transform(cipherArea.getText()),
							model.calculateKey(keyField.getText()));
				}
			} catch (WrongNumberFormatException e1) {
				JOptionPane.showMessageDialog(this,
						"KEY: Only uppercase letters A-Z possible.",
						"wrong input", JOptionPane.ERROR_MESSAGE);
			} catch (NoValueFoundException e1) {
				JOptionPane.showMessageDialog(this,
						"No content at cipher text or key", "No value found",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == btnDecipher
				&& rdbtnEntschlsseln.isSelected()) {
			readInputCypherArea();
			try {
				if (cipherArea.getText().isEmpty()) {
					throw new NoValueFoundException();
				} else {
					model.decipherCaesar(model.transform(cipherArea.getText()));
				}
			} catch (NoValueFoundException e1) {
				JOptionPane.showMessageDialog(this,
						"No content at cipher text", "No value found",
						JOptionPane.ERROR_MESSAGE);
			} catch (WrongNumberFormatException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == btnDecipher
				&& rdbtnEntschlsselnVigenere.isSelected()) {
			readInputCypherArea();
			try {
				if (cipherArea.getText().isEmpty()) {
					throw new NoValueFoundException();
				} else {
					model.decipherVigenere(model.transform(cipherArea.getText()));
				}
			} catch (NoValueFoundException e1) {
				JOptionPane.showMessageDialog(this,
						"No content at cipher text", "No value found",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		// Ciphertext clear Button Action
		if (e.getSource() == cipherClear) {
			cipherArea.setText("");
			model.setCypherText("");
			cipherSourceLabel.setText("");
		}
		// PlainFileChooser Button Action
		if (e.getSource() == btnChosePlainFile) {
			clear();
			if (plainChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				try {
					model.openFile(plainChooser.getSelectedFile(), true);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "error at reading",
							"I/O Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		// CipherFileChooser Button Action
		if (e.getSource() == btnChoseCipherFile) {
			clear();
			if (cipherChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				try {
					model.openFile(cipherChooser.getSelectedFile(), false);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "error at reading",
							"I/O Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		// Radiobutton Normal Action
		if (e.getSource() == rdbtnNormal) {
			changemode();
			clear();
		}
		// Radiobutton Entschlüsseln Action
		if (e.getSource() == rdbtnEntschlsseln) {
			changemode();

			clear();
		}
		// Radiobutton EntschlsselnVigenere Action
		if (e.getSource() == rdbtnEntschlsselnVigenere) {
			changemode();
			clear();
		}
	}

	/**
	 * Deaktiviert/Aktiviert Elemente der Gui je nach dem welcher Radiobutton
	 * selektiert ist.
	 */
	private void changemode() {
		if (rdbtnNormal.isSelected()) {
			btnEncipher.setEnabled(true);
			btnChosePlainFile.setEnabled(true);
			keyField.setEditable(true);
			plainArea.setEditable(true);
		} else {
			btnEncipher.setEnabled(false);
			btnChosePlainFile.setEnabled(false);
			keyField.setEditable(false);
			plainArea.setEditable(false);
		}
	}

	/**
	 * Löscht den Inhalt der beiden Text-Area und des Keyfiled.
	 */
	private void clear() {
		keyField.setText("");
		model.setKey("");
		plainArea.setText("");
		model.setPlainText("");
		cipherArea.setText("");
		model.setCypherText("");
	}

	/**
	 * Liest den Inhalt des JTextArea mit dem Name cipherArea aus.
	 */
	private void readInputDecypherArea() {
		model.setCypherText(cipherArea.getText());
	}

	/**
	 * Liest den Inhalt des JtextArea mit dem Namen plainArea aus.
	 */
	private void readInputCypherArea() {
		model.setPlainText(plainArea.getText());
	}
}
