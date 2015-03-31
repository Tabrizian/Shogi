package game.gui;

import game.Game;
import game.Piece;
import game.Player;
import game.Position;
import game.Table;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NormalButtonsListener implements ActionListener {
	private Position pos;
	private boolean pressed = false;
	private Player player2;
	private Player player1;
	private static int turn;
	private Table table;
	private ArrayList<Piece> pieces;
	private Game game;

	public NormalButtonsListener(Game game, Position pos) {
		this.player2 = game.getPlayer1();
		this.player1 = game.getPlayer2();
		NormalButtonsListener.turn = game.getTurn();
		this.pos = pos;
		this.table = game.getTable();
		this.game = game;
	}

	public void actionPerformed(ActionEvent arg0) {
		table.showMessage("");

		if (turn % 2 == 0) {
			if (!table.getPlayer1Komadi().isPressed()) {
				if (table.isKingCheck(game.getPlayer1(), game)) {
					table.showMessage("You're CHECK!");
				}
				if (table.getBoard().getButton(pos).getBackground() != Color.BLUE
						&& table.getBoard().getButton(pos).getBackground() != (Color.ORANGE)) {
					ArrayList<Position> positions;
					Piece piece = table.getTableCell(pos);
					pieces = player1.getPieces();

					if (pieces.contains(piece)) {
						positions = piece.getAllowedCells(game, player1);
						if (positions.size() > 0) {
							table.getBoard().getButton(pos)
									.setBackground(Color.ORANGE);
							for (int i = 0; i < positions.size(); i++) {
								table.getBoard().getButton(positions.get(i))
										.setBackground(Color.BLUE);
							}
						} else {
							table.showMessage("You can't move choose another piece!");
						}
					} else {
						table.showMessage("This is not your piece!");
					}
				} else if (table.getBoard().getButton(pos).getBackground() == (Color.ORANGE)) {

					Piece piece = table.getTableCell(table.getBoard()
							.findPressedButton());

					table.getBoard().getButton(piece.getPos())
							.setBackground(Table.ORIGINAL_COLOR);
					ArrayList<Position> positions;
					positions = piece.getAllowedCells(game, player1);
					if (positions.size() > 0) {
						for (int i = 0; i < positions.size(); i++) {
							table.getBoard().getButton(positions.get(i))
									.setBackground(Table.ORIGINAL_COLOR);
						}
					}
				} else {
					Piece movingPiece = table.getTableCell(pos);
					Piece piece = table.getTableCell(table.getBoard()
							.findPressedButton());
					boolean moved;
					table.getBoard().getButton(piece.getPos())
							.setBackground(Table.ORIGINAL_COLOR);
					ArrayList<Position> positions;
					positions = piece.getAllowedCells(game, player1);
					if (positions.size() > 0) {
						for (int i = 0; i < positions.size(); i++) {
							table.getBoard().getButton(positions.get(i))
									.setBackground(Table.ORIGINAL_COLOR);
						}
					}
					moved = piece.move(movingPiece.getPos(), table, game,
							game.getPlayer1());
					if (table.isKingCheck(game.getPlayer1(), game)) {
						table.showMessage("You're CHECK!");
					}
					if (moved)
						game.setTurn(++turn);
				}
			} else {

				table.setTableCell(pos, table.getPlayer1Komadi()
						.getPressedPiece());
				player1.getPieces().add(
						table.getPlayer1Komadi().getPressedPiece());
				table.getPlayer1Komadi().removeFromPanel(
						table.getPlayer1Komadi().getPressedPiece());
				player1.getKomadi().remove(
						table.getPlayer1Komadi().getPressedPiece());
				table.getPlayer1Komadi().setPressed(false);
				game.setTurn(++turn);

			}

			// Sare gardane ...
		} else {
			if (!table.getPlayer2Komadi().isPressed()) {
				if (table.isKingCheck(game.getPlayer2(), game)) {
					table.showMessage("You're CHECK!");
				}
				if (table.getBoard().getButton(pos).getBackground() != Color.BLUE
						&& table.getBoard().getButton(pos).getBackground() != (Color.ORANGE)) {
					ArrayList<Position> positions;
					Piece piece = table.getTableCell(pos);
					pieces = player2.getPieces();
					if (pieces.contains(piece)) {
						positions = piece.getAllowedCells(game, player2);
						if (positions.size() > 0) {
							table.getBoard().getButton(pos)
									.setBackground(Color.ORANGE);
							for (int i = 0; i < positions.size(); i++) {
								table.getBoard().getButton(positions.get(i))
										.setBackground(Color.BLUE);
							}
						} else {
							table.showMessage("You can't move choose another piece!");
						}
					} else {
						table.showMessage("This is not your piece!");
					}

				} else if (table.getBoard().getButton(pos).getBackground() == (Color.ORANGE)) {

					Piece piece = table.getTableCell(pos);

					table.getBoard().getButton(piece.getPos())
							.setBackground(Table.ORIGINAL_COLOR);
					ArrayList<Position> positions;
					positions = piece.getAllowedCells(game, player2);
					if (positions.size() > 0) {
						for (int i = 0; i < positions.size(); i++) {
							table.getBoard().getButton(positions.get(i))
									.setBackground(Table.ORIGINAL_COLOR);
						}
					}

				} else {
					boolean moved;
					Piece movingPiece = table.getTableCell(pos);
					Piece piece = table.getTableCell(table.getBoard()
							.findPressedButton());

					table.getBoard().getButton(piece.getPos())
							.setBackground(Table.ORIGINAL_COLOR);
					ArrayList<Position> positions;
					positions = piece.getAllowedCells(game, player2);
					if (positions.size() > 0) {
						for (int i = 0; i < positions.size(); i++) {
							table.getBoard().getButton(positions.get(i))
									.setBackground(Table.ORIGINAL_COLOR);
						}
					}
					moved = piece.move(movingPiece.getPos(), table, game,
							game.getPlayer2());
					if (table.isKingCheck(game.getPlayer2(), game)) {
						table.showMessage("You're CHECK!");
					}
					if (moved)
						game.setTurn(++turn);
				}
			} else {

				table.setTableCell(pos, table.getPlayer2Komadi()
						.getPressedPiece());
				player2.getPieces().add(
						table.getPlayer2Komadi().getPressedPiece());
				table.getPlayer2Komadi().removeFromPanel(
						table.getPlayer2Komadi().getPressedPiece());
				player2.getKomadi().remove(
						table.getPlayer2Komadi().getPressedPiece());
				table.getPlayer2Komadi().setPressed(false);
				game.setTurn(++turn);
			}

		}

	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

}
