package game.pieces;

import game.Direction;
import game.Game;
import game.Piece;
import game.Player;
import game.Position;
import game.Table;

import java.util.ArrayList;

public class Lance extends Piece {

	private static int population = 0;

	public Lance(Table table) {
		Position defaultPos = getDefaultPos();
		setPos(defaultPos);
		table.setTableCell(defaultPos, this);
		population++;
	}

	@Override
	public boolean move(Position pos, Table table, Game game,Player player) {
		return table.swapTableCells(pos, this.getPos(), game,player)[0];
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
	public ArrayList<Position> getAllowedCells(Game game,Player player) {
		ArrayList<Position> positions = new ArrayList<>();
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
