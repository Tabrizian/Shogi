package game;

import game.pieces.None;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Table extends JFrame {
	private Piece[][] table;
	// private JFrame jFrame;
	private BoardPanel board;
	JLabel label;

	public static final Color ORIGINAL_COLOR = (new JButton()).getBackground();
	// private static final int sleepTime = 250;
	LayoutManager borderLayout;

	public Table() {
		super("SHOGI");
		table = new Piece[9][9];
		borderLayout = new FlowLayout();
		board = new BoardPanel();
		label = new JLabel("");
		board.initialize(this);
		setLayout(borderLayout);
		add(board, BorderLayout.CENTER);
		add(label, BorderLayout.WEST);
	}

	public void setTableCell(Position pos, Piece type) {
		table[pos.getY()][pos.getX()] = type;
		board.setButtonText(pos);
	}

	public Piece getTableCell(Position pos) {
		return table[pos.getY()][pos.getX()];
	}

	public boolean swapTableCells(Position pos1, Position pos2, Game game,
			Player player) {
		if (getTableCell(pos1) instanceof None) {
			Piece temp;
			temp = getTableCell(pos1);
			setTableCell(pos1, getTableCell(pos2));
			setTableCell(pos2, temp);
			getTableCell(pos1).setPos(pos1);
			getTableCell(pos2).setPos(pos2);
			board.getButton(pos1).setText(getTableCell(pos1).toString());
			board.getButton(pos2).setText(getTableCell(pos2).toString());
		} else {
			game.getKomadi().add(getTableCell(pos1));
			if (game.getPlayer1().getPieces().contains(getTableCell(pos1)))
				game.getPlayer1().getPieces().remove(getTableCell(pos1));
			if (game.getPlayer2().getPieces().contains(getTableCell(pos1)))
				game.getPlayer2().getPieces().remove(getTableCell(pos1));
			setTableCell(pos1, getTableCell(pos2));
			Piece newPiece = new None(this, pos2);
			setTableCell(pos2, newPiece);
			getTableCell(pos1).setPos(pos1);
			getTableCell(pos2).setPos(pos2);
			board.getButton(pos1).setText(getTableCell(pos1).toString());
			board.getButton(pos2).setText(getTableCell(pos2).toString());
		}
		Player goodPlayer;
		if (player == game.getPlayer1())
			goodPlayer = game.getPlayer2();
		else
			goodPlayer = game.getPlayer1();

		if (!isKingCheck(goodPlayer, game)) {
			return true;
		} else {
			if (getTableCell(pos2) instanceof None) {
				Piece temp;
				temp = getTableCell(pos1);
				setTableCell(pos1, getTableCell(pos2));
				setTableCell(pos2, temp);
				getTableCell(pos1).setPos(pos1);
				getTableCell(pos2).setPos(pos2);
				board.getButton(pos1).setText(getTableCell(pos1).toString());
				board.getButton(pos2).setText(getTableCell(pos2).toString());
			} else {

				Piece deleted = game.getKomadi().get(
						game.getKomadi().size() - 1);
				game.getKomadi().remove(game.getKomadi().size() - 1);
				player.getPieces().add(deleted);
				setTableCell(pos1, getTableCell(pos2));
				setTableCell(pos2, deleted);
				getTableCell(pos1).setPos(pos1);
				getTableCell(pos2).setPos(pos2);
				board.getButton(pos1).setText(getTableCell(pos1).toString());
				board.getButton(pos2).setText(getTableCell(pos2).toString());
			}
			return false;
		}
	}

	public void showMessage(String s) {
		label.setText(s);
	}

	public boolean isKingCheck(Player player, Game game) {
		if (player == game.getPlayer2()) {
			for (int i = 0; i < game.getPlayer1().getPieces().size(); i++) {
				Piece piece = game.getPlayer1().getPieces().get(i);
				if (piece.getAllowedCells(game, game.getPlayer1()).contains(
						game.getPlayer2().getKing().getPos())) {
					return true;
				}
			}
			return false;
		} else {
			for (int i = 0; i < game.getPlayer2().getPieces().size(); i++) {
				Piece piece = game.getPlayer2().getPieces().get(i);
				if (piece.getAllowedCells(game, game.getPlayer2()).contains(
						game.getPlayer1().getKing().getPos())) {
					return true;
				}
			}
			return false;
		}
	}

	// public void print() {
	// for (int i = 8; i >= 0; i--) {
	// for (int j = 0; j < table.length; j++) {
	// Print.printSimply((table[i][j].toString() + " "));
	// }
	// Print.printLine("");
	// }
	// }

	// public Piece blinkCell(Position pos) {
	// KeyListener listener = new MyKeyListener(pos, this);
	// while (true) {
	// jFrame.setVisible(true);
	// jFrame.requestFocus();
	// jFrame.addKeyListener(listener);
	// Print.printAtPosition(pos);
	// Print.printSimply(" ");
	// try {
	// Thread.sleep(sleepTime);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// Print.printAtPosition(pos);
	// Print.printSimply(getTableCell(pos).toString());
	// try {
	// Thread.sleep(sleepTime);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// if (((MyKeyListener) listener).isPressedEnter()) {
	// break;
	// }
	// }
	// return table[pos.getY()][pos.getX()];
	// }

	// public Piece blinkCell(Position pos, ArrayList<Position> specialPoses) {
	// KeyListener listener = new MyKeyListener(pos, this, specialPoses);
	//
	// while (true) {
	// jFrame.setVisible(true);
	// jFrame.requestFocus();
	// jFrame.addKeyListener(listener);
	// Print.printAtPosition(pos);
	// Print.printSimply(" ");
	// try {
	// Thread.sleep(sleepTime);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// if (!specialPoses.contains(pos)) {
	// Print.printAtPosition(pos);
	// Print.printSimply(getTableCell(pos).toString());
	// } else {
	// Print.printSpecial(pos, Print.RED, this);
	// }
	// try {
	// Thread.sleep(sleepTime);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// if (((MyKeyListener) listener).isPressedEnter()) {
	// break;
	// }
	//
	// }
	// return table[pos.getY()][pos.getX()];
	// }

	public boolean isEmpty(Position pos) {
		if (getTableCell(pos) instanceof None)
			return true;
		else
			return false;
	}

	public void showGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setVisible(true);
	}

	public BoardPanel getBoard() {
		return board;
	}

}
