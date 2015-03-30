package game.pieces;

import game.Direction;
import game.Game;
import game.Piece;
import game.Player;
import game.Position;
import game.Table;

import java.util.ArrayList;

public class SilverGeneral extends Piece {

	private static int population = 0;

	public SilverGeneral(Table table) {
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
			return (new Position(2, 0));
		else if (population == 1)
			return (new Position(6, 0));
		else if (population == 2)
			return (new Position(2, 8));
		else
			return (new Position(6, 8));
	}

	@Override
	public String toString() {
		return "S";
	}

	@Override
	public ArrayList<Position> getAllowedCells(Game game,Player player) {
		ArrayList<Position> positions = new ArrayList<>();
		if (player.getPlayerId() == 1) {
			for (int i = 0; i < 8; i++) {
				if (i != 2 && i != 4 && i != 6) {
					Position currentPos = this.getPos().getNextPos(
							Direction.values()[i]);
					try {
						if (game.getTable().isEmpty(currentPos)) {
							positions.add(new Position(currentPos));
						}
					} catch (Exception e) {
					}
				}
			}
		}

		else {
			for (int i = 0; i < 8; i++) {
				if (i != 2 && i != 0 && i != 6) {
					Position currentPos = this.getPos().getNextPos(
							Direction.values()[i]);
					try {
						if (game.getTable().isEmpty(currentPos)) {
							positions.add(new Position(currentPos));
						}
					} catch (Exception e) {
					}
				}
			}
		}
		return positions;
	}
	
}
