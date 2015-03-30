package game;

import game.pieces.None;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Table extends JFrame {
	private Piece[][] table;
//	private JFrame jFrame;
	private JButton[][] buttons;
	public static final Color ORIGINAL_COLOR = (new JButton()).getBackground();
//	private static final int sleepTime = 250;
	GridLayout gridLayout;

	public Table() {
		super("SHOGI");
		table = new Piece[9][9];
		buttons = new JButton[9][9];
		gridLayout = new GridLayout(table.length, table[0].length);
		setLayout(gridLayout);
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				buttons[i][j] = new JButton();
				table[i][j] = new None(this, new Position(j, i));
				buttons[i][j].setText(table[i][j].toString());
				add(buttons[i][j]);
			}
		}

	}

	public void setTableCell(Position pos, Piece type) {
		table[pos.getY()][pos.getX()] = type;
		buttons[pos.getY()][pos.getX()].setText(type.toString());
	}

	public Piece getTableCell(Position pos) {
		return table[pos.getY()][pos.getX()];
	}

	public void swapTableCells(Position pos1, Position pos2) {
		Piece temp;
		temp = getTableCell(pos1);
		setTableCell(pos1, getTableCell(pos2));
		setTableCell(pos2, temp);
		getTableCell(pos1).setPos(pos1);
		getTableCell(pos2).setPos(pos2);
		getButton(pos1).setText(getTableCell(pos1).toString());
		getButton(pos2).setText(getTableCell(pos2).toString());
	}

//	public void print() {
//		for (int i = 8; i >= 0; i--) {
//			for (int j = 0; j < table.length; j++) {
//				Print.printSimply((table[i][j].toString() + " "));
//			}
//			Print.printLine("");
//		}
//	}

//	public Piece blinkCell(Position pos) {
//		KeyListener listener = new MyKeyListener(pos, this);
//		while (true) {
//			jFrame.setVisible(true);
//			jFrame.requestFocus();
//			jFrame.addKeyListener(listener);
//			Print.printAtPosition(pos);
//			Print.printSimply(" ");
//			try {
//				Thread.sleep(sleepTime);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Print.printAtPosition(pos);
//			Print.printSimply(getTableCell(pos).toString());
//			try {
//				Thread.sleep(sleepTime);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (((MyKeyListener) listener).isPressedEnter()) {
//				break;
//			}
//		}
//		return table[pos.getY()][pos.getX()];
//	}

//	public Piece blinkCell(Position pos, ArrayList<Position> specialPoses) {
//		KeyListener listener = new MyKeyListener(pos, this, specialPoses);
//
//		while (true) {
//			jFrame.setVisible(true);
//			jFrame.requestFocus();
//			jFrame.addKeyListener(listener);
//			Print.printAtPosition(pos);
//			Print.printSimply(" ");
//			try {
//				Thread.sleep(sleepTime);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (!specialPoses.contains(pos)) {
//				Print.printAtPosition(pos);
//				Print.printSimply(getTableCell(pos).toString());
//			} else {
//				Print.printSpecial(pos, Print.RED, this);
//			}
//			try {
//				Thread.sleep(sleepTime);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (((MyKeyListener) listener).isPressedEnter()) {
//				break;
//			}
//
//		}
//		return table[pos.getY()][pos.getX()];
//	}

	public boolean isEmpty(Position pos) {
		if (getTableCell(pos) instanceof None)
			return true;
		else
			return false;
	}

	public void showGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
	}

	public JButton[][] getButtons() {
		return buttons;
	}

	public JButton getButton(Position pos) {
		return buttons[pos.getY()][pos.getX()];
	}

	public Position findPressedButton(){
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				if(buttons[i][j].getBackground() == Color.ORANGE)
					return (new Position(j,i));
			}
		}
		return null;
	}
	
}
