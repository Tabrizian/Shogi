package game.pieces;
import game.Piece;
import game.Position;
import game.Table;

public class Rook extends Piece {
	
	private static int population=0;
	
	public Rook(Table table) {
		Position defaultPos = getDefaultPos();
		setPos(defaultPos);
		table.setTableCell(defaultPos, 'R');
		population++;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
	}

	@Override
	protected Position getDefaultPos() {
		if (population == 0)
			return (new Position(7, 1));
		else
			return (new Position(1, 7));
	}

	@Override
	public boolean isAllowdMoving(Position dst) {
		// TODO Auto-generated method stub
		return false;
	}

}
