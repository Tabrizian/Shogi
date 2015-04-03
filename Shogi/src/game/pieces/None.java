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
	public boolean move(Position pos,Table table,Game game,Player player) {
		return false;
		
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
	
	@Override
	public void chechForUpgrade(Player player) {
		
	}
	

}
