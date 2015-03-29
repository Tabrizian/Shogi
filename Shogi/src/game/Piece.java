package game;

import java.util.ArrayList;

import javax.swing.JButton;

public abstract class Piece {

	private Position pos;
	protected JButton button;
	public abstract void move(Position pos,Table table);	
	protected abstract Position getDefaultPos();
	public abstract ArrayList<Position> getAllowedCells(Table table, int playerId);

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
	
	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

}
