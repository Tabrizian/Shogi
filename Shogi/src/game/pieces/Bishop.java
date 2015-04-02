package game.pieces;

import game.Direction;
import game.Game;
import game.Piece;
import game.Player;
import game.Position;
import game.Table;

import java.util.ArrayList;

public class Bishop extends Piece {

	private static int population = 0;

	public Bishop(Table table) {
		Position defaultPos = getDefaultPos();
		setPos(defaultPos);
		table.setTableCell(defaultPos, this);
		population++;
	}

	@Override
	public boolean move(Position pos, Table table, Game game, Player player) {
		return table.swapTableCells(pos, this.getPos(), game, player)[0];
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
		if (!upgraded)
			return "B";
		else
			return "B+";
	}

	@Override
	public ArrayList<Position> getAllowedCells(Game game, Player player) {
		ArrayList<Position> positions = new ArrayList<>();
		try {
			Position current;
			for (current = this.getPos().getNextPos(Direction.NORTH_EAST); game
					.getTable().isEmpty(current); current = current
					.getNextPos(Direction.NORTH_EAST)) {
				positions.add(new Position(current));
			}
			if (!player.getPieces().contains(
					game.getTable().getTableCell(current))) {
				positions.add(current);
			}
		} catch (Exception e) {
		}
		try {
			Position current;
			for (current = this.getPos().getNextPos(Direction.NORTH_WEST); game
					.getTable().isEmpty(current); current = current
					.getNextPos(Direction.NORTH_WEST)) {
				positions.add(new Position(current));
			}
			if (!player.getPieces().contains(
					game.getTable().getTableCell(current))) {
				positions.add(current);
			}
		} catch (Exception e) {
		}
		try {
			Position current;
			for (current = this.getPos().getNextPos(Direction.SOUTH_EAST); game
					.getTable().isEmpty(current); current = current
					.getNextPos(Direction.SOUTH_EAST)) {
				positions.add(new Position(current));
			}
			if (!player.getPieces().contains(
					game.getTable().getTableCell(current))) {
				positions.add(current);
			}
		} catch (Exception e) {
		}
		try {
			Position current;
			for (current = this.getPos().getNextPos(Direction.SOUTH_WEST); game
					.getTable().isEmpty(current); current = current
					.getNextPos(Direction.SOUTH_WEST)) {
				positions.add(new Position(current));
			}
			if (!player.getPieces().contains(
					game.getTable().getTableCell(current))) {
				positions.add(current);
			}
		} catch (Exception e) {
		}
		try {
			for (int i = 0; i < 8; i += 2) {
				Position currentPos = this.getPos().getNextPos(
						Direction.values()[i]);
				try {
					if (player.getPieces().contains(
							game.getTable().getTableCell(currentPos))
							|| game.getTable().isEmpty(currentPos)) {
						positions.add(new Position(currentPos));
					}
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}
		return positions;
	}
}
