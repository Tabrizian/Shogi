package game.gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class FormPanel extends JPanel {
	private JLabel label;
	public FormPanel(){
		label = new JLabel("");
		add(label);
		Dimension dim = getPreferredSize();
		dim.height = 50;
		setPreferredSize(dim);
	}
	
	public JLabel getLabel(){
		return label;
	}
}
