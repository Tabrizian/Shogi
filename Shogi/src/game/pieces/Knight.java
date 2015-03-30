package game.pieces;

import game.Direction;
import game.Game;
import game.Piece;
import game.Player;
import game.Position;
import game.Table;

import java.util.ArrayList;

public class Knight extends Piece {

	private static int population = 0;

	public Knight(Table table) {
		Position defaultPos = getDefaultPos();
		setPos(defaultPos);
		table.setTableCell(defaultPos, this);
		population++;
	}

	@Override
	public void move(Position pos, Table table,Game game) {
		table.swapTableCells(pos, this.getPos(),game);
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
	public ArrayList<Position> getAllowedCells(Game game,Player player) {
		ArrayList<Position> positions = new ArrayList<>();
		if (player.getPlayerId() == 1) {
			
			Position current;
			try {
				current = this.getPos().getNextPos(Direction.NORTH_EAST).getNextPos(Direction.NORTH);
				if (player == game.getPlayer1()) {
					if (game.getPlayer2().getPieces().contains(game.getTable().getTableCell(current))||game.getTable().isEmpty(current)) {
						positions.add(new Position(current));
					}
				}
				if (player == game.getPlayer2()) {
					if (game.getPlayer1().getPieces().contains(game.getTable().getTableCell(current))||game.getTable().isEmpty(current)) {
						positions.add(new Position(current));
					}
				}
			} catch (Exception e) {
			}
			try {
				current = this.getPos().getNextPos(Direction.NORTH_WEST).getNextPos(Direction.NORTH);
				if (player == game.getPlayer1()) {
					if (game.getPlayer2().getPieces().contains(game.getTable().getTableCell(current))||game.getTable().isEmpty(current)) {
						positions.add(new Position(current));
					}
				}
				if (player == game.getPlayer2()) {
					if (game.getPlayer1().getPieces().contains(game.getTable().getTableCell(current))||game.getTable().isEmpty(current)) {
						positions.add(new Position(current));
					}
				}
			} catch (Exception e) {
			}
		} else {
			Position current;
			try {
				current = this.getPos().getNextPos(Direction.SOUTH_EAST).getNextPos(Direction.SOUTH);
				if (player == game.getPlayer1()) {
					if (game.getPlayer2().getPieces().contains(game.getTable().getTableCell(current))||game.getTable().isEmpty(current)) {
						positions.add(new Position(current));
					}
				}
				if (player == game.getPlayer2()) {
					if (game.getPlayer1().getPieces().contains(game.getTable().getTableCell(current))||game.getTable().isEmpty(current)) {
						positions.add(new Position(current));
					}
				}
			} catch (Exception e) {
			}
			try {
				current = this.getPos().getNextPos(Direction.SOUTH_WEST).getNextPos(Direction.SOUTH);
				if (player == game.getPlayer1()) {
					if (game.getPlayer2().getPieces().contains(game.getTable().getTableCell(current))||game.getTable().isEmpty(current)) {
						positions.add(new Position(current));
					}
				}
				if (player == game.getPlayer2()) {
					if (game.getPlayer1().getPieces().contains(game.getTable().getTableCell(current))||game.getTable().isEmpty(current)) {
						positions.add(new Position(current));
					}
				}
			} catch (Exception e) {
			}
		}
		return positions;
	}

}
