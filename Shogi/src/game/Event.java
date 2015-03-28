package game;

public class Event {
	private Direction[] directions;
	private String nameOfObject;
	public Event(Direction[] directions ,String name){
		
		for (int i = 0; i < directions.length; i++) {
			this.directions[i] = directions[i];
		}
		
		nameOfObject = name;
		
	}
}
