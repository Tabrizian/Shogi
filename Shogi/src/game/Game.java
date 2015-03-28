package game;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Game {
	private Player player1;
	private Player player2;
	private Table table;
	private ArrayList<Piece> komadi;
	private int turn;

	public Game() {
		table = new Table();
		player1 = new Player(table);
		player2 = new Player(table);
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
		turn++;
		if (turn % 2 == 0)
			player1.move(turn, table);
		else
			player2.move(turn, table);
	}
}
