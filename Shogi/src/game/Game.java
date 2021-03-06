package game;

import game.gui.NormalButtonsListener;

import javax.swing.JButton;

public class Game {
	private Player player1;

	private Player player2;
	private Table table;
	
	private int turn;

	public Game() {
		table = new Table(this);
		player1 = new Player(table,1);
		player2 = new Player(table,2);
		
		table.getPlayer1Komadi().addName(player1);
		table.getPlayer2Komadi().addName(player2);
		turn = 0;

	}

	public void showGame() {
		table.showGUI();
	}
	
	public void run(){
		JButton[][] buttons = table.getBoard().getButtons();
		for (int k = 0; k < buttons.length; k++) {
			for (int k2 = 0; k2 < buttons[0].length; k2++) {
				buttons[k][k2].addMouseListener(new NormalButtonsListener(this,new Position(k2,k)));
			}
		}
		
		showGame();
	}
	
	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	public Table getTable() {
		return table;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void setTurn(int turn){
		this.turn = turn;
	}

}
