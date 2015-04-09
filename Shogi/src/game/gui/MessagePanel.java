package game.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class MessagePanel extends JPanel {
	private JLabel label;
	public MessagePanel(){
		label = new JLabel("");
		add(label);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		Dimension dim = getPreferredSize();
		dim.height = 25;
		setPreferredSize(dim);
	}
	
	public JLabel getLabel(){
		return label;
	}
	
	public void showMessage(String s){
		label.setText(s);
	}
}
