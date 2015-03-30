package game.pieces;

import game.Game;
import game.Piece;
import game.Player;
import game.Position;
import game.Table;

import java.util.ArrayList;

public class None extends Piece{
	
	public None(Table table,Position pos){
		this.setPos(pos);
		table.setTableCell(pos, this);
	}
	@Override
	public void move(Position pos,Table table) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Position getDefaultPos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Position> getAllowedCells(Game game,Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "-";
	}
	
	

}
