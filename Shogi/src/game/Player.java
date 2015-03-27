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

	public Player(Table table) {
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
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
	
	public Piece getPiece(Position pos){
		for (int i = 0; i < pieces.size(); i++) {
			if(pieces.get(i).getPos().equals(pos)){
				return pieces.get(i);
			}
		}
		return null;
	}

}
