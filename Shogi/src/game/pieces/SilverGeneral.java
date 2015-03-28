package game.pieces;
import java.util.ArrayList;

import game.Piece;
import game.Position;
import game.Table;


public class SilverGeneral extends Piece{
	
	private static int population=0;
	
	public SilverGeneral(Table table){
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
			return (new Position(2,0));
		else if (population == 1)
			return (new Position(6,0));
		else if (population == 2)
			return (new Position(2,8));
		else
			return (new Position(6,8));
	}
	
	@Override
	public String toString() {
		return "S";
	}

	@Override
	public ArrayList<Position> getAllowedCells(Table table) {
		// TODO Auto-generated method stub
		return null;
	}

}
