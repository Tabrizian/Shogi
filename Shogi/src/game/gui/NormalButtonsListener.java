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
		this.player1 = game.getPlayer1();
		this.player2 = game.getPlayer2();
		NormalButtonsListener.turn = game.getTurn();
		this.pos = pos;
		this.table = game.getTable();
		this.game = game;
	}

	public void actionPerformed(ActionEvent arg0) {
		table.showMessage("");
		if (game.getTurn() % 2 == 0) {
			if(table.isKingMate(game.getPlayer1(), game)){
				table.showMessage("You're CHECKMATE!");
				return;
			}
			if (table.isKingCheck(game.getPlayer1(), game)) {
				table.showMessage("You're CHECK!");
			}
			if (!table.getPlayer1Komadi().isPressed()) {
				if (table.getBoard().getButton(pos).getBackground() != Color.BLUE
						&& table.getBoard().getButton(pos).getBackground() != (Color.ORANGE)
						&& !table.isACellSelected()) {
					ArrayList<Position> positions;
					Piece piece = table.getTableCell(pos);
					pieces = game.getPlayer1().getPieces();

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
				} else if (table.getBoard().getButton(pos).getBackground() == Color.BLUE) {
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
					if (table.isKingCheck(game.getPlayer2(), game)) {
						table.showMessage("You're CHECK!");
					}
					if (moved)
						game.setTurn(++turn);
				}
			} else if(table.getPlayer1Komadi().isPressed() && !table.isACellSelected()){
			
				
					if (table.isEmpty(pos)) {
						boolean setted;
						setted = table.setTableKomadiCell(pos, table.getPlayer1Komadi()
								.getPressedPiece());
						if (setted) {
						table.setTableCell(pos, table.getPlayer1Komadi()
								.getPressedPiece());
						game.getPlayer1()
								.getPieces()
								.add(table.getPlayer1Komadi().getPressedPiece());
						table.getPlayer1Komadi().removeFromPanel(
								table.getPlayer1Komadi().getPressedPiece());
						game.getPlayer1()
								.getKomadi()
								.remove(table.getPlayer1Komadi()
										.getPressedPiece());
						table.getPlayer1Komadi().setPressed(false);
						game.setTurn(++turn);
					}
					if (table.isKingCheck(game.getPlayer2(), game)) {
						table.showMessage("You're CHECK!");
					}
				}

			}

			// Sare gardane ...
		} else {
			if(table.isKingMate(game.getPlayer2(), game)){
				table.showMessage("You're CHECKMATE!");
				return;
			}
			if (table.isKingCheck(game.getPlayer2(), game)) {
				table.showMessage("You're CHECK!");
			}
			if (!table.getPlayer2Komadi().isPressed()) {
				if (table.getBoard().getButton(pos).getBackground() != Color.BLUE
						&& table.getBoard().getButton(pos).getBackground() != (Color.ORANGE)
						&& !table.isACellSelected()) {
					ArrayList<Position> positions;
					Piece piece = table.getTableCell(pos);
					pieces = game.getPlayer2().getPieces();
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

				} else if (table.getBoard().getButton(pos).getBackground() == Color.BLUE) {
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
					if (table.isKingCheck(game.getPlayer1(), game)) {
						table.showMessage("You're CHECK!");
					}
					if (moved)
						game.setTurn(++turn);
				}
			} else if(table.getPlayer2Komadi().isPressed() && !table.isACellSelected()) {
				if (table.isEmpty(pos)) {
					boolean setted;
					setted = table.setTableKomadiCell(pos, table
							.getPlayer2Komadi().getPressedPiece());
					if (setted) {
						game.getPlayer2()
								.getPieces()
								.add(table.getPlayer2Komadi().getPressedPiece());
						table.getPlayer2Komadi().removeFromPanel(
								table.getPlayer2Komadi().getPressedPiece());
						game.getPlayer2()
								.getKomadi()
								.remove(table.getPlayer2Komadi()
										.getPressedPiece());
						table.getPlayer2Komadi().setPressed(false);
						if (table.isKingCheck(game.getPlayer1(), game)) {
							table.showMessage("You're CHECK!");
						}
						game.setTurn(++turn);
					}
				}
			}
		}
		table.colorTable(game);
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
