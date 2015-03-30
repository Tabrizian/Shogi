package game;

import java.util.ArrayList;

import javax.swing.JButton;

public class Game {
	private Player player1;

	private Player player2;
	private Table table;
	private ArrayList<Piece> komadi;
	
	private int turn;

	public int getTurn() {
		return turn;
	}

	public Game() {
		table = new Table();
		player1 = new Player(table,1);
		player2 = new Player(table,2);
		komadi = new ArrayList<>();
		turn = 0;

	}

	public void showGame() {
		table.showGUI();
//		Print.clearScreen();
//		if (turn % 2 == 0) {
//			Print.printLine("Player 1 turn:");
//			table.print();
//			table.showGUI();
//		} else {
//			Print.printLine("Player 2 turn:");
//			table.print();
//			table.showGUI();
//		}
	}

//	public void doTurn() {
//		if (turn % 2 == 0)
//			player1.move(turn, table);
//		else
//			player2.move(turn, table);
//		turn++;
//	}
	
	public void run(){
		JButton[][] buttons = table.getBoard().getButtons();
		for (int k = 0; k < buttons.length; k++) {
			for (int k2 = 0; k2 < buttons[0].length; k2++) {
				buttons[k][k2].addActionListener(new MyActionListener(this,new Position(k2,k)));
			}
		}
		showGame();
//		while(true){
//			showGame();
//			doTurn();
//		}
	}
	
	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	public ArrayList<Piece> getKomadi() {
		return komadi;
	}
	
	public Table getTable() {
		return table;
	}

}
