package game.pieces;

import game.Direction;
import game.Game;
import game.Piece;
import game.Player;
import game.Position;
import game.Table;

import java.util.ArrayList;

public class GoldGeneral extends Piece {

	private static int population = 0;

	public GoldGeneral(Table table) {
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
	public ArrayList<Position> getAllowedCells(Game game, Player player) {
		if (player == game.getPlayer1()) {
			ArrayList<Position> positions = new ArrayList<>();
			for (int i = 0; i < 8; i++) {
				if (i != 3 && i != 5) {
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
			}
			return positions;
		} else {
			ArrayList<Position> positions = new ArrayList<>();
			for (int i = 0; i < 8; i++) {
				if (i != 1 && i != 7) {
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
			}
			return positions;
		}
	}
	@Override
	public void chechForUpgrade(Player player) {
		
	}

	@Override
	public void mustUpgrade(Player player) {
		// TODO Auto-generated method stub
		
	}

}
