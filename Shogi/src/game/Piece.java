package game;

public abstract class Piece {

	private Position pos;

	public abstract void move();

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	protected abstract Position getDefaultPos();
	
	public abstract boolean isAllowdMoving(Position dst);
}
