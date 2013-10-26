package de.fhb.kryptografie;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Component;

import java.awt.Dimension;

public class KgView extends JPanel implements ActionListener, Observer{
	
	KgModel model;
	SetMaxText maxText;
	
	JTextField keyField = new JTextField();
	
	JButton cypherClear = new JButton("clear");
	JButton decypher = new JButton("decypher -->");
	JButton encypher = new JButton("<-- encyper");
	JButton decypherClear = new JButton("clear");
	
	JLabel cypherLabel = new JLabel("cypher text");
	JLabel keyLabel = new JLabel("key");
	JLabel decypherLabel = new JLabel("decypher text");
	
	JTextArea cypherArea = new JTextArea(10,10);
	JTextArea decypherArea = new JTextArea();
	
	public KgView(KgModel model) {
		this.model = model;
		model.addObserver(this);
		setBackground(Color.lightGray);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setAlignmentY(Component.CENTER_ALIGNMENT);
		add(horizontalBox);
		
		Box leftBox = Box.createVerticalBox();
		horizontalBox.add(leftBox);
		
		Box rowOneLeftBox = Box.createHorizontalBox();
		leftBox.add(rowOneLeftBox);
		
		rowOneLeftBox.add(cypherLabel);
		
		Component verticalStrut = Box.createVerticalStrut(40);
		rowOneLeftBox.add(verticalStrut);
		
		Box rowTwoLeftBox = Box.createHorizontalBox();
		leftBox.add(rowTwoLeftBox);
		
		cypherArea.setLineWrap(true);
        cypherArea.setWrapStyleWord(true);
        rowTwoLeftBox.add(new JScrollPane(cypherArea));
		
		Component verticalStrut_2 = Box.createVerticalStrut(300);
		verticalStrut_2.setMaximumSize(new Dimension(0, 400));
		rowTwoLeftBox.add(verticalStrut_2);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(300);
		horizontalStrut_3.setMaximumSize(new Dimension(200, 0));
		leftBox.add(horizontalStrut_3);
		
		Box rowThreeLeftBox = Box.createHorizontalBox();
		leftBox.add(rowThreeLeftBox);
		
		cypherClear.addActionListener(this);
		rowThreeLeftBox.add(cypherClear);
		
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
		decypher.addActionListener(this);
		decypher.setMaximumSize(new Dimension(110, 23));
		decypher.setMinimumSize(new Dimension(110, 23));
		decypher.setPreferredSize(new Dimension(100, 23));
		horizontalBox_7.add(decypher);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox_5.add(verticalStrut_1);
		
		Box horizontalBox_8 = Box.createHorizontalBox();
		verticalBox_5.add(horizontalBox_8);
		encypher.addActionListener(this);
		encypher.setMinimumSize(new Dimension(110, 23));
		encypher.setMaximumSize(new Dimension(110, 23));
		encypher.setPreferredSize(new Dimension(100, 23));
		horizontalBox_8.add(encypher);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalBox_5.add(verticalGlue);
		
		Box rightBox = Box.createVerticalBox();
		horizontalBox.add(rightBox);
		
		Box horizontalBox_5 = Box.createHorizontalBox();
		rightBox.add(horizontalBox_5);

		horizontalBox_5.add(decypherLabel);
		
		Component verticalStrut_3 = Box.createVerticalStrut(50);
		horizontalBox_5.add(verticalStrut_3);
		
		Box horizontalBox_6 = Box.createHorizontalBox();
		rightBox.add(horizontalBox_6);
		
		decypherArea.setLineWrap(true);
        decypherArea.setWrapStyleWord(true);
		horizontalBox_6.add(new JScrollPane(decypherArea));
		
		Component verticalStrut_5 = Box.createVerticalStrut(300);
		verticalStrut_5.setMaximumSize(new Dimension(0, 400));
		horizontalBox_6.add(verticalStrut_5);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(300);
		horizontalStrut_4.setMaximumSize(new Dimension(200, 0));
		rightBox.add(horizontalStrut_4);
		
		Box horizontalBox_9 = Box.createHorizontalBox();
		rightBox.add(horizontalBox_9);
		
		decypherClear.addActionListener(this);
		horizontalBox_9.add(decypherClear);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox_9.add(horizontalGlue_1);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		cypherArea.setText(model.getCypherText());
		decypherArea.setText(model.getDeCypherText());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cypherClear){
			cypherArea.setText("");
		}
		if(e.getSource() == decypher){
			readInputDecypherArea();
			model.transform();;
		}
		if(e.getSource() == encypher){
			readInputCypherArea();
		}
		if(e.getSource() == decypherClear){
			decypherArea.setText("");
		}
	}

	private void readInputDecypherArea() {
		model.setDeCypherText(decypherArea.getText());
	}

	private void readInputCypherArea() {
		model.setCypherText(cypherArea.getText());
	}

}
