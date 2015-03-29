package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class MyActionListener implements ActionListener {
	private Position pos;
	private boolean pressed = false;
	private Player player2;
	private Player player1;
	private static int turn;
	private Table table;
	private ArrayList<Piece> pieces;

	public MyActionListener(Player player1, Player player2, int turn,
			Table table, Position pos) {
		this.player2 = player2;
		this.player1 = player1;
		MyActionListener.turn = turn;
		this.pos = pos;
		this.table = table;
	}

	public void actionPerformed(ActionEvent arg0) {

		if (turn % 2 == 0) {
			if (table.getButton(pos).getBackground() != Color.BLUE && table.getButton(pos).getBackground() != (Color.ORANGE)) {
				ArrayList<Position> positions;
				Piece piece = table.getTableCell(pos);
				pieces = player1.getPieces();

				if (pieces.contains(piece)) {
					positions = piece.getAllowedCells(table,
							player1.getPlayerId());
					if (positions.size() > 0) {
						table.getButton(pos).setBackground(Color.ORANGE);
						for (int i = 0; i < positions.size(); i++) {
							table.getButton(positions.get(i)).setBackground(
									Color.BLUE);
						}
					}
				}
			} else if (table.getButton(pos).getBackground() == (Color.ORANGE)) {

				Piece piece = table.getTableCell(table.findPressedButton());

				table.getButton(piece.getPos()).setBackground(
						Table.ORIGINAL_COLOR);
				ArrayList<Position> positions;
				positions = piece.getAllowedCells(table, player1.getPlayerId());
				if (positions.size() > 0) {
					for (int i = 0; i < positions.size(); i++) {
						table.getButton(positions.get(i)).setBackground(
								Table.ORIGINAL_COLOR);
					}
				}
			} else {
				Piece movingPiece = table.getTableCell(pos);
				Piece piece = table.getTableCell(table.findPressedButton());

				table.getButton(piece.getPos()).setBackground(
						Table.ORIGINAL_COLOR);
				ArrayList<Position> positions;
				positions = piece.getAllowedCells(table, player1.getPlayerId());
				if (positions.size() > 0) {
					for (int i = 0; i < positions.size(); i++) {
						table.getButton(positions.get(i)).setBackground(
								Table.ORIGINAL_COLOR);
					}
				}
				piece.move(movingPiece.getPos(), table);
				turn++;
			}
		} else {
			if (table.getButton(pos).getBackground() != Color.BLUE &&table.getButton(pos).getBackground() != (Color.ORANGE)) {
				ArrayList<Position> positions;
				Piece piece = table.getTableCell(pos);
				pieces = player2.getPieces();
				if (pieces.contains(piece)) {
					positions = piece.getAllowedCells(table,
							player2.getPlayerId());
					if (positions.size() > 0) {
						table.getButton(pos).setBackground(Color.ORANGE);
						for (int i = 0; i < positions.size(); i++) {
							table.getButton(positions.get(i)).setBackground(
									Color.BLUE);
						}
					}
				}
			} else if (table.getButton(pos).getBackground() == (Color.ORANGE)) {

				Piece piece = table.getTableCell(table.findPressedButton());

				table.getButton(piece.getPos()).setBackground(
						Table.ORIGINAL_COLOR);
				ArrayList<Position> positions;
				positions = piece.getAllowedCells(table, player1.getPlayerId());
				if (positions.size() > 0) {
					for (int i = 0; i < positions.size(); i++) {
						table.getButton(positions.get(i)).setBackground(
								Table.ORIGINAL_COLOR);
					}
				}

			} else {
				Piece movingPiece = table.getTableCell(pos);
				Piece piece = table.getTableCell(table.findPressedButton());

				table.getButton(piece.getPos()).setBackground(
						Table.ORIGINAL_COLOR);
				ArrayList<Position> positions;
				positions = piece.getAllowedCells(table, player2.getPlayerId());
				if (positions.size() > 0) {
					for (int i = 0; i < positions.size(); i++) {
						table.getButton(positions.get(i)).setBackground(
								Table.ORIGINAL_COLOR);
					}
				}
				piece.move(movingPiece.getPos(), table);
				turn++;
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
