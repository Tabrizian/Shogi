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
	public boolean move(Position pos, Table table, Game game, Player player) {
		return table.swapTableCells(pos, this.getPos(), game, player)[0];
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
		if (upgraded) {
			return "P+";
		} else {
			return "P";
		}
	}

	@Override
	public ArrayList<Position> getAllowedCells(Game game, Player player) {
		ArrayList<Position> positions = new ArrayList<>();
		if (!upgraded) {
			if (player.getPlayerId() == 1) {
				Position currentPos = this.getPos().getNextPos(
						Direction.values()[0]);
				try {
					if (game.getPlayer2().getPieces()
							.contains(game.getTable().getTableCell(currentPos))
							|| game.getTable().isEmpty(currentPos)) {
						positions.add(new Position(currentPos));
					}
				} catch (Exception e) {
				}
			} else {
				Position currentPos = this.getPos().getNextPos(
						Direction.values()[4]);
				try {
					if (game.getPlayer1().getPieces()
							.contains(game.getTable().getTableCell(currentPos))
							|| game.getTable().isEmpty(currentPos)) {
						positions.add(new Position(currentPos));
					}
				} catch (Exception e) {
				}
			}
		} else {
			if (player == game.getPlayer1()) {
				for (int i = 0; i < 8; i++) {
					if (i != 3 && i != 5) {
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
						} catch (Exception e) {
						}
					}
				}
			} else {
				for (int i = 0; i < 8; i++) {
					if (i != 1 && i != 7) {
						Position currentPos = this.getPos().getNextPos(
								Direction.values()[i]);
						try {
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
		}
		return positions;
	}

	@Override
	public void chechForUpgrade(Player player) {
		if (player.getPlayerId() == 1) {
			if (getPos().getY() >= 6)
				upgraded = true;
		} else {
			if (getPos().getY() <= 2)
				upgraded = true;
		}
	}

	@Override
	public void mustUpgrade(Player player) {
		if (player.getPlayerId() == 1) {
			if (getPos().getY() == 8)
				upgraded = true;
		} else {
			if (getPos().getY() == 0)
				upgraded = true;
		}
	}
}
