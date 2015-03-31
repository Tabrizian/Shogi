package game.gui;

import game.Piece;
import game.Player;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FormPanel extends JPanel {
	private JLabel label;
	public FormPanel(){
		label = new JLabel("");
		add(label);
		Dimension dim = getPreferredSize();
		dim.width = 150;
		setPreferredSize(dim);
	}
	
	public JLabel getLabel(){
		return label;
	}
}
