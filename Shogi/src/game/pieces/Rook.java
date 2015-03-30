package game.pieces;

import game.Direction;
import game.Game;
import game.Piece;
import game.Player;
import game.Position;
import game.Table;

import java.util.ArrayList;

public class Rook extends Piece {

	private static int population = 0;

	public Rook(Table table) {
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
			return (new Position(7, 1));
		else
			return (new Position(1, 7));
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public ArrayList<Position> getAllowedCells(Game game, Player player) {
		ArrayList<Position> positions = new ArrayList<>();
		try {
			Position current;
			for (current = this.getPos().getNextPos(Direction.EAST); game.getTable()
					.isEmpty(current); current = current.getNextPos(Direction.EAST)) {
				positions.add(current);
			}
			if(!player.getPieces().contains(game.getTable().getTableCell(current))){
				positions.add(current);
			}
		} catch (Exception e) {
		}
		try {
			Position current;
			for (current = this.getPos().getNextPos(Direction.WEST); game.getTable()
					.isEmpty(current); current = current.getNextPos(Direction.WEST)) {
				positions.add(new Position(current));
			}
			if(!player.getPieces().contains(game.getTable().getTableCell(current))){
				positions.add(current);
			}
		} catch (Exception e) {
		}
		try {
			Position current;
			for (current = this.getPos().getNextPos(Direction.NORTH); game.getTable()
					.isEmpty(current); current = current.getNextPos(Direction.NORTH)) {
				positions.add(new Position(current));
			}
			if(!player.getPieces().contains(game.getTable().getTableCell(current))){
				positions.add(current);
			}
		} catch (Exception e) {
		}
		try {
			Position current;
			for (current = this.getPos().getNextPos(Direction.SOUTH); game.getTable()
					.isEmpty(current); current = current.getNextPos(Direction.SOUTH)) {
				positions.add(new Position(current));
			}
			if(!player.getPieces().contains(game.getTable().getTableCell(current))){
				positions.add(current);
			}
		} catch (Exception e) {
		}
		return positions;
	}
}
