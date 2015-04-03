package game;

import java.util.ArrayList;

public abstract class Piece {

	private Position pos;
	protected boolean upgraded = false;
	
	public abstract boolean move(Position pos,Table table,Game game,Player player);	
	protected abstract Position getDefaultPos();
	public abstract ArrayList<Position> getAllowedCells(Game game,Player player);
	public abstract void chechForUpgrade(Player player);
	
	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

}
