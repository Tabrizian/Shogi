package game.pieces;

import java.util.ArrayList;

import javax.swing.JButton;

import game.Direction;
import game.Game;
import game.Piece;
import game.Player;
import game.Position;
import game.Table;

public class GoldGeneral extends Piece {

	private static int population = 0;

	public GoldGeneral(Table table) {
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
			return (new Position(3, 0));
		else if (population == 1)
			return (new Position(5, 0));
		else if (population == 2)
			return (new Position(3, 8));
		else
			return (new Position(5, 8));
	}

	@Override
	public String toString() {
		return "G";
	}

	@Override
	public ArrayList<Position> getAllowedCells(Game game,Player player) {
		if (player.getPlayerId() == 1) {
			ArrayList<Position> positions = new ArrayList<>();
			for (int i = 0; i < 8; i++) {
				if (i != 3 && i != 5) {
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
			return positions;
		}
		else{
			ArrayList<Position> positions = new ArrayList<>();
			for (int i = 0; i < 8; i++) {
				if (i != 1 && i != 7) {
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
			return positions;
		}
	}

}
