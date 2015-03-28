package game.pieces;
import java.util.ArrayList;

import game.Piece;
import game.Position;
import game.Table;


public class Knight extends Piece {
	
	private static int population=0;
	
	public Knight(Table table){
		Position defaultPos = getDefaultPos();
		setPos(defaultPos);
		table.setTableCell(defaultPos, this);
		population++;
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Position getDefaultPos() {
		if (population == 0)
			return (new Position(1,0));
		else if (population == 1)
			return (new Position(7,0));
		else if (population == 2)
			return (new Position(1,8));
		else
			return (new Position(7,8));
		}

	
	@Override
	public String toString() {
		return "N";
	}

	@Override
	public ArrayList<Position> getAllowedCells(Table table) {
		// TODO Auto-generated method stub
		return null;
	}

}
