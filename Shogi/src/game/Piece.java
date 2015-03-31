package game;

import java.util.ArrayList;

import javax.swing.JButton;

public abstract class Piece {

	private Position pos;
	
	public abstract boolean move(Position pos,Table table,Game game,Player player);	
	protected abstract Position getDefaultPos();
	public abstract ArrayList<Position> getAllowedCells(Game game,Player player);
	protected boolean isUpgraded = false;
	
	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

}
