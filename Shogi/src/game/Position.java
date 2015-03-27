package game;

public class Position {

	private int xPos;
	private int yPos;

	public Position(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getX() {
		return xPos;
	}

	public void setX(int xPos) {
		this.xPos = xPos;
	}

	public int getY() {
		return yPos;
	}

	public void setY(int yPos) {
		this.yPos = yPos;
	}
	
	@Override
	public boolean equals(Object pos) {
		Position newPos = (Position) pos;
		if(newPos.xPos == this.xPos && newPos.yPos==this.yPos){
			return true;
		}
		return false;
	}
	
	
}
