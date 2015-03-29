package game.pieces;

import game.Direction;
import game.Piece;
import game.Position;
import game.Table;

import java.util.ArrayList;

public class King extends Piece {

	private static int population = 0;

	public King(Table table) {
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
			return (new Position(4, 0));
		else
			return (new Position(4, 8));
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public ArrayList<Position> getAllowedCells(Table table, int playerId) {
		ArrayList<Position> positions = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			Position currentPos = this.getPos().getNextPos(
					Direction.values()[i]);

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
