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
	public boolean move(Position pos, Table table, Game game, Player player) {
		return table.swapTableCells(pos, this.getPos(), game, player)[0];
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
		if (upgraded) {
			return "S+";
		} else {
			return "S";
		}
	}

	@Override
	public ArrayList<Position> getAllowedCells(Game game, Player player) {
		ArrayList<Position> positions = new ArrayList<>();
		if (!upgraded) {
			if (game.getPlayer1() == player) {
				for (int i = 0; i < 8; i++) {
					if (i != 2 && i != 4 && i != 6) {
						Position currentPos = this.getPos().getNextPos(
								Direction.values()[i]);
						try {
							if (game.getPlayer2()
									.getPieces()
									.contains(
											game.getTable().getTableCell(
													currentPos))
									|| game.getTable().isEmpty(currentPos)) {
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
							if (player == game.getPlayer1()) {
								if (game.getPlayer2()
										.getPieces()
										.contains(
												game.getTable().getTableCell(
														currentPos))
										|| game.getTable().isEmpty(currentPos)) {
									positions.add(new Position(currentPos));
								}
							}
							if (player == game.getPlayer2()) {
								if (game.getPlayer1()
										.getPieces()
										.contains(
												game.getTable().getTableCell(
														currentPos))
										|| game.getTable().isEmpty(currentPos)) {
									positions.add(new Position(currentPos));
								}
							}
						} catch (Exception e) {
						}
					}
				}
			}
		} else {
			for (int i = 0; i < 8; i++) {
				if (i != 1 && i != 7) {
					Position currentPos = this.getPos().getNextPos(
							Direction.values()[i]);
					try {
						if (player == game.getPlayer1()) {
							if (game.getPlayer2()
									.getPieces()
									.contains(
											game.getTable().getTableCell(
													currentPos))
									|| game.getTable().isEmpty(currentPos)) {
								positions.add(new Position(currentPos));
							}
						}
						if (player == game.getPlayer2()) {
							if (game.getPlayer1()
									.getPieces()
									.contains(
											game.getTable().getTableCell(
													currentPos))
									|| game.getTable().isEmpty(currentPos)) {
								positions.add(new Position(currentPos));
							}
						}
					} catch (Exception e) {
					}
				}
			}
		}
		return positions;
	}

	@Override
	public void chechForUpgrade(Player player) {
		if (player.getPlayerId() == 1) {
			if (getPos().getY() >= 6)
				upgraded = true;
		} else {
			if (getPos().getY() <= 3)
				upgraded = true;
		}
	}

	@Override
	public void mustUpgrade(Player player) {
		// TODO Auto-generated method stub

	}

}
