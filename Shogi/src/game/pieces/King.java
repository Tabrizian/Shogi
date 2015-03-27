package game.pieces;
import game.Piece;
import game.Position;
import game.Table;

public class King extends Piece {
	
	private static int population=0;
	
	public King(Table table) {
		Position defaultPos = getDefaultPos();
		setPos(defaultPos);
		table.setTableCell(defaultPos, 'K');
		population++;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
	}

	@Override
	protected Position getDefaultPos() {
		if (population == 0)
			return (new Position(4, 0));
		else
			return (new Position(4, 8));
	}

	@Override
	public boolean isAllowdMoving(Position dst) {
		// TODO Auto-generated method stub
		return false;
	}
}
