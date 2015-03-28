package game;

import java.util.ArrayList;

public class Game {
	private Player player1;
	private Player player2;
	private Table table;
	private ArrayList<Piece> komadi;
	private int turn;

	public Game() {
		table = new Table();
		player1 = new Player(table,1);
		player2 = new Player(table,2);
		komadi = new ArrayList<>();
		turn = 0;

	}

	public void showGame() {
		System.out.printf("%c[2J", 0x1B);
		if (turn % 2 == 0) {
			System.out.println("Player 1 turn:");
			table.print();
			doTurn();
		} else {
			System.out.println("Player 2 turn:");
			table.print();
		}
	}

	public void doTurn() {
		if (turn % 2 == 0)
			player1.move(turn, table);
		else
			player2.move(turn, table);
		turn++;
	}
}
