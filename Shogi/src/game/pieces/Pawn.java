package game.pieces;

import game.Direction;
import game.Game;
import game.Piece;
import game.Player;
import game.Position;
import game.Table;

import java.util.ArrayList;

public class Pawn extends Piece {

	private static int population = 0;

	public Pawn(Table table) {
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
	public ArrayList<Position> getAllowedCells(Game game,Player player) {
		ArrayList<Position> positions = new ArrayList<>();
		if (player.getPlayerId() == 1) {
			Position currentPos = this.getPos().getNextPos(
					Direction.values()[0]);

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
		else{
			Position currentPos = this.getPos().getNextPos(
					Direction.values()[4]);

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
