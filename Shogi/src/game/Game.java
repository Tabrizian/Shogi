package game;

public class Game {
	private Player player1;
	private Player player2;
	private Table table;

	public Game() {
		table = new Table();
		player1 = new Player(table);
		player2 = new Player(table);
	}

	public void showGame() {
		table.print();
	}
}
