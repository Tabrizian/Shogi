package game.pieces;

import java.util.ArrayList;

import game.Direction;
import game.Piece;
import game.Position;
import game.Table;

public class Lance extends Piece {

	private static int population = 0;

	public Lance(Table table) {
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
			return (new Position(0, 0));
		else if (population == 1)
			return (new Position(8, 0));
		else if (population == 2)
			return (new Position(0, 8));
		else
			return (new Position(8, 8));
	}

	@Override
	public String toString() {
		return "L";
	}

	@Override
	public ArrayList<Position> getAllowedCells(Table table, int playerId) {
		ArrayList<Position> positions = new ArrayList<>();
		if (playerId == 1) {
			Position currentPos = this.getPos().getNextPos(
					Direction.values()[0]);

			try {
				if (table.isEmpty(currentPos)) {
					positions.add(currentPos);
				}
			} catch (Exception e) {
			}
			currentPos = this.getPos().getNextPos(Direction.values()[0])
					.getNextPos(Direction.values()[0]);

			try {
				if (table.isEmpty(currentPos)) {
					positions.add(currentPos);
				}
			} catch (Exception e) {
			}
		} else {
			Position currentPos = this.getPos().getNextPos(
					Direction.values()[4]);

			try {
				if (table.isEmpty(currentPos)) {
					positions.add(currentPos);
				}
			} catch (Exception e) {
			}
			currentPos = this.getPos().getNextPos(Direction.values()[4])
					.getNextPos(Direction.values()[4]);

			try {
				if (table.isEmpty(currentPos)) {
					positions.add(new Position(currentPos));
				}
			} catch (Exception e) {
			}
		}
		return positions;
	}

}
