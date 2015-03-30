package game;

import game.pieces.Bishop;
import game.pieces.GoldGeneral;
import game.pieces.King;
import game.pieces.Knight;
import game.pieces.Lance;
import game.pieces.Pawn;
import game.pieces.Rook;
import game.pieces.SilverGeneral;

import java.util.ArrayList;

public class Player {
	private ArrayList<Piece> pieces;
	private int playerId;

	public Player(Table table, int playerId) {
		pieces = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			pieces.add(new Pawn(table));
		}
		pieces.add(new King(table));
		pieces.add(new Rook(table));
		pieces.add(new SilverGeneral(table));
		pieces.add(new SilverGeneral(table));
		pieces.add(new GoldGeneral(table));
		pieces.add(new GoldGeneral(table));
		pieces.add(new Lance(table));
		pieces.add(new Lance(table));
		pieces.add(new Knight(table));
		pieces.add(new Knight(table));
		pieces.add(new Bishop(table));
		this.playerId = playerId;
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}

//	public void move(int turn, Table table) {
//		Piece piece;
//		Piece movingPiece;
//		ArrayList<Position> positions;
//		if (turn % 2 == 0) {
//			piece = table.blinkCell(new Position(0, 0));
//			if (pieces.contains(piece)) {
//				positions = piece.getAllowedCells(table, playerId);
//				if (positions.size() > 0) {
//					Print.printSpecial(positions, Print.RED, table);
//					movingPiece = table.blinkCell(
//							new Position(positions.get(0)), positions);
//					if (positions.contains(movingPiece.getPos())) {
//						piece.move(movingPiece.getPos(), table);
//					}
//
//					while (positions.size() != 0)
//						positions.remove(0);
//				} else {
//					Print.printMessege("There is no where to go from here!");
//				}
//				movingPiece = null;
//			} else {
//				Print.printMessege("This is not you're piece!");
//			}
//
//		} else {
//			piece = table.blinkCell(new Position(8, 8));
//			if (pieces.contains(piece)) {
//				positions = piece.getAllowedCells(table, playerId);
//				if (positions.size() > 0) {
//					Print.printSpecial((positions), Print.RED, table);
//					movingPiece = table.blinkCell(
//							new Position(positions.get(0)), positions);
//					if (positions.contains(movingPiece.getPos())) {
//						piece.move(movingPiece.getPos(), table);
//					}
//					movingPiece = null;
//
//					while (positions.size() != 0)
//						positions.remove(0);
//				} else {
//					Print.printMessege("There is no where to go from here!");
//				}
//			} else {
//				Print.printMessege("This is not you're piece!");
//			}
//		}
//
//	}
	
	public void moveGUI(Table table,int turn){
		Piece piece;
		Piece movingPiece;
		ArrayList<Position> positions;
		
	}
	
	public int getPlayerId() {
		return playerId;
	}

}
