package game.pieces;

import java.util.ArrayList;

import game.Direction;
import game.Piece;
import game.Position;
import game.Table;

public class Knight extends Piece {

	private static int population = 0;

	public Knight(Table table) {
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
			return (new Position(1, 0));
		else if (population == 1)
			return (new Position(7, 0));
		else if (population == 2)
			return (new Position(1, 8));
		else
			return (new Position(7, 8));
	}

	@Override
	public String toString() {
		return "N";
	}

	@Override
	public ArrayList<Position> getAllowedCells(Table table, int playerId) {
		ArrayList<Position> positions = new ArrayList<>();
		if (playerId == 1) {
			
			Position current;
			try {
				current = this.getPos().getNextPos(Direction.NORTH_EAST).getNextPos(Direction.NORTH);
				if(table.isEmpty(current)){
					positions.add(current);
				}
			} catch (Exception e) {
			}
			try {
				current = this.getPos().getNextPos(Direction.NORTH_WEST).getNextPos(Direction.NORTH);
				if(table.isEmpty(current)){
					positions.add(current);
				}
			} catch (Exception e) {
			}
		} else {
			Position current;
			try {
				current = this.getPos().getNextPos(Direction.SOUTH_EAST).getNextPos(Direction.SOUTH);
				if(table.isEmpty(current)){
					positions.add(current);
				}
			} catch (Exception e) {
			}
			try {
				current = this.getPos().getNextPos(Direction.SOUTH_WEST).getNextPos(Direction.SOUTH);
				if(table.isEmpty(current)){
					positions.add(current);
				}
			} catch (Exception e) {
			}
		}
		return positions;
	}

}
