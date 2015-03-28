package game.pieces;

import java.util.ArrayList;

import game.Direction;
import game.Piece;
import game.Position;
import game.Table;

public class Pawn extends Piece {

	private static int population = 0;

	public Pawn(Table table) {
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
		if (population < 9) {
			return (new Position(population, 2));
		} else {
			return (new Position(population - 9, 6));
		}
	}

	@Override
	public String toString() {
		return "P";
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
		}
		else{
			Position currentPos = this.getPos().getNextPos(
					Direction.values()[4]);

			try {
				if (table.isEmpty(currentPos)) {
					positions.add(currentPos);
				}
			} catch (Exception e) {
			}
		}
		return positions;
	}
}
