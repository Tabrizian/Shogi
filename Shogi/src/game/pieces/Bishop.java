package game.pieces;

import java.util.ArrayList;

import game.Direction;
import game.Piece;
import game.Position;
import game.Table;

public class Bishop extends Piece {

	private static int population = 0;

	public Bishop(Table table) {
		Position defaultPos = getDefaultPos();
		setPos(defaultPos);
		table.setTableCell(defaultPos, this);
		population++;
	}

	@Override
	public void move(Position pos, Table table) {
		table.swapTableCells(pos, this.getPos());
	}
	
	@Override
	protected Position getDefaultPos() {
		if (population == 0)
			return (new Position(1, 1));
		else
			return (new Position(7, 7));
	}
	
	@Override
	public String toString() {
		return "B";
	}

	@Override
	public ArrayList<Position> getAllowedCells(Table table,int playerId) {
		ArrayList<Position> positions = new ArrayList<>();
		try {
			for (Position current = this.getPos().getNextPos(Direction.NORTH_EAST); table
					.isEmpty(current); current = current.getNextPos(Direction.NORTH_EAST)) {
				positions.add(new Position(current));
			}
		} catch (Exception e) {
		}
		try {
			for (Position current = this.getPos().getNextPos(Direction.NORTH_WEST); table
					.isEmpty(current); current = current.getNextPos(Direction.NORTH_WEST)) {
				positions.add(new Position(current));
			}
		} catch (Exception e) {
		}
		try {
			for (Position current = this.getPos().getNextPos(Direction.SOUTH_EAST); table
					.isEmpty(current); current = current.getNextPos(Direction.SOUTH_EAST)) {
				positions.add(new Position(current));
			}
		} catch (Exception e) {
		}
		try {
			for (Position current = this.getPos().getNextPos(Direction.SOUTH_WEST); table
					.isEmpty(current); current = current.getNextPos(Direction.SOUTH_WEST)) {
				positions.add(new Position(current));
			}
		} catch (Exception e) {
		}
		return positions;
	}
}
