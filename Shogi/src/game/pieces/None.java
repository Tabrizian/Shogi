package game.pieces;

import java.util.ArrayList;

import javax.swing.JButton;

import game.Piece;
import game.Position;
import game.Table;

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
	public ArrayList<Position> getAllowedCells(Table table, int playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "-";
	}
	
	

}
