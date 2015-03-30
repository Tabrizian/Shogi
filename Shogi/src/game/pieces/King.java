package game.pieces;

import game.Direction;
import game.Game;
import game.Piece;
import game.Player;
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
	public boolean move(Position pos, Table table,Game game,Player player) {
		return table.swapTableCells(pos, this.getPos(),game,player);
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
	public ArrayList<Position> getAllowedCells(Game game,Player player) {
		ArrayList<Position> positions = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			Position currentPos = this.getPos().getNextPos(
					Direction.values()[i]);

			try {
				if (player == game.getPlayer1()) {
					if (game.getPlayer2().getPieces().contains(game.getTable().getTableCell(currentPos))||game.getTable().isEmpty(currentPos)) {
						positions.add(new Position(currentPos));
					}
				}
				if (player == game.getPlayer2()) {
					if (game.getPlayer1().getPieces().contains(game.getTable().getTableCell(currentPos))||game.getTable().isEmpty(currentPos)) {
						positions.add(new Position(currentPos));
					}
				}
			} catch (Exception e) {
			}
		}
		return positions;
	}

}
