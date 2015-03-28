package game.pieces;

import java.util.ArrayList;

import game.Direction;
import game.Piece;
import game.Position;
import game.Table;

public class Rook extends Piece {

	private static int population = 0;

	public Rook(Table table) {
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
			return (new Position(7, 1));
		else
			return (new Position(1, 7));
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public ArrayList<Position> getAllowedCells(Table table, int playerId) {
		ArrayList<Position> positions = new ArrayList<>();
		try {
			for (Position current = this.getPos().getNextPos(Direction.EAST); table
					.isEmpty(current); current = current.getNextPos(Direction.EAST)) {
				positions.add(current);
			}
		} catch (Exception e) {
		}
		try {
			for (Position current = this.getPos().getNextPos(Direction.WEST); table
					.isEmpty(current); current = current.getNextPos(Direction.WEST)) {
				positions.add(current);
			}
		} catch (Exception e) {
		}
		try {
			for (Position current = this.getPos().getNextPos(Direction.NORTH); table
					.isEmpty(current); current = current.getNextPos(Direction.NORTH)) {
				positions.add(current);
			}
		} catch (Exception e) {
		}
		try {
			for (Position current = this.getPos().getNextPos(Direction.SOUTH); table
					.isEmpty(current); current = current.getNextPos(Direction.SOUTH)) {
				positions.add(current);
			}
		} catch (Exception e) {
		}
		return positions;
	}
}
