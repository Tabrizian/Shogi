package game;

public class Event {
	private Direction[] directions;
	private Piece piece;
	public Event(Direction[] directions ,Piece piece){
		
		for (int i = 0; i < directions.length; i++) {
			this.directions[i] = directions[i];
		}
		
		this.piece = piece;
		
	}
}
