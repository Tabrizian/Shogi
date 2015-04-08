package game.gui;
import game.Position;
import game.Table;
import game.pieces.None;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	
	private LayoutManager layout;
	private JButton[][] buttons;
	private Table table;
	public BoardPanel(){
		
	}
	public void initialize(Table table){
		layout = new GridLayout(9,9,2,2);
		setLayout(layout);
		buttons = new JButton[9][9];
		this.table = table;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				buttons[i][j] = new JButton();
				table.setTableCell(new Position(j, i),new None(table, new Position(j, i)));
				setButtonText(new Position(j, i));
				add(buttons[i][j]);
			}
		}
	}
	public JButton[][] getButtons() {
		return buttons;
	}

	public JButton getButton(Position pos) {
		return buttons[pos.getY()][pos.getX()];
	}

	public Position findPressedButton() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				if (buttons[i][j].getBackground() == Color.ORANGE)
					return (new Position(j, i));
			}
		}
		return null;
	}
	
	public void setButtonText(Position pos){
		buttons[pos.getY()][pos.getX()].setText(table.getTableCell(new Position(pos.getX(), pos.getY())).toString());
	}
	
	public void setBorder(Position pos){
		buttons[pos.getY()][pos.getX()].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	}
	public void setDefaultBorder(Position pos){
		buttons[pos.getY()][pos.getX()].setBorder(BorderFactory.createEmptyBorder());;
	}
}
