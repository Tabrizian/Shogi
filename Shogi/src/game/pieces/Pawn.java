package game.pieces;
import game.Piece;
import game.Position;
import game.Table;

public class Pawn extends Piece {
	
	private static int population=0;
	public Pawn(Table table) {
		Position defaultPos = getDefaultPos();
		setPos(defaultPos);
		table.setTableCell(defaultPos, 'P');
		population++;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
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
	public boolean isAllowdMoving(Position dst) {
		// TODO Auto-generated method stub
		return false;
	}

}
